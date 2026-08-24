package thefizzyrascal.household.fizzyhomeplace.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import thefizzyrascal.household.fizzyhomeplace.data.entity.OrderEntity
import thefizzyrascal.household.fizzyhomeplace.ui.composable.shared.FOTQVContentWrapper
import thefizzyrascal.household.fizzyhomeplace.ui.state.DataUiState
import thefizzyrascal.household.fizzyhomeplace.ui.theme.DeepSage
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.OrderViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    FOTQVContentWrapper(
        dataState = state,
        dataPopulated = {
            val orders = (state as DataUiState.Populated).data.sortedByDescending { it.timestamp }
            LazyColumn(modifier = modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                item { Text("Your reservations", style = MaterialTheme.typography.headlineMedium) }
                item { Text("Keep your collection numbers handy.", color = MaterialTheme.colorScheme.onSurfaceVariant) }
                items(orders, key = { it.orderNumber }) { OrderCard(it) }
            }
        },
        dataEmpty = {
            Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("No orders yet", style = MaterialTheme.typography.headlineMedium)
                Text("Your reservations will appear here.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 8.dp))
            }
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), shape = RoundedCornerShape(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("#${order.orderNumber}", style = MaterialTheme.typography.titleMedium)
                Surface(color = DeepSage.copy(alpha = 0.12f), shape = RoundedCornerShape(50)) {
                    Text("Reserved", color = DeepSage, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
                }
            }
            Text(
                order.timestamp.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp),
            )
            Text(order.description, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 10.dp))
            Text("£%.2f".format(order.price), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 12.dp))
            Text("Collect within 24 hours", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(top = 5.dp))
        }
    }
}
