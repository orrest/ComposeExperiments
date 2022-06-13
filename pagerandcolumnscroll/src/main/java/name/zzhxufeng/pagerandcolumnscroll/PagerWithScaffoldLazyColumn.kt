package name.zzhxufeng.pagerandcolumnscroll

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/*
* https://www.rockandnull.com/jetpack-compose-swipe-pager/
* */
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
        HorizontalPager(
            /* the pager num, same as the tab num */
            count = tabTitles.size,
            /* pager state for the current position info */
            state = pagerState,
        ) {
            LazyColumn {
                item {
                    repeat(200) { i ->
                        Text(
                            text = i.toString(),
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        )
                    }
                }
            }
        }
    }
}