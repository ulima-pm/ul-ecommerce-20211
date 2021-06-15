package pe.edu.ulima.pm.ulecommerce.models.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.ulima.pm.ulecommerce.models.persistence.dao.UsersDAO
import pe.edu.ulima.pm.ulecommerce.models.persistence.entities.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO() : UsersDAO
}