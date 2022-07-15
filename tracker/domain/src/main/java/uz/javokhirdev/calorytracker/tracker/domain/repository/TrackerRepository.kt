package uz.javokhirdev.calorytracker.tracker.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.calorytracker.tracker.domain.model.TrackableFood
import uz.javokhirdev.calorytracker.tracker.domain.model.TrackedFood
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}