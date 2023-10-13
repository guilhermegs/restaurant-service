package br.com.guilhermegarciadev.restaurant.domain.entity

import br.com.guilhermegarciadev.restaurant.domain.factory.RestaurantFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class RestaurantTest {

    private val restaurant = RestaurantFactory.createRestaurant(
        "Mcdonald's",
        4,
        2,
        BigDecimal(30),
        "Chinese"
    )

    @Test
    fun `Should return true if restaurant name matches`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            "onald",
            null,
            null,
            null,
            null
        )
        assertTrue(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant name doesn't match`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            "abcd",
            null,
            null,
            null,
            null
        )
        assertFalse(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant customerRating matches`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            null,
            3,
            null,
            null,
            null
        )
        assertTrue(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant customerRating doesn't match`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            null,
            5,
            null,
            null,
            null
        )
        assertFalse(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant distance matches`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            null,
            null,
            3,
            null,
            null
        )
        assertTrue(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant distance doesn't match`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            null,
            null,
            1,
            null,
            null
        )
        assertFalse(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant price matches`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            null,
            null,
            null,
            BigDecimal(50),
            null
        )
        assertTrue(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant price doesn't match`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            null,
            null,
            null,
            BigDecimal(10),
            null
        )
        assertFalse(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant cuisine matches`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            null,
            null,
            null,
             null,
            "Chinese"
        )
        assertTrue(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if restaurant cuisine doesn't match`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            null,
            null,
            null,
            null,
            "Italian"
        )
        assertFalse(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if criteria combination matches`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            "Mcd",
            4,
            2,
            BigDecimal(30),
            "Chinese"
        )
        assertTrue(restaurant.matches(criteria))
    }

    @Test
    fun `Should return true if criteria combination doesn't match`() {
        val criteria = RestaurantFactory.createRestaurantCriteria(
            "Inf",
            4,
            2,
            BigDecimal(30),
            "American"
        )
        assertFalse(restaurant.matches(criteria))
    }

    @Test
    fun `Should return minus one if restaurant is distance is less than other distance`() {
        val other = RestaurantFactory.createRestaurant(
            "Outback",
            4,
            4,
            BigDecimal(30),
            "Chinese"
        )
        assertEquals(-1, restaurant.compareTo(other))
    }

    @Test
    fun `Should return one if restaurant is distance is greater than other distance`() {
        val other = RestaurantFactory.createRestaurant(
            "Outback",
            4,
            1,
            BigDecimal(30),
            "Chinese"
        )
        assertEquals(1, restaurant.compareTo(other))
    }

    @Test
    fun `Should return one if restaurant is customerRating is less than other customerRating`() {
        val other = RestaurantFactory.createRestaurant(
            "Outback",
            5,
            2,
            BigDecimal(30),
            "Chinese"
        )
        assertEquals(1, restaurant.compareTo(other))
    }

    @Test
    fun `Should return minus one if restaurant is customerRating is greater than other customerRating`() {
        val other = RestaurantFactory.createRestaurant(
            "Outback",
            3,
            2,
            BigDecimal(30),
            "Chinese"
        )
        assertEquals(-1, restaurant.compareTo(other))
    }

    @Test
    fun `Should return one if restaurant is price is less than other price`() {
        val other = RestaurantFactory.createRestaurant(
            "Outback",
            4,
            2,
            BigDecimal(29),
            "Chinese"
        )
        assertEquals(1, restaurant.compareTo(other))
    }

    @Test
    fun `Should return minus one if restaurant is price is greater than other price`() {
        val other = RestaurantFactory.createRestaurant(
            "Outback",
            4,
            2,
            BigDecimal(31),
            "Chinese"
        )
        assertEquals(-1, restaurant.compareTo(other))
    }

    @Test
    fun `Should return zero if restaurant is price is equal to other price`() {
        val other = RestaurantFactory.createRestaurant(
            "Outback",
            4,
            2,
            BigDecimal(30),
            "Chinese"
        )
        assertEquals(0, restaurant.compareTo(other))
    }
}
