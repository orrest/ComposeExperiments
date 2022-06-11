package name.zzhxufeng.lazycolumnrowstate

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun LazyColumnRowState(
    viewModel: MainViewModel,
) {
    val flowItems = viewModel.articleFlow.collectAsLazyPagingItems()
    val banners = viewModel.banners.value

    val listState: LazyListState
    // Google issue https://issuetracker.google.com/issues/177245496?pli=1
    if (flowItems.itemCount > 0){
        /*
        * 当flowItems > 0的时候，证明我们加载过这个paging。
        * 当我们滑动的时候，这个listState会被滑动事件刷新，拥有新值。而这个值总是在 item > 0 的时候被刷新的，
        * 这意味着它的位置是我们预期的那个对的位置。
        * */
        listState = viewModel.listState
        Log.d("LazyColumn", "item count > 0 | \"${listState.firstVisibleItemIndex}, ${listState.firstVisibleItemScrollOffset}\"")
    } else {
        /*
        * 只有在compose切换的时候那个第一帧是item==0的，实际上在第一次加载之后item都是>0的。
        * 当item == 0的时候，返回的 rememberLazyListState() 其实是 0, 0。
        * remember避免了每次都创建新对象！ LazyListState(0,0)
        * */
        listState = rememberLazyListState()
        Log.d("LazyColumn", "item count == 0 | \"${listState.firstVisibleItemIndex}, ${listState.firstVisibleItemScrollOffset}\"")
        Log.d("LazyColumn", "Just return! So the 0,0 frame will not be visible!")
        return
    }

    LazyColumn(
        state = listState
    ) {
        Log.d("LazyColumn", "run into ...")

        item {
            LazyRow(modifier = Modifier.padding(5.dp), state = viewModel.rowState) {
                items(banners) { item ->
                    BannerImage(
                        banner = item,
                        onBannerClick = {},
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }

        items(flowItems) { item ->
            Text(text = item!!.title)
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}

@Composable
fun BannerImage(
    banner: BannerModel,
    onBannerClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.size(400.dp, 240.dp)
    ) {
        Column(
            modifier = Modifier.clickable { onBannerClick(banner.url) }
        ){
             Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = banner.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
