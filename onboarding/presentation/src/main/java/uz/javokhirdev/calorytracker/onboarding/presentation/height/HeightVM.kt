package uz.javokhirdev.calorytracker.onboarding.presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.javokhirdev.calorytracker.core.R
import uz.javokhirdev.calorytracker.core.domain.usecase.FilterOutDigits
import uz.javokhirdev.calorytracker.core.navigation.Route
import uz.javokhirdev.calorytracker.core.domain.preferences.Preferences
import uz.javokhirdev.calorytracker.core.util.UiEvent
import uz.javokhirdev.calorytracker.core.util.UiText
import javax.inject.Inject

@HiltViewModel
class HeightVM @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var height by mutableStateOf("180")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightEnter(height: String) {
        if (height.length <= 3) {
            this.height = filterOutDigits(height)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val heightNumber = height.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }
}