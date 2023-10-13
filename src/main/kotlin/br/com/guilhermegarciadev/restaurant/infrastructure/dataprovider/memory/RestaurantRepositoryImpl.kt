package br.com.guilhermegarciadev.restaurant.infrastructure.dataprovider.memory

import br.com.guilhermegarciadev.restaurant.domain.entity.Restaurant
import br.com.guilhermegarciadev.restaurant.domain.entity.RestaurantCriteria
import br.com.guilhermegarciadev.restaurant.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(
    private val restaurantsList: List<Restaurant>
): RestaurantRepository {

    override fun search(criteria: RestaurantCriteria): List<Restaurant> {
        return restaurantsList.filter { it.matches(criteria) }
    }
}
