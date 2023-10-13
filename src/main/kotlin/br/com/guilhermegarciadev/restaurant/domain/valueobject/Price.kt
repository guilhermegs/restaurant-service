package br.com.guilhermegarciadev.restaurant.domain.valueobject

import br.com.guilhermegarciadev.restaurant.domain.entity.Searchable
import java.math.BigDecimal

class Price(
    val value: BigDecimal
): Searchable<BigDecimal>, Comparable<Price> {
    override fun matches(criteria: BigDecimal): Boolean = this.value <= criteria
    override fun compareTo(other: Price): Int = this.value compareTo other.value
}
