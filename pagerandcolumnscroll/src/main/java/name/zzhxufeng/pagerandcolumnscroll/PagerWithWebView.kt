package name.zzhxufeng.pagerandcolumnscroll

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.web.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerWithWebView() {
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
            /*
            * Wrap the web view in the LazyColumn item, the vertical scroll will be just fine.
            * But why?
            *
            * TODO
            * */
            WebView()
        }
    }
}

@Composable
fun WebView() {
    LazyColumn {
        item {
            WebView(
                url = "https://www.baidu.com"
            )
        }
    }
}
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(
    url: String
) {
    var urlState by remember { mutableStateOf(url) }
    val state = rememberWebViewState(url = urlState)
    val navigator = rememberWebViewNavigator()
    var textFieldValue by remember(state.content.getCurrentUrl()) {
        mutableStateOf(state.content.getCurrentUrl() ?: "")
    }

    Column {
        TopAppBar(
            title = { Text(text = "WebView Sample") },
            navigationIcon = {
                if (navigator.canGoBack) {
                    IconButton(onClick = { navigator.navigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            }
        )

        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // A custom WebViewClient and WebChromeClient can be provided via subclassing
        val webClient = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    /*could use some indicator here.*/
                    Log.d("Accompanist WebView", "Page started loading for $url")
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    /*could shutdown indicator here.*/
                    Log.d("Accompanist WebView", "Page finished loading for $url")
                }

                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    super.onReceivedSslError(view, handler, error)
                    Log.d("Accompanist WebView", "Received ssl error! \n $url")
                }
            }
        }

        WebView(
            state = state,
            modifier = Modifier.fillMaxSize(),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
                /*TODO cookie*/
                /*agent*/
                webView.overScrollMode = WebView.OVER_SCROLL_NEVER
            },
            client = webClient
        )
    }
}
