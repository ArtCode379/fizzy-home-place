package thefizzyrascal.household.fizzyhomeplace.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import thefizzyrascal.household.fizzyhomeplace.R
import thefizzyrascal.household.fizzyhomeplace.data.entity.OrderEntity
import thefizzyrascal.household.fizzyhomeplace.ui.composable.shared.FOTQVContentWrapper
import thefizzyrascal.household.fizzyhomeplace.ui.composable.shared.FOTQVEmptyView
import thefizzyrascal.household.fizzyhomeplace.ui.state.DataUiState
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        FOTQVContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                FOTQVEmptyView(
                    primaryText = stringResource(R.string.fotqv_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}