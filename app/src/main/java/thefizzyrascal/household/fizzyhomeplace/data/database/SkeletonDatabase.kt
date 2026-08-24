package thefizzyrascal.household.fizzyhomeplace.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import thefizzyrascal.household.fizzyhomeplace.data.dao.CartItemDao
import thefizzyrascal.household.fizzyhomeplace.data.dao.OrderDao
import thefizzyrascal.household.fizzyhomeplace.data.database.converter.Converters
import thefizzyrascal.household.fizzyhomeplace.data.entity.CartItemEntity
import thefizzyrascal.household.fizzyhomeplace.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FOTQVDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}