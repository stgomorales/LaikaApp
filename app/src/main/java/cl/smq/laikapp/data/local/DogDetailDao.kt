package cl.smq.laikapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.smq.laikapp.data.entities.DogDetail

@Dao
interface DogDetailDao {

    @Query("SELECT * FROM DogDetail WHERE dogImages LIKE '%' ||  :breed || '%' limit 1")
    fun getAllDogDetail(breed: String) : LiveData<DogDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogDetails: List<DogDetail>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogDetail: DogDetail)
}