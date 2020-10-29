package cl.smq.laikapp.data.remote

import javax.inject.Inject

class DogBreedRemoteDataSource @Inject constructor(
    private val dogBreedService: DogBreedService): RemoteDataSource(){

    suspend fun getallDogBreed() = getResult { dogBreedService.getDogBreads() }
    suspend fun getDogDetail(breed :String) = getResult { dogBreedService.getDogDetail(breed) }
}