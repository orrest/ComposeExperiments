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

@Preview
@Composable
fun AnimatableDemo1() {
    var big by remember { mutableStateOf(false) }
    val size = remember(big) { if(big) 96.dp else 48.dp }
    val anim = remember{ Animatable(size, Dp.VectorConverter) }

    /*
    * Two processes will be triggered:
    * 1. big -> small
    *   snapTo 0.dp -> animateTo 48.dp
    * 2. small -> big
    *   snapTo 192.dp -> animateTo 96.dp
    * */
    LaunchedEffect(key1 = big, block = {
        anim.snapTo(if(big) 192.dp else 0.dp)
        anim.animateTo(size)
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
fun AnimatableDemo2Bounce() {
    var big by remember { mutableStateOf(false) }
    val size = remember(big) { if(big) 96.dp else 48.dp }
    val anim = remember{ Animatable(size, Dp.VectorConverter) }

    LaunchedEffect(key1 = big, block = {
        anim.animateTo(
            targetValue = size,
            animationSpec = spring(Spring.DampingRatioHighBouncy)
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

@Composable
fun TweenSpecDifferentEasing(
    animationSpec: AnimationSpec<Dp>
) {
    var big by remember { mutableStateOf(false) }
    val size = remember(big) { if(big) 96.dp else 48.dp }
    val anim = remember{ Animatable(size, Dp.VectorConverter) }

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
fun TweenSpecLinearOutSlowInEasing() {
    TweenSpecDifferentEasing(animationSpec = TweenSpec(easing = LinearOutSlowInEasing))
}

@Preview
@Composable
fun TweenSpecFastOutSlowInEasing() {
    TweenSpecDifferentEasing(animationSpec = TweenSpec(easing = FastOutSlowInEasing))
}

@Preview
@Composable
fun TweenSpecFastOutLinearInEasing() {
    TweenSpecDifferentEasing(animationSpec = TweenSpec(easing = FastOutLinearInEasing))
}