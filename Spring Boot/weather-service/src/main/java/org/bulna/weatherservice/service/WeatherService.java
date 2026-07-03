package org.bulna.weatherservice.service;

import jakarta.transaction.Transactional;
import org.bulna.weatherservice.repository.WeatherRepository;
import org.bulna.weatherservice.entity.Weather;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    private WeatherRepository weatherRepository;
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Cacheable(value = "weather", key = "#city")
    public String getWeatherByCity(String city) {
        System.out.println("Fetching Weather from DB for city: " + city);
        Optional<Weather> weather = weatherRepository.findByCity(city);
        return weather.map(Weather::getForecast).orElse("Weather data not available");
    }

    public Weather addWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public List<Weather> getAllWeather(){
        return weatherRepository.findAll();
    }

    @CachePut(value = "weather", key = "#city")
    public String updateWeather(String city, String updateWeather) {
        weatherRepository.findByCity(city).ifPresent(weather -> {
            weather.setForecast(updateWeather);
        });
        return updateWeather;
    }

    @Transactional
    @CacheEvict(value = "weather", key = "#city")
    public void deleteWeather(String city) {
        System.out.println("Deleting Weather from DB for city: " + city);
        weatherRepository.deleteByCity(city);
    }

}
