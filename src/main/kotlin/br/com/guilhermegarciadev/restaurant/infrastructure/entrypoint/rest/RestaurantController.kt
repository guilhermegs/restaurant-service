package br.com.guilhermegarciadev.restaurant.infrastructure.entrypoint.rest

import br.com.guilhermegarciadev.restaurant.infrastructure.entrypoint.rest.dto.OutputRestaurantDto
import br.com.guilhermegarciadev.restaurant.usecase.search.SearchRestaurantUseCase
import br.com.guilhermegarciadev.restaurant.usecase.search.dto.InputRestaurantDto
import br.com.guilhermegarciadev.restaurant.usecase.search.exception.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1")
class RestaurantController(
    private val useCase: SearchRestaurantUseCase
) {

    @GetMapping("/restaurant")
    fun get(@RequestParam queryParams: Map<String, String>): ResponseEntity<List<OutputRestaurantDto>> {
        val restaurants = this.useCase.execute(
            InputRestaurantDto(
                name = queryParams["name"],
                customerRating = queryParams["customerRating"],
                price = queryParams["price"],
                distance = queryParams["distance"],
                cuisine = queryParams["cuisine"]
            )
        )
        return ResponseEntity.ok(OutputRestaurantDto.from(restaurants))
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(exception: BadRequestException) : ResponseEntity<String> {
        return ResponseEntity.badRequest().body(exception.message)
    }
}