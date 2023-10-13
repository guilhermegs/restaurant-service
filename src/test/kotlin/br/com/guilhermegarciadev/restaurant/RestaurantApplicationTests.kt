package br.com.guilhermegarciadev.restaurant

import br.com.guilhermegarciadev.restaurant.infrastructure.entrypoint.rest.dto.OutputRestaurantDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(
	classes = [RestaurantApplication::class],
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun contextLoads() {
	}

	@Test
	fun `Should return bad request when no criteria is provided`(){
		val result = restTemplate.getForEntity("/v1/restaurant", String::class.java);

		assertNotNull(result)
		assertEquals(HttpStatus.BAD_REQUEST, result?.statusCode)
		assertEquals("At least one parameter is required", result?.body)
	}

	@Test
	fun `Should return ok and selected restaurants filtered by name`(){
		val result: ResponseEntity<List<OutputRestaurantDto>> = restTemplate.exchange(
			"/v1/restaurant?name=table",
			HttpMethod.GET,
			null,
			object : ParameterizedTypeReference<List<OutputRestaurantDto>>() {}
		)

		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(5, result.body?.size)
		assertEquals("Fodder Table", result.body?.get(0)?.name)
		assertEquals("Tableooze", result.body?.get(1)?.name)
		assertEquals("Chow Table", result.body?.get(2)?.name)
		assertEquals("Herbed Table", result.body?.get(3)?.name)
		assertEquals("Palate Table", result.body?.get(4)?.name)
	}

	@Test
	fun `Should return ok and selected restaurants filtered by customerRating`(){
		val result: ResponseEntity<List<OutputRestaurantDto>> = restTemplate.exchange(
			"/v1/restaurant?customerRating=5",
			HttpMethod.GET,
			null,
			object : ParameterizedTypeReference<List<OutputRestaurantDto>>() {}
		)

		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(5, result.body?.size)
		assertEquals("Grove Table", result.body?.get(0)?.name)
		assertEquals("Bang Delicious", result.body?.get(1)?.name)
		assertEquals("Traditional Chow", result.body?.get(2)?.name)
		assertEquals("Tableadora", result.body?.get(3)?.name)
		assertEquals("Presto Grill", result.body?.get(4)?.name)
	}

	@Test
	fun `Should return ok and selected restaurants filtered by distance`(){
		val result: ResponseEntity<List<OutputRestaurantDto>> = restTemplate.exchange(
			"/v1/restaurant?distance=1",
			HttpMethod.GET,
			null,
			object : ParameterizedTypeReference<List<OutputRestaurantDto>>() {}
		)

		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(5, result.body?.size)
		assertEquals("Deliciousgenix", result.body?.get(0)?.name)
		assertEquals("Deliciouszilla", result.body?.get(1)?.name)
		assertEquals("Fodder Table", result.body?.get(2)?.name)
		assertEquals("Dished Grill", result.body?.get(3)?.name)
		assertEquals("Sizzle Yummy", result.body?.get(4)?.name)
	}

	@Test
	fun `Should return ok and selected restaurants filtered by price`(){
		val result: ResponseEntity<List<OutputRestaurantDto>> = restTemplate.exchange(
			"/v1/restaurant?price=10",
			HttpMethod.GET,
			null,
			object : ParameterizedTypeReference<List<OutputRestaurantDto>>() {}
		)

		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(5, result.body?.size)
		assertEquals("Deliciousgenix", result.body?.get(0)?.name)
		assertEquals("Dished Grill", result.body?.get(1)?.name)
		assertEquals("Kitchenster", result.body?.get(2)?.name)
		assertEquals("Chow Table", result.body?.get(3)?.name)
		assertEquals("Grove Table", result.body?.get(4)?.name)
	}

	@Test
	fun `Should return ok and selected restaurants filtered by cuisine`(){
		val result: ResponseEntity<List<OutputRestaurantDto>> = restTemplate.exchange(
			"/v1/restaurant?cuisine=African",
			HttpMethod.GET,
			null,
			object : ParameterizedTypeReference<List<OutputRestaurantDto>>() {}
		)

		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(5, result.body?.size)
		assertEquals("Presto Grill", result.body?.get(0)?.name)
		assertEquals("Festive Yummy", result.body?.get(1)?.name)
		assertEquals("Story Kitchen", result.body?.get(2)?.name)
		assertEquals("Aladdin Yummy", result.body?.get(3)?.name)
		assertEquals("Ambrosial Chow", result.body?.get(4)?.name)
	}

	@Test
	fun `Should return ok and selected selected restaurants filtered by multiple parameters`(){
		val result: ResponseEntity<List<OutputRestaurantDto>> = restTemplate.exchange(
			"/v1/restaurant?name=table&customerRating=3&distance=4&price=10&cuisine=Mexican",
			HttpMethod.GET,
			null,
			object : ParameterizedTypeReference<List<OutputRestaurantDto>>() {}
		)

		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(1, result.body?.size)
		assertEquals("Grove Table", result.body?.get(0)?.name)
	}

	@Test
	fun `Should return ok and selected empty list by multiple parameters`(){
		val result: ResponseEntity<List<OutputRestaurantDto>> = restTemplate.exchange(
			"/v1/restaurant?name=table&customerRating=5&distance=1&price=10&cuisine=African",
			HttpMethod.GET,
			null,
			object : ParameterizedTypeReference<List<OutputRestaurantDto>>() {}
		)

		assertNotNull(result)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(0, result.body?.size)
	}

}
