package name.zzhxufeng.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldWithAutoImePosition() {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.show()

    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box(modifier = Modifier.fillMaxSize()) {
            androidx.compose.material.TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.align(Alignment.BottomCenter),
                keyboardOptions = KeyboardOptions.Default.copy()
            )
        }
    }
}