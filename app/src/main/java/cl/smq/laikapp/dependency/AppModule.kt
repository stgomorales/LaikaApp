package cl.smq.laikapp.dependency

import android.content.Context
import cl.smq.laikapp.BuildConfig
import cl.smq.laikapp.data.local.AppDatabase
import cl.smq.laikapp.data.local.DogBreedDao
import cl.smq.laikapp.data.local.DogDetailDao
import cl.smq.laikapp.data.remote.DogBreedRemoteDataSource
import cl.smq.laikapp.data.remote.DogBreedService
import cl.smq.laikapp.data.repository.DogRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideDogBreedService(retrofit: Retrofit):DogBreedService = retrofit.create(DogBreedService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(dogBreedService: DogBreedService) = DogBreedRemoteDataSource(dogBreedService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideDogBreedDao(db: AppDatabase) = db.dogBreedDao()

    @Singleton
    @Provides
    fun provideDogDetailDao(db: AppDatabase) = db.dogDetailDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: DogBreedRemoteDataSource,
                          localDogBreedDao: DogBreedDao,
                          localDogDetailDao: DogDetailDao) =
        DogRepository(remoteDataSource, localDogBreedDao, localDogDetailDao)
}