package thefizzyrascal.household.fizzyhomeplace.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import thefizzyrascal.household.fizzyhomeplace.R
import thefizzyrascal.household.fizzyhomeplace.ui.composable.shared.FOTQVContentWrapper
import thefizzyrascal.household.fizzyhomeplace.ui.composable.shared.FOTQVEmptyView
import thefizzyrascal.household.fizzyhomeplace.ui.state.CartItemUiState
import thefizzyrascal.household.fizzyhomeplace.ui.state.DataUiState
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()

    FOTQVContentWrapper(
        dataState = state,
        dataPopulated = {
            val items = (state as DataUiState.Populated).data
            Column(modifier = modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    item { Text("Your selected pieces", style = MaterialTheme.typography.headlineMedium) }
                    items(items, key = { it.productId }) { item ->
                        CartItem(
                            item = item,
                            onPlus = { viewModel.incrementProductInCart(item.productId) },
                            onMinus = {
                                if (item.quantity == 1) {
                                    viewModel.deleteFromCart(item.productId)
                                } else {
                                    viewModel.decrementItemInCart(item.productId)
                                }
                            },
                            onDelete = { viewModel.deleteFromCart(item.productId) },
                        )
                    }
                }
                Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Total", style = MaterialTheme.typography.titleLarge)
                        Text("£%.2f".format(total), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                    }
                    Button(onClick = onNavigateToCheckoutScreen, modifier = Modifier.fillMaxWidth().padding(top = 16.dp).height(52.dp)) {
                        Text("Proceed to Checkout")
                    }
                }
            }
        },
        dataEmpty = {
            Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("Your home edit starts here", style = MaterialTheme.typography.headlineMedium)
                Text("Add a few beautiful, useful pieces from the shop.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(12.dp))
                Text("Start Shopping", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
            }
        },
    )
}

@Composable
private fun CartItem(item: CartItemUiState, onPlus: () -> Unit, onMinus: () -> Unit, onDelete: () -> Unit) {
    Card(shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = item.productImageUrl, contentDescription = item.productTitle, contentScale = ContentScale.Crop, modifier = Modifier.size(72.dp))
            Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                Text("£%.2f".format(item.productPrice), color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 5.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = onMinus, contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp), modifier = Modifier.size(34.dp)) { Text("−") }
                    Text(item.quantity.toString())
                    OutlinedButton(onClick = onPlus, contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp), modifier = Modifier.size(34.dp)) { Text("+") }
                }
            }
            IconButton(onClick = onDelete) { Icon(Icons.Outlined.Delete, contentDescription = stringResource(R.string.fotqv_delete_item_icon_description)) }
        }
    }
}
