package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.user.UpdateUserForm
import aliesia.lykhova.nure.shelter.data.user.UpdateUserResponse
import aliesia.lykhova.nure.shelter.data.user.User
import retrofit2.Call
import retrofit2.http.*

interface UpdateUser {
    @PUT("http://192.168.1.15/api/en/user/{id}/")
    fun updateUser(@Header("Authorization") authToken: String, @Path("id") int: Int, @Body requestBody: UpdateUserForm): Call<UpdateUserResponse>
}