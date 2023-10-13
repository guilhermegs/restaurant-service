package br.com.guilhermegarciadev.restaurant.domain.factory

import br.com.guilhermegarciadev.restaurant.domain.entity.Restaurant
import br.com.guilhermegarciadev.restaurant.domain.entity.RestaurantCriteria
import br.com.guilhermegarciadev.restaurant.domain.valueobject.RestaurantName
import br.com.guilhermegarciadev.restaurant.domain.valueobject.CustomerRating
import br.com.guilhermegarciadev.restaurant.domain.valueobject.Distance
import br.com.guilhermegarciadev.restaurant.domain.valueobject.Price
import br.com.guilhermegarciadev.restaurant.domain.valueobject.Cuisine
import br.com.guilhermegarciadev.restaurant.usecase.search.dto.InputRestaurantDto
import br.com.guilhermegarciadev.restaurant.usecase.search.exception.BadRequestException
import java.math.BigDecimal

class RestaurantFactory private constructor() {
    companion object {
        fun createRestaurant(inputName: String, inputCustomerRating: Int, inputDistance: Int,
                             inputPrice: BigDecimal, inputCuisine: Int) =
            Restaurant(
                RestaurantName(inputName),
                CustomerRating(inputCustomerRating),
                Distance(inputDistance),
                Price(inputPrice),
                Cuisine.byId(inputCuisine) ?: throw BadRequestException("Cuisine id is invalid")
            )

        fun createRestaurant(inputName: String, inputCustomerRating: Int, inputDistance: Int,
                             inputPrice: BigDecimal, inputCuisine: String) =
            Restaurant(
                RestaurantName(inputName),
                CustomerRating(inputCustomerRating),
                Distance(inputDistance),
                Price(inputPrice),
                Cuisine.valueOf(inputCuisine.uppercase())
            )

        fun createRestaurantCriteria(inputName: String?, inputCustomerRating: Int?, inputDistance: Int?,
                                     inputPrice: BigDecimal?, inputCuisine: String?) =
            RestaurantCriteria(
                inputName ,
                inputCustomerRating,
                inputDistance,
                inputPrice,
                inputCuisine?.let { Cuisine.valueOf(inputCuisine.uppercase()) }
            )

        fun createRestaurantCriteria(input: InputRestaurantDto) =
            createRestaurantCriteria(
                input.name,
                input.customerRating,
                input.distance,
                input.price,
                input.cuisine
            )
    }
}
