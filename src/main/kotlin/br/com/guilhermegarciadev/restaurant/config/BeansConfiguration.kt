package br.com.guilhermegarciadev.restaurant.config

import br.com.guilhermegarciadev.restaurant.config.utils.RestaurantFileLoader
import br.com.guilhermegarciadev.restaurant.domain.entity.Restaurant
import br.com.guilhermegarciadev.restaurant.domain.repository.RestaurantRepository
import br.com.guilhermegarciadev.restaurant.infrastructure.dataprovider.memory.RestaurantRepositoryImpl
import br.com.guilhermegarciadev.restaurant.usecase.search.SearchRestaurantUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class BeansConfiguration {

    @Bean
    fun loadRestaurants() : List<Restaurant> = RestaurantFileLoader.load()

    @Bean
    fun restaurantRepository() : RestaurantRepository = RestaurantRepositoryImpl(loadRestaurants())

    @Bean
    fun searchUseCase(): SearchRestaurantUseCase = SearchRestaurantUseCase(restaurantRepository())
}