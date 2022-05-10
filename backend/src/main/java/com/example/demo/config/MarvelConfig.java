package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.example.demo.MarvelApi.MarvelApiConnector;
import com.example.demo.service.MarvelService;

@Component
@PropertySource("classpath:application.yml")
public class MarvelConfig {
	@Bean
    MarvelApiConnector getMarvelAPIConnector() {
        return new MarvelApiConnector();
    }
	@Bean
    MarvelService getMarvelService() {
        return new MarvelService();
    }
}

