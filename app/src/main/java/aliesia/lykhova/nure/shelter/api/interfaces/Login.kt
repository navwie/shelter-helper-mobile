package aliesia.lykhova.nure.shelter.api.interfaces

import aliesia.lykhova.nure.shelter.data.user.UserLoginForm
import aliesia.lykhova.nure.shelter.data.user.UserLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("http://192.168.1.15/api/en/login/")
    fun login(@Body requestBody: UserLoginForm): Call<UserLoginResponse>;
}