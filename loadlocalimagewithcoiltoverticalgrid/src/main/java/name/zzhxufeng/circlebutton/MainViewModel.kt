package name.zzhxufeng.circlebutton

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val imageRepo: LocalImageRepository = LocalImageRepository()
    var localImages by mutableStateOf(listOf<LocalImgModel>())
        private set

    init {
        viewModelScope.launch {
            localImages = imageRepo.loadAllImgs()
            Log.d("MainViewModel", "Local images amount: ${localImages.size}")
        }
    }

}