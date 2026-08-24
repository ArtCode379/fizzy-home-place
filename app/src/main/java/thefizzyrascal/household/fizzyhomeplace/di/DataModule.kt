package thefizzyrascal.household.fizzyhomeplace.di

import thefizzyrascal.household.fizzyhomeplace.data.repository.CartRepository
import thefizzyrascal.household.fizzyhomeplace.data.repository.FOTQVOnboardingRepo
import thefizzyrascal.household.fizzyhomeplace.data.repository.OrderRepository
import thefizzyrascal.household.fizzyhomeplace.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        FOTQVOnboardingRepo(
            fotqvOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}