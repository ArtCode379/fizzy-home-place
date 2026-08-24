package thefizzyrascal.household.fizzyhomeplace.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import thefizzyrascal.household.fizzyhomeplace.ui.state.DataUiState
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var collectionAddress by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    val enabled by remember {
        derivedStateOf {
            viewModel.customerFirstName.isNotBlank() &&
                viewModel.customerLastName.isNotBlank() &&
                viewModel.customerEmail.isNotBlank() &&
                collectionAddress.isNotBlank() &&
                phoneNumber.isNotBlank()
        }
    }
    if (orderState is DataUiState.Populated) {
        CheckoutDialog(order = (orderState as DataUiState.Populated).data, onConfirm = onNavigateToOrdersScreen)
    }
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Reserve your order", style = MaterialTheme.typography.headlineMedium)
        Text("Leave your details and we’ll have everything ready for collection.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        CheckoutTextField(viewModel.customerFirstName, viewModel::updateCustomerFirstName, "First name", Modifier.fillMaxWidth())
        CheckoutTextField(viewModel.customerLastName, viewModel::updateCustomerLastName, "Last name", Modifier.fillMaxWidth())
        CheckoutTextField(collectionAddress, { collectionAddress = it }, "Address", Modifier.fillMaxWidth())
        CheckoutTextField(
            input = phoneNumber,
            onInputChange = { phoneNumber = it },
            labelText = "Phone number",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        )
        CheckoutTextField(
            input = viewModel.customerEmail,
            onInputChange = viewModel::updateCustomerEmail,
            labelText = "Email address",
            modifier = Modifier.fillMaxWidth(),
            isError = emailInvalid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
        if (emailInvalid) { Text("Enter a valid email address", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium) }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Collection promise", style = MaterialTheme.typography.titleMedium)
                Text("Your reservation will be held in store for 24 hours after confirmation.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 6.dp))
            }
        }
        Button(onClick = viewModel::placeOrder, enabled = enabled, modifier = Modifier.fillMaxWidth().height(54.dp)) { Text("Place Reservation") }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        label = { Text(labelText) },
        modifier = modifier,
        enabled = enabled,
        isError = isError,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}
