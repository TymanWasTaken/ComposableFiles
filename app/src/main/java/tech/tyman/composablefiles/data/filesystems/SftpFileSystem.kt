package tech.tyman.composablefiles.data.filesystems

import com.jcraft.jsch.*
import tech.tyman.composablefiles.data.FileSystem
import tech.tyman.composablefiles.data.FileSystemEntry
import java.io.File

class SftpFileSystem(
    private val username: String,
    private val host: String,
    private val port: Int,
    private val onPasswordPrompt: (message: String) -> String?,
    private val onConfirmationPrompt: (message: String) -> Boolean,
    private val onMessage: (message: String) -> Unit
) : FileSystem() {
    private val jSch = JSch()
    private lateinit var session: Session
    private lateinit var channel: ChannelSftp

    override fun load() {
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

    override fun getEntry(path: String): FileSystemEntry {
        val stat = channel.stat(path)
    }
}

class SftpFileSystemEntry(
    override val fileSystem: FileSystem,
    val sftpATTRS: SftpATTRS,
    override val path: String
) : FileSystemEntry() {
    override val name: String = File(path).name
    override val extension: String = File(path).extension
    override val lastModified: Long = sftpATTRS.mTime.toLong()
    override val isDirectory: Boolean = sftpATTRS.isDir

    override fun readString(): String? {
        return files
    }

    override fun writeString(data: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(recursive: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun listFiles(): List<FileSystemEntry>? {
        TODO("Not yet implemented")
    }

    override fun getParent(): FileSystemEntry? {
        TODO("Not yet implemented")
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