package cl.smq.laikapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    var gson = Gson()

    @TypeConverter
    fun ListToString(serieItems: List<String>?): String? {
        return if (serieItems.isNullOrEmpty())
            null
        else
            gson.toJson(serieItems)
    }

    @TypeConverter
    fun stringToList(data: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {
        }.type
        return if (data.isNullOrEmpty())
            null
        else
            gson.fromJson(data, listType)
    }

}