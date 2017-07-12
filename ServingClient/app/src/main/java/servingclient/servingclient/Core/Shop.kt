package servingclient.servingclient.Core




class Shop(private val id: Long, private val sectorId: Long,
           private var ordersList: ArrayList<Order>?, private var servingUserList: ArrayList<ServingUser>?) {
    constructor() : this(-1, -1, null, null) {

    }

    fun getOrders(): ArrayList<Order>? = ordersList

    fun orderIsMade(order: Order) {
        order.isMade = true
    }

    fun orderIsDelivered(order: Order) {
        order.isDelivered = true
    }

    fun addOrder(order: Order) {
        ordersList?.add(order)
    }

    fun removeOrder(order: Order) {
        ordersList?.remove(order)
    }

    fun removeFromOrder(order: Order, product: Product) {
        val value = order.productList.getValue(product)
        order.productList.remove(product)
        order.productList.put(product, value - 1)
    }

    fun checkServingUser(servingUser: ServingUser): Boolean = servingUserList?.contains(servingUser)!!

    fun addServingUser(servingUser: ServingUser) {
        if (!servingUserList?.contains(servingUser)!!) servingUserList?.add(servingUser)
    }

}
class ServingUser(val id: Long, val login: String, val password: String) {
    fun connectToShop(shop: Shop) {
        shop.addServingUser(this)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val otherServingUser = other as ServingUser
        return otherServingUser.id == this.id &&
                otherServingUser.login == this.login &&
                otherServingUser.password == this.password
    }
}

data class Order(val id: Long, var productList: HashMap<Product, Int>,
                 var isDelivered: Boolean = false,
                 var isMade: Boolean = false)

data class Product(val productType: ProductType, private val price: Double)