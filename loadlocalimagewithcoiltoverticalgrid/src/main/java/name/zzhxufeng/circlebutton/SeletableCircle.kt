package name.zzhxufeng.circlebutton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectableCircle(
    number: Int = 0,
    onSelected: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(false) }
    IconButton(
        onClick = {
            onSelected()
            selected = !selected
        },
        modifier = Modifier.size(24.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = if (!selected) {
                    Icons.Outlined.RadioButtonUnchecked
                } else {
                    Icons.Filled.Circle
                },
                contentDescription = "",
                tint = if (!selected) { Color.White } else { Color.Red },
            )
            if (selected) {
                Text(
                    text = number.toString(),
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x00000000)
@Composable
fun PreviewSelectableCircle() {
    SelectableCircle(modifier = Modifier, )
}