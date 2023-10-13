package br.com.guilhermegarciadev.restaurant.infrastructure.entrypoint.rest.dto

import br.com.guilhermegarciadev.restaurant.domain.entity.Restaurant
import com.fasterxml.jackson.annotation.JsonProperty

class OutputRestaurantDto(
    @JsonProperty("name") val name: String
) {
    companion object {
        fun from(restaurants: List<Restaurant>): List<OutputRestaurantDto> {
            return restaurants.map {
                OutputRestaurantDto(it.name.value)
            }
        }
    }
}