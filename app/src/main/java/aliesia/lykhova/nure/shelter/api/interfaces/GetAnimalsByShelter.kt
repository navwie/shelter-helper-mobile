package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.shelter.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetAnimalsByShelter {
    @GET("http://192.168.1.15/api/en/animal/{id}/")
    fun getAnimalsList(@Path("id") int: Int): Call<ArrayList<Animal>>
}