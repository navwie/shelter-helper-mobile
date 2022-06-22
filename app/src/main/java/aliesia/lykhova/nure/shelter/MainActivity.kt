package aliesia.lykhova.nure.shelter

import aliesia.lykhova.nure.shelter.api.objects.Login
import aliesia.lykhova.nure.shelter.data.user.UserLoginForm
import aliesia.lykhova.nure.shelter.data.user.UserLoginResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
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

        fun setAuthToken(value: String){
            authToken = value
        }
        fun getAuthToken(): String? = this.authToken
    }

    fun login(view: View) {
        val email: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById<EditText>(R.id.editTextTextPassword)

        val user = UserLoginForm(
            email.text.toString(),
            password.text.toString()
        )
        val adminIntent = Intent(this, MapsActivity::class.java)
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

                    when (response.body()?.role) {
                        false -> startActivity(userIntent)
                        true -> startActivity(adminIntent)
                        null -> println("ne pravilno")
                    }
                }
            }
        })
    }
}