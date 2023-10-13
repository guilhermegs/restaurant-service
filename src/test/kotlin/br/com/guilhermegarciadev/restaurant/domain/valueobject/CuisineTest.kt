package br.com.guilhermegarciadev.restaurant.domain.valueobject

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class CuisineTest {

    @Test
    fun `Should return true when matches is called with criteria null`() {
        assertTrue(Cuisine.AMERICAN.matches(null))
    }

    @Test
    fun `Should return true when matches is called with criteria equals to cuisineName`() {
        assertTrue(Cuisine.AFRICAN.matches("African"))
    }

    @Test
    fun `Should return true when matches is called with criteria equals to cuisineName ignoring case`() {
        assertTrue(Cuisine.ITALIAN.matches("italian"))
    }

    @Test
    fun `Should return false when matches is called with criteria different of cuisineName`() {
        assertFalse(Cuisine.ITALIAN.matches("Brazilian"))
    }
}