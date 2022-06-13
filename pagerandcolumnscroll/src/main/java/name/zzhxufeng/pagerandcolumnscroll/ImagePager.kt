package name.zzhxufeng.pagerandcolumnscroll

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


/*
* https://google.github.io/accompanist/pager/?from=from_parent_mindnote
* */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePager() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("title") },
                backgroundColor = MaterialTheme.colors.surface,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            val pagerState = rememberPagerState()

            // Display 10 items
            HorizontalPager(
                count = 10,
                state = pagerState,
                // Add 32.dp horizontal padding to 'center' the pages
                contentPadding = PaddingValues(horizontal = 32.dp),
                // Add some horizontal spacing between items
                itemSpacing = 4.dp,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .horizontalScroll(rememberScrollState())
                ) {
                    PagerSampleItem(
                        page = page,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )

                    PagerSampleItem(
                        page = page,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )

                    PagerSampleItem(
                        page = page,
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                }
            }

            ActionsRow(
                pagerState = pagerState,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun ActionsRow(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    infiniteLoop: Boolean = false
) {
    Row(modifier) {
        val scope = rememberCoroutineScope()

        IconButton(
            enabled = infiniteLoop.not() && pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
        ) {
            Icon(Icons.Default.FirstPage, null)
        }

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }
        ) {
            Icon(Icons.Default.NavigateBefore, null)
        }

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        ) {
            Icon(Icons.Default.NavigateNext, null)
        }

        IconButton(
            enabled = infiniteLoop.not() && pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.pageCount - 1)
                }
            }
        ) {
            Icon(Icons.Default.LastPage, null)
        }
    }
}

