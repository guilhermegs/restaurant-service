package br.com.guilhermegarciadev.restaurant.usecase.search

import br.com.guilhermegarciadev.restaurant.domain.factory.RestaurantFactory
import br.com.guilhermegarciadev.restaurant.domain.repository.RestaurantRepository
import br.com.guilhermegarciadev.restaurant.usecase.search.dto.InputRestaurantDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.math.BigDecimal

internal class SearchRestaurantUseCaseTest {

    private val repository = Mockito.mock(RestaurantRepository::class.java)
    private val searchRestaurantUseCase = SearchRestaurantUseCase(repository)

    @Test
    fun `Should return all restaurants returned by repository sorted correctly if the quantity is less than 5`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "Mcdonald's",
            4,
            1,
            BigDecimal(30),
            "Chinese"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "Garcia Salgados",
            5,
            1,
            BigDecimal(10),
            "Chinese"
        )

        val restaurant3 = RestaurantFactory.createRestaurant(
            "Outback",
            3,
            4,
            BigDecimal(30),
            "Chinese"
        )

        val restaurant4 = RestaurantFactory.createRestaurant(
            "China in box",
            5,
            7,
            BigDecimal(30),
            "Chinese"
        )

        val input = InputRestaurantDto(cuisine = "Chinese")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        Mockito.`when`(repository.search(criteria)).thenReturn(listOf(
            restaurant1, restaurant2, restaurant3, restaurant4
        ))

        val restaurants = searchRestaurantUseCase.execute(input)

        assertEquals(4, restaurants.size)
        assertEquals("Garcia Salgados", restaurants[0].name.value)
        assertEquals("Mcdonald's", restaurants[1].name.value)
        assertEquals("Outback", restaurants[2].name.value)
        assertEquals("China in box", restaurants[3].name.value)
    }

    @Test
    fun `Should return all restaurants returned by repository sorted correctly if the quantity is equal to 5`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "Mcdonald's",
            4,
            2,
            BigDecimal(50),
            "African"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "Garcia Salgados",
            5,
            1,
            BigDecimal(10),
            "African"
        )

        val restaurant3 = RestaurantFactory.createRestaurant(
            "Outback",
            4,
            2,
            BigDecimal(30),
            "African"
        )

        val restaurant4 = RestaurantFactory.createRestaurant(
            "China in box",
            2,
            5,
            BigDecimal(10),
            "African"
        )

        val restaurant5 = RestaurantFactory.createRestaurant(
            "Subway",
            2,
            5,
            BigDecimal(20),
            "African"
        )

        val input = InputRestaurantDto(cuisine = "African")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        Mockito.`when`(repository.search(criteria)).thenReturn(listOf(
            restaurant1, restaurant2, restaurant3, restaurant4, restaurant5
        ))

        val restaurants = searchRestaurantUseCase.execute(input)

        assertEquals(5, restaurants.size)
        assertEquals("Garcia Salgados", restaurants[0].name.value)
        assertEquals("Outback", restaurants[1].name.value)
        assertEquals("Mcdonald's", restaurants[2].name.value)
        assertEquals("China in box", restaurants[3].name.value)
        assertEquals("Subway", restaurants[4].name.value)
    }

    @Test
    fun `Should return just 5 first restaurants returned by repository sorted correctly if the quantity is greater to 5`(){
        val restaurant1 = RestaurantFactory.createRestaurant(
            "Mcdonald's",
            4,
            2,
            BigDecimal(50),
            "American"
        )

        val restaurant2 = RestaurantFactory.createRestaurant(
            "Garcia Salgados",
            5,
            1,
            BigDecimal(40),
            "American"
        )

        val restaurant3 = RestaurantFactory.createRestaurant(
            "Outback",
            4,
            2,
            BigDecimal(30),
            "American"
        )

        val restaurant4 = RestaurantFactory.createRestaurant(
            "China in box",
            2,
            5,
            BigDecimal(10),
            "American"
        )

        val restaurant5 = RestaurantFactory.createRestaurant(
            "Subway",
            2,
            5,
            BigDecimal(20),
            "American"
        )

        val restaurant6 = RestaurantFactory.createRestaurant(
            "KFC",
            5,
            1,
            BigDecimal(10),
            "American"
        )

        val input = InputRestaurantDto(cuisine = "American")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        Mockito.`when`(repository.search(criteria)).thenReturn(listOf(
            restaurant1, restaurant2, restaurant3, restaurant4, restaurant5, restaurant6
        ))

        val restaurants = searchRestaurantUseCase.execute(input)

        assertEquals(5, restaurants.size)
        assertEquals("KFC", restaurants[0].name.value)
        assertEquals("Garcia Salgados", restaurants[1].name.value)
        assertEquals("Outback", restaurants[2].name.value)
        assertEquals("Mcdonald's", restaurants[3].name.value)
        assertEquals("China in box", restaurants[4].name.value)
    }

    @Test
    fun `should return an empty list when the repository does not return any restaurants`(){
        val input = InputRestaurantDto(cuisine = "American")
        val criteria = RestaurantFactory.createRestaurantCriteria(input)

        Mockito.`when`(repository.search(criteria)).thenReturn(emptyList())

        val restaurants = searchRestaurantUseCase.execute(input)

        assertEquals(0, restaurants.size)
    }
}
