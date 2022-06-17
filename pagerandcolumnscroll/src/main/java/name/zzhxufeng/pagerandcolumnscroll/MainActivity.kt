package name.zzhxufeng.pagerandcolumnscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import name.zzhxufeng.pagerandcolumnscroll.ui.theme.ComposeExperimentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExperimentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*
                    * TODO
                    * The pager with lazy column will remember the position where it
                    * left.
                    * But things different when it comes to the lazy grid. The lazy grid's
                    * position won't be remembered.
                    *
                    * WHY?
                    * */
                    PagerWithScaffoldLazyColumn()
                }
            }
        }
    }
}
