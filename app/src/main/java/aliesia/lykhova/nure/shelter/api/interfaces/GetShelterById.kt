package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.data.shelter.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetShelterById {
    @GET("http://192.168.1.15/api/en/shelter/{id}/")
    fun getShelterById(@Path("id") int: Int): Call<Shelter>
}