package name.zzhxufeng.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview(showSystemUi = true)
@Composable
fun Bouncing() {
    BoxWithConstraints {
        val anim = remember { Animatable(0.dp, Dp.VectorConverter) }
        val animY = remember { Animatable(0.dp, Dp.VectorConverter) }
        val decay = remember { exponentialDecay<Dp>() }
        LaunchedEffect(Unit){
            delay(1000)
            var result = anim.animateDecay(
                initialVelocity = 5000.dp,
                animationSpec = decay
            )
            while (result.endReason == AnimationEndReason.BoundReached) {
                result = anim.animateDecay(- result.endState.velocity, decay)
            }
        }
        LaunchedEffect(Unit){
            delay(1000)
            var result = animY.animateDecay(5000.dp, decay)
            while (result.endReason == AnimationEndReason.BoundReached) {
                result = animY.animateDecay(- result.endState.velocity, decay)
            }
        }
        anim.updateBounds(
            lowerBound = 0.dp,
            upperBound = maxWidth - 100.dp
        )
        animY.updateBounds(
            lowerBound = 0.dp,
            upperBound = maxHeight - 100.dp
        )
        Box(modifier = Modifier
            .padding(
                start = anim.value,
                top = animY.value,
                end = 0.dp,
                bottom = 0.dp
            )
            .size(100.dp)
            .background(Color.Black)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun Bouncing1() {
    BoxWithConstraints {
        val anim = remember { Animatable(500.dp, Dp.VectorConverter) }
        val decay = remember { exponentialDecay<Dp>() }
        LaunchedEffect(Unit){
            delay(1000)
            var result = anim.animateDecay(
                initialVelocity = 5000.dp,
                animationSpec = decay
            )
            while (result.endReason == AnimationEndReason.BoundReached) {
                result = anim.animateDecay(- result.endState.velocity, decay)
            }
        }

        anim.updateBounds(
            lowerBound = 0.dp,
            upperBound = maxHeight-100.dp
        )

        Box(modifier = Modifier
            .padding(
                start = 100.dp,
                top = anim.value,
                end = 0.dp,
                bottom = 0.dp
            )
            .size(100.dp)
            .background(
                color = Color.Black,
                shape = CircleShape
            )
        )
    }
}