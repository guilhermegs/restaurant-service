package br.com.guilhermegarciadev.restaurant.domain.valueobject

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DistanceTest {

    @Test
    fun `Should return false when matches is called with criteria less than value`(){
        val distance = Distance(2)
        val criteria = 1
        assertFalse(distance.matches(criteria))
    }

    @Test
    fun `Should return true when matches is called with criteria equals to value`(){
        val distance = Distance(1)
        val criteria = 1
        assertTrue(distance.matches(criteria))
    }

    @Test
    fun `Should return true when matches is called with criteria greater than value`(){
        val distance = Distance(1)
        val criteria = 2
        assertTrue(distance.matches(criteria))
    }

    @Test
    fun `Should return one when compareTo is called with other less than value`(){
        val distance = Distance(3)
        val criteria = Distance(2)
        assertEquals(1, distance.compareTo(criteria))
    }

    @Test
    fun `Should return zero when compareTo is called with other equal to value`(){
        val distance = Distance(3)
        val criteria = Distance(3)
        assertEquals(0, distance.compareTo(criteria))
    }

    @Test
    fun `Should return minus one number when compareTo is called with greater than to value`(){
        val distance = Distance(3)
        val criteria = Distance(5)
        assertEquals(-1, distance.compareTo(criteria))
    }
}