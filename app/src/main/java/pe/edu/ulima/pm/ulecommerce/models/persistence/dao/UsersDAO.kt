package pe.edu.ulima.pm.ulecommerce.models.persistence.dao

import androidx.room.Query
import pe.edu.ulima.pm.ulecommerce.models.persistence.entities.User

interface UsersDAO {
    @Query("SELECT * FROM User")
    fun findAll() : User
}