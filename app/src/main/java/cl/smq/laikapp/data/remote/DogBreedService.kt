package cl.smq.laikapp.data.remote

import cl.smq.laikapp.data.entities.DogBreed
import cl.smq.laikapp.data.entities.DogDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedService {

    @GET("breeds/list")
    suspend fun getDogBreads(): Response<DogBreed>

    @GET("breed/{breed}/images")
    suspend fun getDogDetail(@Path("breed") breed :String) : Response<DogDetail>

}