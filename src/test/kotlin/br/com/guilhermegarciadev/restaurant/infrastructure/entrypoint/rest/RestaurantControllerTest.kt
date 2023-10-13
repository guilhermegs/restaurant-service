package br.com.guilhermegarciadev.restaurant.infrastructure.entrypoint.rest

import br.com.guilhermegarciadev.restaurant.domain.factory.RestaurantFactory
import br.com.guilhermegarciadev.restaurant.usecase.search.SearchRestaurantUseCase
import br.com.guilhermegarciadev.restaurant.usecase.search.dto.InputRestaurantDto
import br.com.guilhermegarciadev.restaurant.usecase.search.exception.BadRequestException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import java.math.BigDecimal

internal class RestaurantControllerTest {

    private val searchRestaurantUseCase = Mockito.mock(SearchRestaurantUseCase::class.java)
    private val controller = RestaurantController(searchRestaurantUseCase)

    @Test
    fun `Should return the restaurant list returned by usecase`() {
        val input = InputRestaurantDto(price = "50")

        Mockito.`when`(searchRestaurantUseCase.execute(input)).thenReturn(listOf(
            RestaurantFactory.createRestaurant(
                "Outback",
                5,
                1,
                BigDecimal(10),
                "Chinese"
            ),
            RestaurantFactory.createRestaurant(
                "McDonald's",
                5,
                1,
                BigDecimal(10),
                "Chinese"
            )
        ))

        val responseEntity = controller.get(mapOf("price" to "50"))
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(2, responseEntity.body?.size)
        assertEquals("Outback", responseEntity.body?.get(0)?.name)
        assertEquals("McDonald's", responseEntity.body?.get(1)?.name)
    }

    @Test
    fun `Should return bad request when exception handler is called`() {
        val responseEntity = controller.badRequestException(BadRequestException("Error"))
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.statusCode)
        assertEquals("Error", responseEntity.body)
    }
}
