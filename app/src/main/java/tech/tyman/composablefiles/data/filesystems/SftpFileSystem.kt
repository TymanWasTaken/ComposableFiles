package tech.tyman.composablefiles.data.filesystems

import com.jcraft.jsch.*
import tech.tyman.composablefiles.data.FileSystem
import tech.tyman.composablefiles.data.FileSystemEntry
import java.io.File
import kotlin.io.path.Path

class SftpFileSystem(
    private val username: String,
    private val host: String,
    private val port: Int,
    private val onPasswordPrompt: (message: String) -> String?,
    private val onConfirmationPrompt: (message: String) -> Boolean,
    private val onMessage: (message: String) -> Unit
) : FileSystem() {
    private val jSch = JSch()
    lateinit var session: Session
    lateinit var channel: ChannelSftp

    override fun load() {
        super.load()
        val userInfo = SftpUserInfo(
            onPasswordPrompt,
            onConfirmationPrompt,
            onMessage
        )
        session = jSch.getSession(username, host, port).apply {
            setUserInfo(userInfo)
            connect()
        }
        channel = session.openChannel("sftp").apply {
            connect()
        } as ChannelSftp
    }

    override fun unload() {
        super.unload()
        channel.disconnect()
        session.disconnect()
    }

    override fun getEntry(path: String): FileSystemEntry {
        val stat = channel.stat(path)
        return SftpFileSystemEntry(
            fileSystem = this,
            sftpATTRS = stat,
            path = path
        )
    }
}

class SftpFileSystemEntry(
    sftpATTRS: SftpATTRS,
    override val fileSystem: SftpFileSystem,
    override val path: String
) : FileSystemEntry() {
    override val name: String = File(path).name
    override val extension: String = File(path).extension
    override val lastModified: Long = sftpATTRS.mTime.toLong()
    override val isDirectory: Boolean = sftpATTRS.isDir

    override fun readBytes(): ByteArray {
        val stream = fileSystem.channel.get(path)
        return stream.readBytes().also {
            stream.close()
        }
    }

    override fun writeBytes(data: ByteArray): Boolean {
        try {
            val stream = fileSystem.channel.put(path)
            stream.write(data).also {
                stream.close()
            }
        } catch (e: SftpException) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun delete(recursive: Boolean): Boolean {
        try {
            if (recursive) fileSystem.channel.rmdir(path)
            else fileSystem.channel.rm(path)
        } catch (e: SftpException) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun listFiles(): List<SftpFileSystemEntry> {
        val ls = fileSystem.channel.ls(path)
        return ls.map {
            SftpFileSystemEntry(
                fileSystem = fileSystem,
                sftpATTRS = it.attrs,
                path = Path(path, it.filename).toString()
            )
        }
    }

    override fun getParent(): SftpFileSystemEntry? {
        val parentPath = File(path).parent ?: return null
        val stat = fileSystem.channel.stat(parentPath)
        return SftpFileSystemEntry(
            fileSystem = fileSystem,
            sftpATTRS = stat,
            path = parentPath
        )
    }

}

class SftpUserInfo(
    private val onPasswordPrompt: (message: String) -> String?,
    private val onConfirmationPrompt: (message: String) -> Boolean,
    private val onMessage: (message: String) -> Unit
) : UserInfo {
    private var password: String? = null

    override fun getPassphrase() = null
    override fun getPassword() = password

    override fun promptPassword(message: String): Boolean {
        password = onPasswordPrompt(message) ?: return false
        return true
    }

    override fun promptPassphrase(message: String): Boolean = true

    override fun promptYesNo(message: String): Boolean = onConfirmationPrompt(message)

    override fun showMessage(message: String) = onMessage(message)
}