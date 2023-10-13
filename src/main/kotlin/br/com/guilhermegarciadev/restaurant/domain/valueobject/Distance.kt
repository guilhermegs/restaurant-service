package br.com.guilhermegarciadev.restaurant.domain.valueobject

import br.com.guilhermegarciadev.restaurant.domain.entity.Searchable

class Distance(
    val value: Int
): Searchable<Int>, Comparable<Distance> {
    override fun matches(criteria: Int): Boolean = this.value <= criteria
    override fun compareTo(other: Distance): Int = this.value compareTo other.value
}
