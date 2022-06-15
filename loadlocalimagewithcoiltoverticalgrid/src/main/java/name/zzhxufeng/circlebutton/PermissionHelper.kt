package name.zzhxufeng.circlebutton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

/**
 * android.Manifest.permission.*
 *
 * Template method.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHelper(
    permission: String,
    content: @Composable () -> Unit
) {
    val permissionState = rememberPermissionState(permission = permission)
    if (permissionState.status.isGranted) {
        content()
    } else {
        LaunchedEffect(key1 = true){
            permissionState.launchPermissionRequest()
        }
    }
}

@Composable
fun MultiPermissionsHelper(

) {

}
