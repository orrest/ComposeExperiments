package name.zzhxufeng.animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InfiniteSpinAnimation(
    modifier: Modifier = Modifier
) {
    val timeUnit by remember { mutableStateOf(500) }

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(timeUnit, easing = LinearEasing)
        )
    )

    Image(
        painter = painterResource(id = R.drawable.spin1),
        contentDescription = "image",
        modifier = modifier
            .graphicsLayer { rotationZ = angle }
            .size(300.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOfThis() {
    Box(modifier = Modifier.fillMaxSize()) {
        InfiniteSpinAnimation(Modifier.align(Alignment.Center))
    }
}