package name.zzhxufeng.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AnimateAsState() {
    var big by remember{ mutableStateOf(false) }
    val size by animateDpAsState(targetValue = if (big) 96.dp else 48.dp)
    val color by animateColorAsState(targetValue = if (big) Color.Green else Color.Blue)

    Box(modifier = Modifier
        .size(size)
        .background(color)
        .clickable {
            big = !big
        })
}
