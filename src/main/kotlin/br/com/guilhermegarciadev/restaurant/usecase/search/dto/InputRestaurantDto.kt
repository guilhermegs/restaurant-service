package br.com.guilhermegarciadev.restaurant.usecase.search.dto

import br.com.guilhermegarciadev.restaurant.domain.valueobject.Cuisine
import br.com.guilhermegarciadev.restaurant.usecase.search.exception.BadRequestException
import java.math.BigDecimal

class InputRestaurantDto(
    name: String? = null,
    customerRating: String? = null,
    distance: String? = null,
    price: String? = null,
    cuisine: String? = null
) {
    val name: String?
    val customerRating: Int?
    val distance: Int?
    val price: BigDecimal?
    val cuisine: String?

    init {
        if(name.isNullOrBlank() &&
            customerRating.isNullOrBlank() &&
            distance.isNullOrBlank() &&
            price.isNullOrBlank() &&
            cuisine.isNullOrBlank()
        ){
            throw BadRequestException("At least one parameter is required")
        }

        name?.let {
            if (it.length < 3) throw BadRequestException("Name should have at least 3 characters")
        }

        customerRating?.let {
            if(it.toInt() < 1 || it.toInt() > 5) throw BadRequestException("CustomerRating should be from 1 to 5")
        }

        distance?.let {
            if(it.toInt() < 1 || it.toInt() > 10) throw BadRequestException("Distance should be from 1 to 10")
        }

        price?.let {
            if(BigDecimal(it) < BigDecimal.TEN || BigDecimal(it) > BigDecimal(50))
                throw BadRequestException("Price should be from $10 to $50")
        }

        cuisine?.let {
            try {
                Cuisine.valueOf(cuisine.uppercase())
            } catch (err: Throwable) {
                throw BadRequestException("Cuisine is invalid")
            }
        }

        this.name = name
        this.customerRating = customerRating?.toInt()
        this.distance = distance?.toInt()
        this.price = price?.let { BigDecimal(price) }
        this.cuisine = cuisine
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InputRestaurantDto

        if (name != other.name) return false
        if (customerRating != other.customerRating) return false
        if (distance != other.distance) return false
        if (price != other.price) return false
        if (cuisine != other.cuisine) return false

        return true
    }

    override fun hashCode(): Int {
        return name?.hashCode() ?: 0
    }
}
