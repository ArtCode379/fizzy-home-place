package thefizzyrascal.household.fizzyhomeplace.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import thefizzyrascal.household.fizzyhomeplace.data.entity.OrderEntity

@Composable
fun CheckoutDialog(order: OrderEntity, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onConfirm,
        confirmButton = { TextButton(onClick = onConfirm) { Text("View Orders") } },
        title = { Text("Reservation confirmed", style = MaterialTheme.typography.titleLarge) },
        text = {
            Column {
                Text("Order #${order.orderNumber}", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium)
                Text(order.description, modifier = Modifier.padding(top = 10.dp))
                Text(
                    "We’ll be expecting you in store within 24 hours. Please show this order number when you arrive.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 12.dp),
                )
            }
        },
    )
}
