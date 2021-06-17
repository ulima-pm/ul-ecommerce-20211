package pe.edu.ulima.pm.ulecommerce.models.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pe.edu.ulima.pm.ulecommerce.models.persistence.entities.Product

@Dao
interface ProductsDAO {
    @Query("SELECT * FROM Product")
    fun findAll() : List<Product>
    @Insert
    fun insert(product : Product)
}