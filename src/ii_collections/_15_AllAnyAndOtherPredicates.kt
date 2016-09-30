package ii_collections

fun example2(list: List<Int>) {

    val isZero: (Int) -> Boolean = { it == 0 }

    val hasZero: Boolean = list.any(isZero)

    val allZeros: Boolean = list.all(isZero)

    val numberOfZeros: Int = list.count(isZero)

    val firstPositiveNumber: Int? = list.firstOrNull { it > 0 }
}

fun Customer.isFrom(city: City): Boolean {
    return this.city.equals(city)
}

fun Shop.checkAllCustomersAreFrom(city: City): Boolean {
    return getSetOfCustomers().all { it.isFrom(city) }
}

fun Shop.hasCustomerFrom(city: City): Boolean {
    return getSetOfCustomers().any { it.isFrom(city) }
}

fun Shop.countCustomersFrom(city: City): Int {
    return getSetOfCustomers().count { it.isFrom(city) }
}

fun Shop.findAnyCustomerFrom(city: City): Customer? {
    return getSetOfCustomers().find { it.isFrom(city) }
}
