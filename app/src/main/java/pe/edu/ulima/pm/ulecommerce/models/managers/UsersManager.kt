package pe.edu.ulima.pm.ulecommerce.models.managers

import pe.edu.ulima.pm.ulecommerce.models.beans.LoginRequest
import pe.edu.ulima.pm.ulecommerce.models.beans.LoginResponse
import pe.edu.ulima.pm.ulecommerce.models.dao.UsersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

// Singleton
class UsersManager {
    companion object {
        private var instance : UsersManager? = null

        fun getInstance() : UsersManager {
            if (instance == null) {
                instance = UsersManager()
            }
            return instance!!
        }
    }

    fun login(callbackSuccess : (username : String, name: String) -> Unit,
              callBackError: (error : String) -> Unit,
              username : String,
              password: String) {

        val retrofit = ConnectionManager.getInstance().getRetrofit()
        val usersService = retrofit.create<UsersService>()

        usersService.login(LoginRequest(username, password)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.body() != null) {
                    callbackSuccess(response.body()!!.username, response.body()!!.name)
                }else {
                    callBackError("LoginResponse nulo")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callBackError(t.message!!)
            }

        })

    }
}