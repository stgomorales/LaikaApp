package cl.smq.laikapp.data.entities

import androidx.room.PrimaryKey

data class DogDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val dogImages: List<String>
)