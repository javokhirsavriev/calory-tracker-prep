package uz.javokhirdev.calorytracker.tracker.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.javokhirdev.calorytracker.tracker.data.local.entity.TrackedFoodEntity

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Query(
        """
             SELECT *
             FROM trackedfoodentity
             WHERE dayOfMonth = :day AND month = :month AND year = :year
         """
    )
    fun getFoodsForDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>
}