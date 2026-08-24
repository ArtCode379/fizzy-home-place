package thefizzyrascal.household.fizzyhomeplace.di

import androidx.room.Room
import thefizzyrascal.household.fizzyhomeplace.data.database.FOTQVDatabase
import org.koin.dsl.module

private const val DB_NAME = "fotqv_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = FOTQVDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<FOTQVDatabase>().cartItemDao() }

    single { get<FOTQVDatabase>().orderDao() }
}