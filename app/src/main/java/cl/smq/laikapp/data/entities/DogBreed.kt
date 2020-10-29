package cl.smq.laikapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DogBreed")
data class DogBreed(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val breed: String
)