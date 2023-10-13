package br.com.guilhermegarciadev.restaurant.domain.repository

import br.com.guilhermegarciadev.restaurant.domain.entity.Restaurant
import br.com.guilhermegarciadev.restaurant.domain.entity.RestaurantCriteria

interface RestaurantRepository {
    fun search(criteria: RestaurantCriteria): List<Restaurant>
}
