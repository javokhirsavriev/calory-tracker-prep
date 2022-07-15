package uz.javokhirdev.calorytracker.tracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.calorytracker.tracker.domain.model.TrackedFood
import uz.javokhirdev.calorytracker.tracker.domain.repository.TrackerRepository
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}