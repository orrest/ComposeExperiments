package name.zzhxufeng.alignment

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SomethingInBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green)
    ){
        Button(onClick = {  }) {
            Image(imageVector = Icons.Filled.Clear, contentDescription = "close")
        }
        Row(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Text(text = "A")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "B")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BoxAlignment() {
    Box(
        modifier = Modifier.background(Color.Green)
    ) {
        Text(text = "center", modifier = Modifier.align(Alignment.Center))
        Text(text = "top end", modifier = Modifier.align(Alignment.TopEnd))
        Text(text = "top start", modifier = Modifier.align(Alignment.TopStart))
        Text(text = "top center", modifier = Modifier.align(Alignment.TopCenter))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BoxDefaultAlignment(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(text = "default alignment")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SomeCompose() {
    Box{
        BoxDefaultAlignment(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
