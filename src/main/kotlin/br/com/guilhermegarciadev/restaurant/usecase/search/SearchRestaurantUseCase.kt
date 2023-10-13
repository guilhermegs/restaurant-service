package br.com.guilhermegarciadev.restaurant.usecase.search

import br.com.guilhermegarciadev.restaurant.domain.entity.Restaurant
import br.com.guilhermegarciadev.restaurant.domain.factory.RestaurantFactory
import br.com.guilhermegarciadev.restaurant.domain.repository.RestaurantRepository
import br.com.guilhermegarciadev.restaurant.usecase.search.dto.InputRestaurantDto

class SearchRestaurantUseCase(
    private val restaurantRepository: RestaurantRepository
) {
    fun execute(input: InputRestaurantDto): List<Restaurant> {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            input.name,
            input.customerRating,
            input.distance,
            input.price,
            input.cuisine
        )
        val restaurants = this.restaurantRepository.search(criteria)
        return restaurants
            .sorted()
            .take(5)
    }
}
