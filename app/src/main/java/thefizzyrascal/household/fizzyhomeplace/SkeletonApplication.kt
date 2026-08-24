package thefizzyrascal.household.fizzyhomeplace

import android.app.Application
//[ANY][import_PrepRepository]
import thefizzyrascal.household.fizzyhomeplace.di.dataModule
import thefizzyrascal.household.fizzyhomeplace.di.dispatcherModule
import thefizzyrascal.household.fizzyhomeplace.di.viewModule
//[COMMON][import_DiModule]
//[REFERRER][import_InstallReferrerManager]
//[APPSFLYER][imports_AppsFlyer]
//[FIREBASE][import_FirebaseMessaging]
//[FIREBASE][imports_coroutines]
//[ANY][import_getKoin]
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FOTQVApplication : Application() {
    //[FIREBASE][appScope]

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule /*[COMMON][diModule]*/

        startKoin {
            androidLogger()
            androidContext(this@FOTQVApplication)
            modules(appModules)
        }

        //[ANY][repository]

        //[APPSFLYER][devKey]

        //[APPSFLYER][appsFlyerSettings]

        //[REFERRER][referrerManagerSettings]

        //[APPSFLYER][appsFlyerId]

        //[FIREBASE][FirebaseMessaging]
    }
}
