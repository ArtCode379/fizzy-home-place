package thefizzyrascal.household.fizzyhomeplace.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import thefizzyrascal.household.fizzyhomeplace.R
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.FOTQVOnboardingVM
import org.koin.androidx.compose.koinViewModel

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int
)

private val onboardingPagesContent = listOf<OnboardingContent>(
    OnboardingContent(
        titleRes = R.string.fotqv_page_1_title,
        descriptionRes = R.string.fotqv_page_1_description,
        imageRes = R.drawable.fotqv_ic_launcher_background,
    ),
    OnboardingContent(
        titleRes = R.string.fotqv_page_2_title,
        descriptionRes = R.string.fotqv_page_2_description,
        imageRes = R.drawable.fotqv_ic_launcher_background,
    ),
    OnboardingContent(
        titleRes = R.string.fotqv_page_2_title,
        descriptionRes = R.string.fotqv_page_3_description,
        imageRes = R.drawable.fotqv_ic_launcher_background,
    ),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: FOTQVOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }


}