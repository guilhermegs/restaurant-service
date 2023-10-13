package br.com.guilhermegarciadev.restaurant.domain.entity

import br.com.guilhermegarciadev.restaurant.domain.valueobject.RestaurantName
import br.com.guilhermegarciadev.restaurant.domain.valueobject.CustomerRating
import br.com.guilhermegarciadev.restaurant.domain.valueobject.Distance
import br.com.guilhermegarciadev.restaurant.domain.valueobject.Price
import br.com.guilhermegarciadev.restaurant.domain.valueobject.Cuisine

data class Restaurant(
    val name: RestaurantName,
    val customerRating: CustomerRating,
    val distance: Distance,
    val price: Price,
    val cuisine: Cuisine
): Searchable<RestaurantCriteria>, Comparable<Restaurant> {

    override fun matches(criteria: RestaurantCriteria): Boolean =
        criteria.name?.let { this.name.matches(criteria.name) } ?: true &&
                criteria.customerRating?.let { this.customerRating.matches(criteria.customerRating) } ?: true &&
                criteria.distance?.let { this.distance.matches(criteria.distance) } ?: true &&
                criteria.price?.let { this.price.matches(criteria.price) } ?: true &&
                criteria.cuisine?.let { this.cuisine.matches(criteria.cuisine.cuisineName) } ?: true

    override fun compareTo(other: Restaurant): Int = compareBy(
        Restaurant::distance,
        Restaurant::customerRating,
        Restaurant::price
    ).compare(this, other)
}
