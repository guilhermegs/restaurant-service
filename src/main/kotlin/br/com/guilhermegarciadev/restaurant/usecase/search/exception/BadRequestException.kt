package br.com.guilhermegarciadev.restaurant.usecase.search.exception

class BadRequestException(override val message: String): RuntimeException(message)