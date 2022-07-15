package uz.javokhirdev.calorytracker.onboarding.presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.javokhirdev.calorytracker.core.model.GoalType
import uz.javokhirdev.calorytracker.core.navigation.Route
import uz.javokhirdev.calorytracker.core.preferences.Preferences
import uz.javokhirdev.calorytracker.core.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class GoalVM @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var selectedGoal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalTypeSelect(goalType: GoalType) {
        selectedGoal = goalType
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoal)
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
        }
    }
}