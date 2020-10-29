package cl.smq.laikapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.smq.laikapp.data.entities.DogBreed

@Dao
interface DogBreedDao {


    @Query("SELECT * FROM DogBreed LIMIT 1")
    fun getAllDogBreed() : LiveData<List<DogBreed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogBreed: DogBreed)

}