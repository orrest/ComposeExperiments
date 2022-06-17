package name.zzhxufeng.pagerandcolumnscroll

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun PagerWithScaffoldLazyGrid() {
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun PagerWithScaffoldLazyColumn() {
        val tabTitles = remember {listOf("Hello", "There", "World")}
        /* it contains the current page location */
        val pagerState = rememberPagerState()
        /* for the scroll animation */
        val scope = rememberCoroutineScope()

        Log.d("PagerWithLazyColumn", "whole recomposing...")

        Scaffold(
            topBar = {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            /*pager modifier api*/
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                        )
                    }
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            /* scroll to the clicked page */
                            onClick = { scope.launch{pagerState.scrollToPage(index)} },
                            text = { Text(text = title) }
                        )
                    }
                }
            }
        ) { it ->
            val i = it
            val data: List<String> = listOf(
                "☕️", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄", "🥑",
                "☕️", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄", "🥑",
                "☕️", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄", "🥑",
                "☕️", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄", "🥑",
                "☕️", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄", "🥑",
                "☕️", "🙂", "🥛", "🎉", "📐", "🎯", "🧩", "😄", "🥑",
            )

            HorizontalPager(
                /* the pager num, same as the tab num */
                count = tabTitles.size,
                /* pager state for the current position info */
                state = pagerState,
            ) {
                when (pagerState.currentPage) {
                    0 -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                        ) {
                            items(data) {
                                Card(
                                    modifier = Modifier.padding(4.dp),
                                    backgroundColor = Color(
                                        red = Random.nextInt(0, 255),
                                        green = Random.nextInt(0, 255),
                                        blue = Random.nextInt(0, 255)
                                    )
                                ) {
                                    Text(
                                        text = it,
                                        fontSize = 42.sp,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(24.dp)
                                    )
                                }
                            }
                        }
                    }
                    1 -> {
                        Box(modifier = Modifier.fillMaxSize()){
                            Text(text = "nothing")
                        }
                    }
                    2 -> {
                        Box(modifier = Modifier.fillMaxSize())
                    }
                }

            }
        }
    }
}