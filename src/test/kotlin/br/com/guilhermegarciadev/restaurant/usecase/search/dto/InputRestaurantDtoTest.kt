package br.com.guilhermegarciadev.restaurant.usecase.search.dto

import br.com.guilhermegarciadev.restaurant.usecase.search.exception.BadRequestException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

internal class InputRestaurantDtoTest {

    @Test
    fun `Should throw an error when all fields of filter are null`(){
        val exception = assertThrows<BadRequestException> {
            InputRestaurantDto()
        }
        assertEquals("At least one parameter is required", exception.message)
    }

    @Test
    fun `Should throw an error when all fields of filter are empty`(){
        val exception = assertThrows<BadRequestException> {
            InputRestaurantDto(
                "",
                "",
                "",
                "",
                ""
            )
        }
        assertEquals("At least one parameter is required", exception.message)
    }

    @Test
    fun `Should throw an error when all fields of filter are blank`(){
        val exception = assertThrows<BadRequestException> {
            InputRestaurantDto(
                " ",
                " ",
                " ",
                " ",
                " "
            )
        }
        assertEquals("At least one parameter is required", exception.message)
    }

    @Test
    fun `Should throw an error when name is invalid`(){
        val exception = assertThrows<BadRequestException> {
            InputRestaurantDto(name = "ab")
        }
        assertEquals("Name should have at least 3 characters", exception.message)
    }

    @Test
    fun `Should throw an error when customerRating is invalid`(){
        val invalidCustomerRating = listOf("-1", "0", "6")

        invalidCustomerRating.forEach {
            val exception = assertThrows<BadRequestException> {
                InputRestaurantDto(customerRating = it)
            }
            assertEquals("CustomerRating should be from 1 to 5", exception.message)
        }
    }

    @Test
    fun `Should throw an error when distance is invalid`(){
        val invalidCustomerRating = listOf("-1", "0", "11")

        invalidCustomerRating.forEach {
            val exception = assertThrows<BadRequestException> {
                InputRestaurantDto(distance = it)
            }
            assertEquals("Distance should be from 1 to 10", exception.message)
        }
    }

    @Test
    fun `Should throw an error when price is invalid`(){
        val invalidCustomerRating = listOf("-1", "9", "51")

        invalidCustomerRating.forEach {
            val exception = assertThrows<BadRequestException> {
                InputRestaurantDto(price = it)
            }
            assertEquals("Price should be from $10 to $50", exception.message)
        }
    }

    @Test
    fun `Should throw an error when cuisine is invalid`(){
        val exception = assertThrows<BadRequestException> {
            InputRestaurantDto(cuisine ="Brazilian")
        }
        assertEquals("Cuisine is invalid", exception.message)
    }

    @Test
    fun `Should create input when name is not null`(){
        val input = InputRestaurantDto(name = "Restaurant 1")
        assertEquals("Restaurant 1", input.name)
    }

    @Test
    fun `Should create input when customerRating is not null`(){
        val input = InputRestaurantDto(customerRating = "3")
        assertEquals(3, input.customerRating)
    }

    @Test
    fun `Should create input when distance is not null`(){
        val input = InputRestaurantDto(distance ="2")
        assertEquals(2, input.distance)
    }

    @Test
    fun `Should create input when price is not null`(){
        val input = InputRestaurantDto(price = "10")
        assertEquals(BigDecimal.TEN, input.price)
    }

    @Test
    fun `Should create input when cuisine is not null`(){
        val input = InputRestaurantDto(cuisine = "American")
        assertEquals("American", input.cuisine)
    }
}
