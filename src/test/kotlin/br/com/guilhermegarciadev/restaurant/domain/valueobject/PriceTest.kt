package br.com.guilhermegarciadev.restaurant.domain.valueobject

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class PriceTest {

    @Test
    fun `Should return false when matches is called with criteria less than value`(){
        val price = Price(BigDecimal(2))
        val criteria = BigDecimal(1)
        assertFalse(price.matches(criteria))
    }

    @Test
    fun `Should return true when matches is called with criteria equals to value`(){
        val price = Price(BigDecimal(1))
        val criteria = BigDecimal(1)
        assertTrue(price.matches(criteria))
    }

    @Test
    fun `Should return true when matches is called with criteria greater than value`(){
        val price = Price(BigDecimal(1))
        val criteria = BigDecimal(2)
        assertTrue(price.matches(criteria))
    }

    @Test
    fun `Should return one when compareTo is called with other less than value`(){
        val price = Price(BigDecimal(3))
        val criteria = Price(BigDecimal(2))
        assertEquals(1, price.compareTo(criteria))
    }

    @Test
    fun `Should return zero when compareTo is called with other equal to value`(){
        val price = Price(BigDecimal(3))
        val criteria = Price(BigDecimal(3))
        assertEquals(0, price.compareTo(criteria))
    }

    @Test
    fun `Should return minus one number when compareTo is called with greater than to value`(){
        val price = Price(BigDecimal(3))
        val criteria = Price(BigDecimal(5))
        assertEquals(-1, price.compareTo(criteria))
    }
}
