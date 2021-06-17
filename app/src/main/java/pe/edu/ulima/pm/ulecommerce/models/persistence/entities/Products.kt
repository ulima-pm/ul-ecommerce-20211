package pe.edu.ulima.pm.ulecommerce.models.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey() val id : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "price") val price : Float,
    @ColumnInfo(name = "url") val url: String
)