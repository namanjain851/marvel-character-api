package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.demo.MarvelApi.MarvelApiConnector;
import com.example.demo.data.MarvelCharacterWrapper;

@Component
public class MarvelService {

	@Autowired
    private MarvelApiConnector marvelAPIConnector;
	
	@Cacheable(value="characters",key="{#nameStartsWith, #pageNo}")
    public MarvelCharacterWrapper getMarvelCharacter(String nameStartsWith, String pageNo) {
        return marvelAPIConnector.getCharacterFromAPI(nameStartsWith,pageNo);
    }
}
