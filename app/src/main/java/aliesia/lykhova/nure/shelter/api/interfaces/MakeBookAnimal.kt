package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.booked_animal.BookedAnimalForm
import aliesia.lykhova.nure.shelter.data.booked_animal.BookedAnimalResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface MakeBookAnimal {
    @PUT("http://192.168.1.15/api/en/animal/booked/{id}/")
    fun makeBook(@Header("Authorization") authToken: String, @Path("id") animalId: Int, @Body requestBody: BookedAnimalForm): Call<BookedAnimalResponse>;
}