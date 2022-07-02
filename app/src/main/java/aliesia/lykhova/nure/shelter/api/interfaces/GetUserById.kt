package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.Shelter
import aliesia.lykhova.nure.shelter.data.shelter.Animal
import aliesia.lykhova.nure.shelter.data.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GetUserById {
    @GET("http://192.168.1.15/api/en/user/{id}/")
    fun getUserById(@Header("Authorization") authToken: String, @Path("id") int: Int): Call<User>
}