package uz.javokhirdev.calorytracker.tracker.presentation.search

import uz.javokhirdev.calorytracker.tracker.domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)