package br.com.guilhermegarciadev.restaurant.domain.entity

import br.com.guilhermegarciadev.restaurant.domain.valueobject.Cuisine
import java.math.BigDecimal

data class RestaurantCriteria(
    val name: String?,
    val customerRating: Int?,
    val distance: Int?,
    val price: BigDecimal?,
    val cuisine: Cuisine?
)
