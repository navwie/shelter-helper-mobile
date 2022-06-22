package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.Shelter
import retrofit2.Call
import retrofit2.http.GET

interface GetAllShelters {
    @GET("http://192.168.1.15/api/en/shelter/")
    fun getShelterList(): Call<ArrayList<Shelter>>;
}