package pe.edu.ulima.pm.ulecommerce.models.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.ulima.pm.ulecommerce.models.persistence.dao.ProductsDAO
import pe.edu.ulima.pm.ulecommerce.models.persistence.dao.UsersDAO
import pe.edu.ulima.pm.ulecommerce.models.persistence.entities.Product
import pe.edu.ulima.pm.ulecommerce.models.persistence.entities.User

@Database(entities = arrayOf(User::class, Product::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO() : UsersDAO
    abstract fun productDAO() : ProductsDAO
}