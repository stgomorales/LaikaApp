package cl.smq.laikapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "DogDetail")
data class DogDetail(
        @PrimaryKey(autoGenerate = true)
    val id: Long,
    @SerializedName("message")
    val dogImages: List<String>
)