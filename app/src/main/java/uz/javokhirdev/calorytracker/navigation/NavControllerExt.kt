package uz.javokhirdev.calorytracker.navigation

import androidx.navigation.NavController
import uz.javokhirdev.calorytracker.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}