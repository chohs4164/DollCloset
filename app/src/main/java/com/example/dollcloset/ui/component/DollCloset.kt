
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dollcloset.R
import com.example.dollcloset.model.CheckData

@Composable
fun DollCloset(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val checkList = rememberSaveable(
        saver = listSaver(
            save = { list -> list.map { it.isChecked } },
            restore = { savedList ->
                mutableStateListOf(
                    CheckData(isChecked = savedList[0], title="arms", R.drawable.arms),
                    CheckData(isChecked = savedList[1], title="eyebrows", R.drawable.eyebrows),
                    CheckData(isChecked = savedList[2], title="glasses", R.drawable.glasses),
                    CheckData(isChecked = savedList[3], title="mouth", R.drawable.mouth),
                    CheckData(isChecked = savedList[4], title="nose", R.drawable.nose),
                    CheckData(isChecked = savedList[5], title="ears", R.drawable.ears),
                    CheckData(isChecked = savedList[6], title="eyes", R.drawable.eyes),
                    CheckData(isChecked = savedList[7], title="hat", R.drawable.hat),
                    CheckData(isChecked = savedList[8], title="mustache", R.drawable.mustache),
                    CheckData(isChecked = savedList[9], title="shoes", R.drawable.shoes),
                )
            }
        )
    ) {
        //디폴트 값
        mutableStateListOf(
            CheckData(isChecked=false, title="arms", R.drawable.arms),
            CheckData(isChecked=false, title="eyebrows", R.drawable.eyebrows),
            CheckData(isChecked=false, title="glasses", R.drawable.glasses),
            CheckData(isChecked=false, title="mouth", R.drawable.mouth),
            CheckData(isChecked=false, title="nose", R.drawable.nose),
            CheckData(isChecked=false, title="ears", R.drawable.ears),
            CheckData(isChecked=false, title="eyes", R.drawable.eyes),
            CheckData(isChecked=false, title="hat", R.drawable.hat),
            CheckData(isChecked=false, title="mustache", R.drawable.mustache),
            CheckData(isChecked=false, title="shoes", R.drawable.shoes),
        )
    }

    if (isPortrait) { //세로모드
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            ImageBox(checkList)
            Text(
                text = "202315323 조현승"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                //체크박스 왼쪽 5개
                Column(horizontalAlignment = Alignment.Start) {

                    checkList.take(5).forEach { item ->
                        CheckItem(item)
                    }
                }
                //체크박스 오른쪽 5개
                Column(horizontalAlignment = Alignment.Start) {
                    checkList.drop(5).forEach { item ->
                        CheckItem(item)
                    }
                }
            }
        }
    } else { //가로모드
        Row(
            modifier = modifier.fillMaxSize(),
        ) {
            //왼쪽의 박스
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageBox(checkList)
            }
            Spacer(modifier = Modifier.width(8.dp))
            //오른쪽의 체크박스와 텍스트
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly, // Changed to SpaceEvenly
                horizontalAlignment = Alignment.Start // Align items to the start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly // Distribute items evenly in the row
                ) {
                    //왼쪽 5개
                    Column(horizontalAlignment = Alignment.Start) {
                        checkList.take(5).forEach { item ->
                            CheckItem(item)
                        }
                    }
                    //오른쪽 5개
                    Column(horizontalAlignment = Alignment.Start) {
                        checkList.drop(5).forEach { item ->
                            CheckItem(item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageBox(checkList: List<CheckData>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        //기본 몸통
        Image(
            painter = painterResource(id = R.drawable.body),
            contentDescription = "body",
            modifier = Modifier.fillMaxSize()
        )
        //체크박스 체크되면 이미지 생기도록
        checkList.forEach { item ->
            if (item.isChecked) {
                Image(
                    painter = painterResource(id = item.imageResourceId),
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun CheckItem(item: CheckData) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp)
    ) {
        //체크박스와
        Checkbox(
            checked = item.isChecked,
            onCheckedChange = { item.isChecked = it }
        )
        //그 옆 텍스트
        Text(text = item.title)
    }
}

@Preview(showBackground = true)
@Composable
fun DollClosetPreviewPortrait() {  //원래 세로 모드 preview로 쓸려고...
    DollCloset()
}

@Preview(showBackground = true)
@Composable
fun DollClosetPreviewLandscape() { //가로 모드 preview로 쓸려고...
    DollCloset()
}