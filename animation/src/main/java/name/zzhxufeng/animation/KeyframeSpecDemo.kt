package name.zzhxufeng.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun KeyframeSpecDifferentConfig(
    animationSpec: AnimationSpec<Dp>
) {
    var big by remember { mutableStateOf(false) }
    val size = remember(big) { if(big) 96.dp else 48.dp }
    val anim = remember{ Animatable(size, Dp.VectorConverter) }

    /*Default animation 300ms.*/
    LaunchedEffect(key1 = big, block = {
        anim.animateTo(
            targetValue = size,
            animationSpec = animationSpec
        )
    })

    Box(
        modifier = Modifier
            .size(anim.value)
            .background(Color.Green)
            .clickable {
                big = !big
            }
    )
}

@Preview
@Composable
fun KeyframeSpecOneFrame() {
    KeyframeSpecDifferentConfig(animationSpec = keyframes {
        144.dp at 50 /*144.dp at 150ms*/
        100.dp at 100
        144.dp at 150
        100.dp at 200
    })
}