package name.zzhxufeng.circlebutton

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RoundedBar(
    modifier: Modifier = Modifier,
    width: Dp = 219.dp,
    height: Dp = 39.dp,
    text: String = "下一步",
) {
    Row(
        modifier = modifier
            .background(
                color = Color.Red,
                shape = RoundedCornerShape(
                    topStartPercent = 100,
                    topEndPercent = 100,
                    bottomEndPercent = 100,
                    bottomStartPercent = 100,
                )
            ).width(width).height(height),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = text, color = Color.White, fontSize = 20.sp)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewRoundedBar() {
    Box {
        RoundedBar()
    }
}

@Composable
fun SelectableCircle(
    text: String,
    modifier: Modifier = Modifier,
    onSelected: () -> Unit = {},
    number: Int = 0
) {
    var selected by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .clickable {
                selected = !selected
            }
            .background(
                color = if (selected) Color.Red else Color.White,
                shape = RoundedCornerShape(
                    topStartPercent = 100,
                    topEndPercent = 100,
                    bottomEndPercent = 100,
                    bottomStartPercent = 100,
                )
            )
            .width(100.dp)
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (selected){
            Text(text = text, color = Color.White, fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSelectableCircle() {
    Box(Modifier.fillMaxWidth().background(Color.Gray)){
        SelectableCircle("adf", modifier = Modifier
            .align(Alignment.TopEnd), )
    }
}