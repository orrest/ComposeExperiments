package name.zzhxufeng.lazycolumnrowstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import name.zzhxufeng.lazycolumnrowstate.ui.theme.ComposeExperimentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var checkout by remember {
                mutableStateOf(false)
            }
            val viewModel: MainViewModel = viewModel()
            ComposeExperimentsTheme {
                Scaffold(
                    floatingActionButton = {
                        IconButton(onClick = { checkout = !checkout }) {
                            Image(imageVector = Icons.Default.Add, contentDescription = "")
                        }
                    }
                ) {
                    if (checkout) {

                        LazyColumnRowState(viewModel)

                    } else {
                        Text(text = "An empty compose")
                    }
                }
            }
        }
    }
}

