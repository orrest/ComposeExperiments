package name.zzhxufeng.animation

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TransitionAnimationDemo() {
    Box(modifier = Modifier.fillMaxSize()) {
        var big by remember { mutableStateOf(false) }
        val bigTransition = updateTransition(targetState = big, label = "big_or_small")
        val size by bigTransition.animateDp(label = "size") {
            if (it) 96.dp else 48.dp
        }
        val corner by bigTransition.animateDp(label = "corner") {
            if (it) 8.dp else 18.dp
        }
        Box(modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(corner))
            .background(Color.Black)
            .clickable { big = !big })
    }
}
