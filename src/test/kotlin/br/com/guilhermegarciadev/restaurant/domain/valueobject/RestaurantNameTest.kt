package br.com.guilhermegarciadev.restaurant.domain.valueobject

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

internal class RestaurantNameTest{

    @Test
    fun `Should return true if the criteria is empty or blank`() {
        val name = RestaurantName("Any name")
        val criteria = listOf("", " ")

        criteria.forEach {
            assertTrue(name.matches(it))
        }
    }

    @Test
    fun `Should return true if the criteria is excatly the restaurant name`() {
        val name = RestaurantName("Any name")
        val criteria = "Any Name"
        assertTrue(name.matches(criteria))
    }

    @Test
    fun `Should return true if the criteria is a part of the restaurant name`() {
        val name = RestaurantName("Any name")
        val criteria = "ame"
        assertTrue(name.matches(criteria))
    }

    @Test
    fun `Should return true if the criteria is a part of the restaurant name divided in two words`() {
        val name = RestaurantName("Any name")
        val criteria = "yname"
        assertTrue(name.matches(criteria))
    }

    @Test
    fun `Should return true if the criteria is a part of the restaurant name ignoring case`() {
        val name = RestaurantName("ANY NAME")
        val criteria = "any name"
        assertTrue(name.matches(criteria))
    }

    @Test
    fun `Should return false if the criteria is different of restaurant name`() {
        val name = RestaurantName("Any Name")
        val criteria = "Other Name"
        assertFalse(name.matches(criteria))
    }
}
