package pe.edu.ulima.pm.ulecommerce.models.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.ulima.pm.ulecommerce.models.persistence.entities.User

@Dao
interface UsersDAO {
    @Query("SELECT * FROM User")
    fun findAll() : List<User>

    @Insert
    fun insert(user : User)

    @Delete
    fun delete(user : User)
}