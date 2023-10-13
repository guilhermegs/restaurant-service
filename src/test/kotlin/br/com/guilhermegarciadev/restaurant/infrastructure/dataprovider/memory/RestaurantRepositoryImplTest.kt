package br.com.guilhermegarciadev.restaurant.infrastructure.dataprovider.memory

import br.com.guilhermegarciadev.restaurant.domain.factory.RestaurantFactory
import br.com.guilhermegarciadev.restaurant.usecase.search.dto.InputRestaurantDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class RestaurantRepositoryImplTest {

    @Test
    fun `Should search restaurants by name`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "Mcdonald's",
            4,
            1,
            BigDecimal(30),
            "Chinese"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "Malback",
            5,
            1,
            BigDecimal(10),
            "American"
        )

        val restaurant3 = RestaurantFactory.createRestaurant(
            "Outback",
            3,
            4,
            BigDecimal(30),
            "Italian"
        )

        val restaurant4 = RestaurantFactory.createRestaurant(
            "China in box",
            5,
            7,
            BigDecimal(30),
            "Chinese"
        )

        val repository = RestaurantRepositoryImpl(listOf(
            restaurant1, restaurant2, restaurant3, restaurant4
        ))

        val input = InputRestaurantDto(name = "back")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        val restaurants = repository.search(criteria)

        assertEquals(2, restaurants.size)
        assertEquals("Malback", restaurants[0].name.value)
        assertEquals("Outback", restaurants[1].name.value)
    }

    @Test
    fun `Should search restaurants by customerRating`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "Mcdonald's",
            4,
            1,
            BigDecimal(30),
            "Chinese"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "Garcia Salgados",
            2,
            1,
            BigDecimal(10),
            "American"
        )

        val restaurant3 = RestaurantFactory.createRestaurant(
            "Outback",
            1,
            4,
            BigDecimal(30),
            "Italian"
        )

        val restaurant4 = RestaurantFactory.createRestaurant(
            "China in box",
            5,
            7,
            BigDecimal(30),
            "Chinese"
        )

        val repository = RestaurantRepositoryImpl(listOf(
            restaurant1, restaurant2, restaurant3, restaurant4
        ))

        val input = InputRestaurantDto(customerRating = "3")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        val restaurants = repository.search(criteria)

        assertEquals(2, restaurants.size)
        assertEquals("Mcdonald's", restaurants[0].name.value)
        assertEquals("China in box", restaurants[1].name.value)
    }

    @Test
    fun `Should search restaurants by distance`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "Mcdonald's",
            4,
            1,
            BigDecimal(30),
            "Chinese"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "Garcia Salgados",
            2,
            4,
            BigDecimal(10),
            "American"
        )

        val restaurant3 = RestaurantFactory.createRestaurant(
            "Outback",
            1,
            6,
            BigDecimal(30),
            "Italian"
        )

        val restaurant4 = RestaurantFactory.createRestaurant(
            "China in box",
            5,
            10,
            BigDecimal(30),
            "Chinese"
        )

        val repository = RestaurantRepositoryImpl(listOf(
            restaurant1, restaurant2, restaurant3, restaurant4
        ))

        val input = InputRestaurantDto(distance = "6")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        val restaurants = repository.search(criteria)

        assertEquals(3, restaurants.size)
        assertEquals("Mcdonald's", restaurants[0].name.value)
        assertEquals("Garcia Salgados", restaurants[1].name.value)
        assertEquals("Outback", restaurants[2].name.value)
    }

    @Test
    fun `Should search restaurants by price`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "Mcdonald's",
            4,
            1,
            BigDecimal(10),
            "Chinese"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "Garcia Salgados",
            2,
            4,
            BigDecimal(37),
            "American"
        )

        val restaurant3 = RestaurantFactory.createRestaurant(
            "Outback",
            1,
            6,
            BigDecimal(42),
            "Italian"
        )

        val restaurant4 = RestaurantFactory.createRestaurant(
            "China in box",
            5,
            10,
            BigDecimal(15),
            "Chinese"
        )

        val repository = RestaurantRepositoryImpl(listOf(
            restaurant1, restaurant2, restaurant3, restaurant4
        ))

        val input = InputRestaurantDto(price = "17")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        val restaurants = repository.search(criteria)

        assertEquals(2, restaurants.size)
        assertEquals("Mcdonald's", restaurants[0].name.value)
        assertEquals("China in box", restaurants[1].name.value)
    }

    @Test
    fun `Should search restaurants by cuisine`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "Mcdonald's",
            4,
            1,
            BigDecimal(10),
            "Chinese"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "Garcia Salgados",
            2,
            4,
            BigDecimal(37),
            "American"
        )

        val restaurant3 = RestaurantFactory.createRestaurant(
            "Outback",
            1,
            6,
            BigDecimal(42),
            "Italian"
        )

        val restaurant4 = RestaurantFactory.createRestaurant(
            "China in box",
            5,
            10,
            BigDecimal(15),
            "Chinese"
        )

        val repository = RestaurantRepositoryImpl(listOf(
            restaurant1, restaurant2, restaurant3, restaurant4
        ))

        val input = InputRestaurantDto(cuisine = "Chinese")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        val restaurants = repository.search(criteria)

        assertEquals(2, restaurants.size)
        assertEquals("Mcdonald's", restaurants[0].name.value)
        assertEquals("China in box", restaurants[1].name.value)
    }

    @Test
    fun `Should search restaurants by all parameters`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "China 123",
            4,
            1,
            BigDecimal(10),
            "Chinese"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "China in box",
            5,
            1,
            BigDecimal(15),
            "Chinese"
        )

        val repository = RestaurantRepositoryImpl(listOf(
            restaurant1, restaurant2
        ))

        val input = InputRestaurantDto(
            name = "china",
            customerRating = "5",
            distance = "3",
            price = "25",
            cuisine = "Chinese")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        val restaurants = repository.search(criteria)

        assertEquals(1, restaurants.size)
        assertEquals("China in box", restaurants[0].name.value)
    }

    @Test
    fun `Should search restaurants by all parameters - not found`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "China 123",
            4,
            1,
            BigDecimal(10),
            "Chinese"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "China in box",
            5,
            1,
            BigDecimal(15),
            "Chinese"
        )

        val repository = RestaurantRepositoryImpl(listOf(
            restaurant1, restaurant2
        ))

        val input = InputRestaurantDto(
            name = "Out",
            customerRating = "5",
            distance = "3",
            price = "25",
            cuisine = "Chinese")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        val restaurants = repository.search(criteria)

        assertEquals(0, restaurants.size)
    }
}