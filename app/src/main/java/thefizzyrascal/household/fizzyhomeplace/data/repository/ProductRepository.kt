package thefizzyrascal.household.fizzyhomeplace.data.repository

import thefizzyrascal.household.fizzyhomeplace.data.model.Product
import thefizzyrascal.household.fizzyhomeplace.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products = listOf(
        Product(
            1,
            "Artisan Stoneware Dinner Set",
            "A tactile 12-piece stoneware set with softly irregular rims and a warm speckled glaze. Made for relaxed breakfasts, shared suppers, and everyday rituals.",
            ProductCategory.KITCHEN,
            78.00,
            "https://images.unsplash.com/photo-1610701596007-11502861dcfa?w=1200",
        ),
        Product(
            2,
            "Woven Linen Throw",
            "A breathable linen-cotton throw with hand-finished tassels. Its natural oat tone layers easily over a sofa, reading chair, or the end of a bed.",
            ProductCategory.TEXTILES,
            46.00,
            "https://images.unsplash.com/photo-1583845112203-29329902332e?w=1200",
        ),
        Product(
            3,
            "Sage Ceramic Vase",
            "A sculptural ceramic vase in a calming sage glaze. Display it alone or pair it with garden stems for an effortless seasonal arrangement.",
            ProductCategory.DECOR,
            34.00,
            "https://images.unsplash.com/photo-1618220179428-22790b461013?w=1200",
        ),
        Product(
            4,
            "Acacia Serving Board",
            "A generously sized acacia board with a softly rounded handle. Ideal for bread, cheese, antipasti, or a welcoming centrepiece.",
            ProductCategory.KITCHEN,
            29.50,
            "https://images.unsplash.com/photo-1556911220-bff31c812dba?w=1200",
        ),
        Product(
            5,
            "Cotton Waffle Towels",
            "A set of two absorbent waffle-weave towels in a gentle clay shade. Lightweight, quick drying, and easy to hang from the woven loop.",
            ProductCategory.TEXTILES,
            24.00,
            "https://images.unsplash.com/photo-1604014237800-1c9102c219da?w=1200",
        ),
        Product(
            6,
            "Rattan Storage Basket",
            "A sturdy handwoven rattan basket that keeps blankets, toys, or laundry beautifully contained while adding natural texture to your room.",
            ProductCategory.STORAGE,
            42.00,
            "https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?w=1200",
        ),
        Product(
            7,
            "Amber Glass Candle",
            "A warm cedar, fig, and bergamot candle poured into reusable amber glass. Burn time approximately 45 hours.",
            ProductCategory.DECOR,
            22.00,
            "https://images.unsplash.com/photo-1603006905003-be475563bc59?w=1200",
        ),
        Product(
            8,
            "Beech Dish Brush Set",
            "Two replaceable-head beech brushes with plant-based bristles. A practical, plastic-light upgrade for daily washing up.",
            ProductCategory.CLEANING,
            16.50,
            "https://images.unsplash.com/photo-1585421514284-efb74c2b69ba?w=1200",
        ),
        Product(
            9,
            "Fluted Glass Tumblers",
            "Four stackable tumblers made from durable fluted glass. Their light-catching texture suits water, juice, or evening cocktails.",
            ProductCategory.KITCHEN,
            26.00,
            "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=1200",
        ),
        Product(
            10,
            "Boucle Cushion",
            "A plump feather-filled cushion in tactile ivory boucle, finished with a concealed zip for a clean and inviting look.",
            ProductCategory.TEXTILES,
            32.00,
            "https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=1200",
        ),
        Product(
            11,
            "Oak Peg Rail",
            "A simple solid-oak rail with five turned pegs for coats, baskets, linens, or kitchen tools. Fixings are included.",
            ProductCategory.STORAGE,
            38.00,
            "https://images.unsplash.com/photo-1618221195710-dd6b41faaea6?w=1200",
        ),
        Product(
            12,
            "Botanical Cleaning Kit",
            "A concentrated multi-surface cleaner with a refillable amber bottle and washable cloth, scented naturally with rosemary and citrus.",
            ProductCategory.CLEANING,
            19.00,
            "https://images.unsplash.com/photo-1563453392212-326f5e854473?w=1200",
        ),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
