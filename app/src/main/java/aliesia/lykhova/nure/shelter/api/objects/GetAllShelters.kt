package aliesia.lykhova.nure.shelter.api.objects

import aliesia.lykhova.nure.shelter.api.RetrofitClient
import aliesia.lykhova.nure.shelter.api.interfaces.GetAllShelters

object GetAllShelters {
    private val GET_URL = "http://192.168.1.15/api/en/shelter/"

    val sheltersRetrofitService: GetAllShelters
        get() = RetrofitClient.getClient(GET_URL).create(GetAllShelters::class.java)
}