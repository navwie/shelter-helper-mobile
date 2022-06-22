package aliesia.lykhova.nure.shelter.api.objects

import aliesia.lykhova.nure.shelter.api.RetrofitClient
import aliesia.lykhova.nure.shelter.api.interfaces.Login

object Login {
    private val LOGIN_URL = "http://192.168.1.15/api/en/login/"

    val retrofitService: Login
        get () = RetrofitClient.getClient(LOGIN_URL).create(Login::class.java)
}