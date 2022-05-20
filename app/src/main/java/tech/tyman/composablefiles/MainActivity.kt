package tech.tyman.composablefiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.tyman.composablefiles.ui.theme.ComposableFilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableFilesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(all = 8.dp)) {
                        listOf(
                            Message(
                                "Tyman",
                                "I love jetpack compose",
                                "https://cdn.discordapp.com/avatars/487443883127472129/c4a0a7c5024629b55015dafaafd1b77c.webp?size=2048"
                            ),
                            Message(
                                "Ven",
                                "I also love jetpack compose",
                                "https://cdn.discordapp.com/avatars/343383572805058560/7248f0cff67f0582f60f7ee7871e3a02.webp?size=2048"
                            ),
                            Message(
                                "Xinto",
                                "I too, love jetpack compose",
                                "https://cdn.discordapp.com/avatars/423915768191647755/03a900cde1522cc53936638774a3060c.webp?size=2048"
                            ),
                            Message(
                                "Juby",
                                "I like jetpack compose",
                                "https://cdn.discordapp.com/avatars/324622488644616195/e6f68af616cdc26abfa2a233b0fff795.webp?size=2048"
                            ),
                            Message(
                                "Zt",
                                "I LOVE jetpack compose",
                                "https://cdn.discordapp.com/avatars/289556910426816513/ad1e67ba1db9188b924cdfddd4058012.webp?size=2048"
                            )
                        ).forEach {
                            MessageCard(msg = it)
                        }
                    }
                }
            }
        }
    }
}

data class Message(val author: String, val body: String, val avatarUrl: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        AsyncImage(
            model = msg.avatarUrl,
            contentDescription = "${msg.author}'s avatar",
            modifier = Modifier
                .size(size = 40.dp)
                .clip(shape = CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.padding(all = 8.dp))

        Column {
            Text(
                text = msg.author,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = msg.body, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}