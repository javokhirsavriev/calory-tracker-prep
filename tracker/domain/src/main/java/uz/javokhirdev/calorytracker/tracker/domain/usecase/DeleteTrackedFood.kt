package uz.javokhirdev.calorytracker.tracker.domain.usecase

import uz.javokhirdev.calorytracker.tracker.domain.model.TrackedFood
import uz.javokhirdev.calorytracker.tracker.domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}