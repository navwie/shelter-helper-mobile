package aliesia.lykhova.nure.shelter.api.objects

import aliesia.lykhova.nure.shelter.api.RetrofitClient
import aliesia.lykhova.nure.shelter.api.interfaces.UpdateUser

object UpdateUser {
    private val GET_URL = "http://192.168.1.15/api/en/user/{id}/"

    val retrofitService: UpdateUser
        get() = RetrofitClient.getClient(GET_URL).create(UpdateUser::class.java)
}