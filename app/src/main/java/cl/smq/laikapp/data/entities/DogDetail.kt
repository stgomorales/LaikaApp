package cl.smq.laikapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DogDetail")
data class DogDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val dogImages: List<String>
)