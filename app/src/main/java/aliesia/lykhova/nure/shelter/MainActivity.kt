package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.api.objects.Login
import aliesia.lykhova.nure.shelter.data.user.UserLoginForm
import aliesia.lykhova.nure.shelter.data.user.UserLoginResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val intent = Intent(this, MapsActivity::class.java)
//        startActivity(intent)
    }

    fun registration(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private var authToken: String? = null
        private var shelterId: Int? = null
        private var userId: Int? = null

        fun setAuthToken(value: String) {
            authToken = value
        }
        fun getAuthToken(): String? = this.authToken

        fun setShelterId(value: Int) {
            shelterId = value
        }
        fun getShelterId(): Int? = this.shelterId

        fun setUserId(value: Int) {
            userId = value
        }
        fun getUserId(): Int? = this.userId
    }

    fun login(view: View) {
        val email: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById<EditText>(R.id.editTextTextPassword)

        val user = UserLoginForm(
            email.text.toString(),
            password.text.toString()
        )
        val adminIntent = Intent(this, AdminBottomNavigationActivity::class.java)
        val userIntent = Intent(this, MapsActivity::class.java)
        val apiService = Login.retrofitService

        apiService.login(user).enqueue(object : Callback<UserLoginResponse> {
            override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<UserLoginResponse>,
                response: Response<UserLoginResponse>
            ) {
                println(response.body())
                println(response.code())

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    setAuthToken("Bearer " + response.body()?.token)
                    setUserId(response.body()?.userId!!)

                    when (response.body()?.role) {
                        false -> startActivity(userIntent)
                        true -> {
                            setShelterId(response.body()?.shelter_id?.id!!)
                            startActivity(adminIntent)
                        }
                        null -> println("ne pravilno")
                    }
                } else {
                    Toast.makeText(view.context, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}