package uz.javokhirdev.calorytracker.tracker.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.javokhirdev.calorytracker.tracker.data.local.TrackerDao
import uz.javokhirdev.calorytracker.tracker.data.mapper.toTrackableFood
import uz.javokhirdev.calorytracker.tracker.data.mapper.toTrackedFood
import uz.javokhirdev.calorytracker.tracker.data.mapper.toTrackedFoodEntity
import uz.javokhirdev.calorytracker.tracker.data.remote.OpenFoodApi
import uz.javokhirdev.calorytracker.tracker.domain.model.TrackableFood
import uz.javokhirdev.calorytracker.tracker.domain.model.TrackedFood
import uz.javokhirdev.calorytracker.tracker.domain.repository.TrackerRepository
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products.mapNotNull { it.toTrackableFood() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}