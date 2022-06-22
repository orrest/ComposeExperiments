package name.zzhxufeng.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay

@Preview(showSystemUi = true)
@Composable
fun PreviewTextField() {
    Box {
        TextField()
    }
}

@Composable
fun TextField() {
    var currentNum by remember { mutableStateOf(0) }
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        androidx.compose.material.TextField(
            value = text,
            placeholder = { Text(text = "请填写正文", color = Color.Gray) },
            onValueChange = {
                currentNum = it.length
                text = it
            },
            modifier = Modifier
                .background(Color.Gray)
                .height(153.dp)
                .fillMaxWidth(),
        )

        val showDialog = remember {
            mutableStateOf(false)
        }
        if (showDialog.value) {
            ToastBar(text = "超出字数限制", show = showDialog)
        }

        TextNumIndicator(
            currentNum = currentNum,
            modifier = Modifier.align(Alignment.BottomEnd),
            onExceed = { showDialog.value = true }
        )
    }
}

@Composable
fun TextNumIndicator(
    currentNum: Int,
    modifier: Modifier = Modifier,
    limitedNum: Int = remember { 20 },
    onExceed: () -> Unit
) {
    val fontSize = remember {12.sp}
    if(currentNum > limitedNum) {
        onExceed()
    }
    Row(modifier) {
        Text(text = currentNum.toString(), fontSize = fontSize, color = Color.White)
        Text(text = "/",                   fontSize = fontSize, color = Color.White)
        Text(text = limitedNum.toString(), fontSize = fontSize, color = Color.White)
    }
}

@Composable
fun ToastBar(
    text: String,
    duration: Long = 2000,
    show: MutableState<Boolean>
) {
    LaunchedEffect(key1 = Unit, block = {
        delay(duration)
        show.value = false
    })
    if (show.value){
        Dialog(onDismissRequest = { /*Do nothing.*/ }) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }
    }
}