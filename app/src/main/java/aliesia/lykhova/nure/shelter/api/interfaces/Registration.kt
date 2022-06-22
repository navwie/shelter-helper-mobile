package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.user.UserRegisterForm
import aliesia.lykhova.nure.shelter.data.user.UserRegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Registration {
    @POST("http://192.168.1.15/api/en/register/")
    fun register(@Body requestBody: UserRegisterForm): Call<UserRegisterResponse>;
}