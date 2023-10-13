package br.com.guilhermegarciadev.restaurant.domain.entity

interface Searchable<T> {
    fun matches(criteria: T): Boolean
}
