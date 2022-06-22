package aliesia.lykhova.nure.shelter.api.objects

import aliesia.lykhova.nure.shelter.api.RetrofitClient
import aliesia.lykhova.nure.shelter.api.interfaces.Registration

object Registration {
    private val LOGIN_URL = "http://192.168.1.15/api/en/register/"

    val retrofitService: Registration
        get () = RetrofitClient.getClient(LOGIN_URL).create(Registration::class.java)
}