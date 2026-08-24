package thefizzyrascal.household.fizzyhomeplace.di

import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.AppViewModel
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.CartViewModel
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.CheckoutViewModel
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.FOTQVOnboardingVM
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.OrderViewModel
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.ProductDetailsViewModel
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.ProductViewModel
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.FOTQVSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        FOTQVSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        FOTQVOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}