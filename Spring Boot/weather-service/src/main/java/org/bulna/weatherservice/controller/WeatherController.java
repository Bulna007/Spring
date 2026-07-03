package org.bulna.weatherservice.controller;

import org.bulna.weatherservice.entity.Weather;
import org.bulna.weatherservice.service.CacheInspectionService;
import org.bulna.weatherservice.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;
    private CacheInspectionService cacheInspectionService;
    @Autowired
    public void setWeatherService(WeatherService weatherService,  CacheInspectionService cacheInspectionService) {
        this.weatherService = weatherService;
        this.cacheInspectionService = cacheInspectionService;
    }

    @GetMapping
    public String getWeather(@RequestParam String city) {
        String weatherByCity = weatherService.getWeatherByCity(city);
        return weatherByCity;
    }

    @PostMapping
    public Weather addWeather(@RequestBody Weather weather) {
        return weatherService.addWeather(weather);
    }

    @GetMapping("/all")
    public List<Weather> getAllWeather() {
        return  weatherService.getAllWeather();
    }

    @GetMapping("/cacheData")
    public void getCacheData() {
        cacheInspectionService.printCacheContents("weather");
    }

    @PutMapping("/{city}")
    public String updateWeather(@PathVariable String city, @RequestParam String updateWeather) {
        return weatherService.updateWeather(city, updateWeather);
    }

    @DeleteMapping("/{city}")
    public String deleteWeather(@PathVariable String city) {
        weatherService.deleteWeather(city);
        return "Weather data for " + city + " has been deleted";
    }
}
