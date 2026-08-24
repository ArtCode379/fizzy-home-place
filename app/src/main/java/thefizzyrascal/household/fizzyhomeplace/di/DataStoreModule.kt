package thefizzyrascal.household.fizzyhomeplace.di

import thefizzyrascal.household.fizzyhomeplace.data.datastore.FOTQVOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { FOTQVOnboardingPrefs(androidContext()) }
}