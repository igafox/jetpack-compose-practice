package com.igafox.composetest

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igafox.composetest.ui.theme.ComposeTestTheme

data class Message(val author: String, val body: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                //Surfaceを付けないとテーマが適応されない
                Surface(
                    //Modifier.fillMaxSize()を使うことで縦横幅にレイアウトを設置する
                    //XMLのmatch_parent的存在
                    modifier = Modifier.fillMaxSize()
                ) {
                    //リストView表示
                    MessageList(messages = SampleData.messages)
                }
            }
        }
    }
}

@Composable
fun MessageCard(message: Message) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //画像表示
            Image(
                painter = painterResource(id = R.mipmap.profile_picture),
                contentDescription = "test",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape) //丸枠トリミング
                    .border(1.dp, MaterialTheme.colors.secondary, CircleShape) //丸枠対応
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = message.author,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                //Surface使うとマテリアルテーマが適応される
                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                    Text(
                        text = message.body,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2
                    )
                }
            }
    }

}

//プレビューを表示させたい場合、@Previewを付ける
//ライトモード時のプレビュー設定
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
//ダークモード時のプレビュー設定
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    ComposeTestTheme {
        MessageCard(message = Message(author = "iga", body = "会津産狐\uD83E\uDD8A"))
    }
}


@Composable
fun MessageList(messages: List<Message>) {
    Surface {
        LazyColumn {
            items(items = messages) { message ->
                MessageCard(message)
            }
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageList() {
    ComposeTestTheme {
        MessageList(SampleData.messages)
    }
}
