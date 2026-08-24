package thefizzyrascal.household.fizzyhomeplace.ui.composable.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import thefizzyrascal.household.fizzyhomeplace.R
import thefizzyrascal.household.fizzyhomeplace.data.model.Product
import thefizzyrascal.household.fizzyhomeplace.data.model.ProductCategory
import thefizzyrascal.household.fizzyhomeplace.ui.composable.shared.FOTQVContentWrapper
import thefizzyrascal.household.fizzyhomeplace.ui.composable.shared.FOTQVEmptyView
import thefizzyrascal.household.fizzyhomeplace.ui.state.DataUiState
import thefizzyrascal.household.fizzyhomeplace.ui.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    var category by remember { mutableStateOf<ProductCategory?>(null) }

    FOTQVContentWrapper(
        dataState = state,
        dataPopulated = {
            val products = (state as DataUiState.Populated).data
            val filtered = category?.let { selected -> products.filter { it.category == selected } } ?: products
            Column(modifier = modifier.fillMaxSize()) {
                FeaturedProduct(products.first(), onNavigateToProductDetails)
                Text(
                    text = "Shop the collection",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 20.dp, top = 22.dp, end = 20.dp),
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item {
                        FilterChip(selected = category == null, onClick = { category = null }, label = { Text("All") })
                    }
                    items(ProductCategory.entries) { item ->
                        FilterChip(
                            selected = category == item,
                            onClick = { category = item },
                            label = { Text(stringResource(item.titleRes)) },
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                        )
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(filtered, key = { it.id }) { product ->
                        ProductCard(product, onNavigateToProductDetails, viewModel::addToCart)
                    }
                }
            }
        },
        dataEmpty = {
            FOTQVEmptyView(primaryText = stringResource(R.string.fotqv_products_state_empty_primary_text), modifier = Modifier.fillMaxSize())
        },
    )
}

@Composable
private fun FeaturedProduct(product: Product, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clickable { onClick(product.id) },
    ) {
        AsyncImage(model = product.imageUrl, contentDescription = product.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)))),
        )
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(22.dp)) {
            Text("THE EDIT", style = MaterialTheme.typography.labelMedium, color = Color.White)
            Text(product.title, style = MaterialTheme.typography.headlineMedium, color = Color.White, modifier = Modifier.padding(top = 5.dp))
            Text("£%.2f".format(product.price), style = MaterialTheme.typography.titleMedium, color = Color.White, modifier = Modifier.padding(top = 6.dp))
        }
    }
}

@Composable
private fun ProductCard(product: Product, onClick: (Int) -> Unit, onAdd: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(product.id) },
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(if (product.id % 2 == 0) 190.dp else 150.dp)
                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.padding(12.dp)) {
            Text(stringResource(product.category.titleRes).uppercase(), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
            Text(product.title, style = MaterialTheme.typography.titleMedium, maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 5.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text("£%.2f".format(product.price), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                IconButton(onClick = { onAdd(product.id) }) {
                    Icon(Icons.Outlined.AddShoppingCart, contentDescription = "Add ${product.title} to cart", tint = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
