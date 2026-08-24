package thefizzyrascal.household.fizzyhomeplace.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val FizzyColors = lightColorScheme(
    primary = Terracotta,
    onPrimary = WarmWhite,
    secondary = Sage,
    onSecondary = WarmWhite,
    tertiary = Golden,
    background = Cream,
    onBackground = Charcoal,
    surface = WarmWhite,
    onSurface = Charcoal,
    surfaceVariant = SoftClay,
    onSurfaceVariant = MutedBrown,
    outline = SandBorder,
    error = DeepTerracotta,
)

@Composable
fun ProductAppFOTQVTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = FizzyColors,
        typography = AppTypography,
        content = content,
    )
}
