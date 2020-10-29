package cl.smq.laikapp.data.repository

import cl.smq.laikapp.data.local.DogBreedDao
import cl.smq.laikapp.data.local.DogDetailDao
import cl.smq.laikapp.data.remote.DogBreedRemoteDataSource
import cl.smq.laikapp.utils.performGetOperation
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val remoteDataSource: DogBreedRemoteDataSource,
    private val localDogBreedDataSource: DogBreedDao,
    private val localDogDetailDataSource: DogDetailDao) {

    fun getAllDogBreed() = performGetOperation(
        databaseQuery = {localDogBreedDataSource.getAllDogBreed()},
        networkCall = suspend { remoteDataSource.getallDogBreed()},
        saveCallResult = {localDogBreedDataSource.insert(it)}
    )

    fun getDogDetail(breed: String) = performGetOperation(
        networkCall = suspend { remoteDataSource.getDogDetail(breed) },
        saveCallResult = {localDogDetailDataSource.insert(it)},
        databaseQuery = {localDogDetailDataSource.getAllDogDetail(breed)}
    )
}