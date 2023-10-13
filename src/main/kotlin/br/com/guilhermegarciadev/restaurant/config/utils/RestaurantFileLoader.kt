package br.com.guilhermegarciadev.restaurant.config.utils

import br.com.guilhermegarciadev.restaurant.domain.entity.Restaurant
import br.com.guilhermegarciadev.restaurant.domain.factory.RestaurantFactory
import org.springframework.core.io.ClassPathResource
import java.math.BigDecimal

class RestaurantFileLoader private constructor() {
    companion object {
        fun load() : List<Restaurant> {
            val reader = ClassPathResource("restaurants.csv").inputStream.bufferedReader()
            reader.readLine() // read header
            return reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val (name, customerRating, distance, price, cuisineId) = it.split(
                        ',',
                        ignoreCase = false,
                        limit = 5
                    )
                    RestaurantFactory.createRestaurant(
                        name,
                        customerRating.toInt(),
                        distance.toInt(),
                        BigDecimal(price),
                        cuisineId.toInt()
                    )
                }.toList()
        }
    }
}