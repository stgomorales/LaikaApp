package cl.smq.laikapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.smq.laikapp.data.entities.DogBreed
import cl.smq.laikapp.data.entities.DogDetail

@Database(entities = [DogBreed::class, DogDetail::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dogBreedDao(): DogBreedDao
    abstract fun dogDetailDao(): DogDetailDao


    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
                instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
                Room.databaseBuilder(appContext, AppDatabase::class.java, "dogDb")
                        .fallbackToDestructiveMigration()
                        .build()
    }
}