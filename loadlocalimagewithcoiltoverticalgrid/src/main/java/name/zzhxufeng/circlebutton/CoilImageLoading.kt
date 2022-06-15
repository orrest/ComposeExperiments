package name.zzhxufeng.circlebutton

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlin.random.Random


@Composable
fun LocalImageGrid(
    images: List<LocalImgModel>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
    ) {
        items(images) { item ->
            Log.d("LocalImageGrid", item.uri.path.toString())
            LocalImage(item.thumbnailUri)
        }
    }
}

@Composable
fun LocalImage(
    uri: Uri
) {
    val painter = rememberAsyncImagePainter(
        model = uri
    )
    Image(
        painter = painter,
        contentDescription = null,
        /*FillBounds in the column.*/
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(123.dp),
    )
}

/**
 * https://alexzh.com/jetpack-compose-building-grids/
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsingLazyVerticalGrid(
    data: List<String> = listOf("☕️", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄", "🥑")
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(data) { item ->
            Card(
                modifier = Modifier.padding(4.dp),
                backgroundColor = Color(
                    red = Random.nextInt(0, 255),
                    green = Random.nextInt(0, 255),
                    blue = Random.nextInt(0, 255)
                )
            ) {
                Text(
                    text = item,
                    fontSize = 42.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLazyVerticalGrid() {
    UsingLazyVerticalGrid()
}
