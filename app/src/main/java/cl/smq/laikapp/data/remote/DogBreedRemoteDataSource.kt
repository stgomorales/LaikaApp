package cl.smq.laikapp.data.remote

class DogBreedRemoteDataSource (
    private val dogBreedService: DogBreedService): RemoteDataSource(){

    suspend fun getallDogBreed() = getResult { dogBreedService.getDogBreads() }
    suspend fun getdogDetail(breed :String) = getResult { dogBreedService.getDogDetail(breed) }
}