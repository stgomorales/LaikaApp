package cl.smq.laikapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    var gson = Gson()

    @TypeConverter
    fun dogImagesListToString(dogImages: List<String>?): String? {
        return if (dogImages.isNullOrEmpty())
            null
        else
            gson.toJson(dogImages)
    }

    @TypeConverter
    fun stringToDogImagesList(data: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {
        }.type
        return if (data.isNullOrEmpty())
            null
        else
            gson.fromJson(data, listType)
    }

}