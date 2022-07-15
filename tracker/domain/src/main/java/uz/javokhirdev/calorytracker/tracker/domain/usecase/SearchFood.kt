package uz.javokhirdev.calorytracker.tracker.domain.usecase

import uz.javokhirdev.calorytracker.tracker.domain.model.TrackableFood
import uz.javokhirdev.calorytracker.tracker.domain.repository.TrackerRepository

class SearchFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchFood(query.trim(), page, pageSize)
    }
}