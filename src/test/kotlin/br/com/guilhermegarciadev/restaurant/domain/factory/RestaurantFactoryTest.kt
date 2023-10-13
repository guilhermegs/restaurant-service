package br.com.guilhermegarciadev.restaurant.domain.factory

import br.com.guilhermegarciadev.restaurant.domain.valueobject.Cuisine
import br.com.guilhermegarciadev.restaurant.usecase.search.dto.InputRestaurantDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class RestaurantFactoryTest {

    @Test
    fun `Should create a restaurant criteria`() {
        val restaurant = RestaurantFactory.createRestaurantCriteria(
            "Restaurant 1",
            1,
            2,
            BigDecimal.TEN,
            "American"
        )

        assertEquals("Restaurant 1", restaurant.name)
        assertEquals(1, restaurant.customerRating)
        assertEquals(2, restaurant.distance)
        assertEquals(BigDecimal.TEN, restaurant.price)
        assertEquals(Cuisine.AMERICAN, restaurant.cuisine)
    }

    @Test
    fun `Should create a restaurant criteria using input object`() {
        val restaurant = RestaurantFactory.createRestaurantCriteria(
            InputRestaurantDto(
            "Restaurant 1",
            "1",
            "2",
            "10",
            "American"
            )
        )

        assertEquals("Restaurant 1", restaurant.name)
        assertEquals(1, restaurant.customerRating)
        assertEquals(2, restaurant.distance)
        assertEquals(BigDecimal.TEN, restaurant.price)
        assertEquals(Cuisine.AMERICAN, restaurant.cuisine)
    }

    @Test
    fun `Should create a restaurant`() {
        val restaurant = RestaurantFactory.createRestaurant(
            "Restaurant 1",
            1,
            2,
            BigDecimal.TEN,
            "American"
        )

        assertEquals("Restaurant 1", restaurant.name.value)
        assertEquals(1, restaurant.customerRating.value)
        assertEquals(2, restaurant.distance.value)
        assertEquals(BigDecimal.TEN, restaurant.price.value)
        assertEquals(Cuisine.AMERICAN, restaurant.cuisine)
    }
}
