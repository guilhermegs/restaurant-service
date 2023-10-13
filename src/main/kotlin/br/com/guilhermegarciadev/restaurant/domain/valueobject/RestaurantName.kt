package br.com.guilhermegarciadev.restaurant.domain.valueobject

import br.com.guilhermegarciadev.restaurant.domain.entity.Searchable

class RestaurantName(
    val value: String
): Searchable<String> {
    override fun matches(criteria: String): Boolean =
        criteria.isBlank() || removeSpaces(value).contains(removeSpaces(criteria), true)

    private fun removeSpaces(string: String) = string.replace(" ", "")
}
