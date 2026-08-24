package thefizzyrascal.household.fizzyhomeplace.data.repository

import thefizzyrascal.household.fizzyhomeplace.data.datastore.FOTQVOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FOTQVOnboardingRepo(
    private val fotqvOnboardingStoreManager: FOTQVOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return fotqvOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            fotqvOnboardingStoreManager.setOnboardedState(state)
        }
    }
}