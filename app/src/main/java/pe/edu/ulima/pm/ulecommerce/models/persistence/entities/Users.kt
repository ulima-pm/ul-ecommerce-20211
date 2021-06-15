package pe.edu.ulima.pm.ulecommerce.models.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "username") val username : String,
    @ColumnInfo(name = "password") val password: String
)