package cl.smq.laikapp.data.repository

import cl.smq.laikapp.data.local.DogBreedDao
import cl.smq.laikapp.data.local.DogDetailDao
import cl.smq.laikapp.data.remote.DogBreedRemoteDataSource
import cl.smq.laikapp.utils.performGetOperation

class DogRepository(
    private val remoteDataSource: DogBreedRemoteDataSource,
    private val localDogBreedDataSource: DogBreedDao,
    private val localDogDetailDataSource: DogDetailDao) {

    fun getAllDogBreed() = performGetOperation(
        databaseQuery = {localDogBreedDataSource.getAllDogBreed()},
        networkCall = suspend { remoteDataSource.getallDogBreed()},
        saveCallResult = {localDogBreedDataSource.insertAll(it)}
    )

    fun getDogDetail(breed: String) = performGetOperation(
        databaseQuery = {localDogDetailDataSource.getAllDogDetail(breed)},
        networkCall = suspend { remoteDataSource.getdogDetail(breed) },
        saveCallResult = {localDogDetailDataSource.insert(it)}
    )
}