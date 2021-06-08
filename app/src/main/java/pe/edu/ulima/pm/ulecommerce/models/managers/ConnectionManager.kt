package pe.edu.ulima.pm.ulecommerce.models.managers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConnectionManager {
    companion object {
        private var instance : ConnectionManager? = null

        fun getInstance() : ConnectionManager {
            if (instance == null) {
                instance = ConnectionManager()
            }
            return instance!!
        }
    }

    private var retrofit : Retrofit? = null

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://60b83e68b54b0a0017c03380.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofit() : Retrofit{
        return retrofit!!
    }
}