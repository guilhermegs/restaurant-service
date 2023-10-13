package br.com.guilhermegarciadev.restaurant.domain.valueobject

import br.com.guilhermegarciadev.restaurant.domain.entity.Searchable

class CustomerRating(
    val value: Int
): Searchable<Int>, Comparable<CustomerRating> {
    override fun matches(criteria: Int): Boolean = this.value >= criteria
    override fun compareTo(other: CustomerRating): Int = other.value compareTo this.value
}
