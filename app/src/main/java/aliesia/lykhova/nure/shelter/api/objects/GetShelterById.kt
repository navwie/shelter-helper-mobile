package aliesia.lykhova.nure.shelter.api.objects

import aliesia.lykhova.nure.shelter.api.RetrofitClient
import aliesia.lykhova.nure.shelter.api.interfaces.GetShelterById

object GetShelterById {
    private val GET_URL = "http://192.168.1.15/api/en/shelter/{id}/"

    val retrofitService: GetShelterById
        get() = RetrofitClient.getClient(GET_URL).create(GetShelterById::class.java)
}