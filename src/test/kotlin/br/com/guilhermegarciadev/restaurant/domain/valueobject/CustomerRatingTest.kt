package br.com.guilhermegarciadev.restaurant.domain.valueobject

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

internal class CustomerRatingTest {

    @Test
    fun `Should return true when matches is called with criteria less than value`(){
        val customerRating = CustomerRating(2)
        val criteria = 1
        assertTrue(customerRating.matches(criteria))
    }

    @Test
    fun `Should return true when matches is called with criteria equals to value`(){
        val customerRating = CustomerRating(3)
        val criteria = 3
        assertTrue(customerRating.matches(criteria))
    }

    @Test
    fun `Should return false when matches is called with criteria greater than value`(){
        val customerRating = CustomerRating(3)
        val criteria = 5
        assertFalse(customerRating.matches(criteria))
    }

    @Test
    fun `Should return minus one when compareTo is called with other less than value`(){
        val customerRating = CustomerRating(3)
        val criteria = CustomerRating(2)
        assertEquals(-1, customerRating.compareTo(criteria))
    }

    @Test
    fun `Should return zero when compareTo is called with other equal to value`(){
        val customerRating = CustomerRating(3)
        val criteria = CustomerRating(3)
        assertEquals(0, customerRating.compareTo(criteria))
    }

    @Test
    fun `Should return one number when compareTo is called with greater than to value`(){
        val customerRating = CustomerRating(3)
        val criteria = CustomerRating(5)
        assertEquals(1, customerRating.compareTo(criteria))
    }
}