package pe.edu.ulima.pm.ulecommerce.models.dao

import pe.edu.ulima.pm.ulecommerce.models.beans.Product
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList

//https://60b83e68b54b0a0017c03380.mockapi.io/devices

interface DevicesService {
    @GET("/devices")
    fun getDevices() : Call<ArrayList<Product>>
}