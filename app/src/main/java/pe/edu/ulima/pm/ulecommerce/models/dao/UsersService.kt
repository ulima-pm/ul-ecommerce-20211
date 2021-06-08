package pe.edu.ulima.pm.ulecommerce.models.dao

import pe.edu.ulima.pm.ulecommerce.models.beans.LoginRequest
import pe.edu.ulima.pm.ulecommerce.models.beans.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersService {
    @POST("/users")
    fun login(@Body data : LoginRequest) : Call<LoginResponse>
}