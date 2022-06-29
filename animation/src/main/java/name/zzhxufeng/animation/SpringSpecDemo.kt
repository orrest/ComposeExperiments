package name.zzhxufeng.animation

import androidx.compose.animation.core.*
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
fun SpringSpecDifferentConfig(
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
fun SpringSpecPreview() {
    SpringSpecDifferentConfig(animationSpec = spring(
        dampingRatio = Spring.DampingRatioHighBouncy,
        stiffness = Spring.StiffnessVeryLow,
        visibilityThreshold = 20.dp
    ))
}

/*
* 原地震动
* */
@Preview
@Composable
fun SpringSpecInPosition() {
    var big by remember { mutableStateOf(false) }
    val size = remember(big) { if(big) 48.dp else 48.dp }
    val anim = remember{ Animatable(size, Dp.VectorConverter) }

    /*Default animation 300ms.*/
    LaunchedEffect(key1 = big, block = {
        anim.animateTo(
            targetValue = size,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessMedium,
                visibilityThreshold = 0.01.dp,
            ),
            initialVelocity = 2000.dp
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