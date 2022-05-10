package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelCharacterWrapper {
	@JsonProperty("data")
    public CharacterDataContainer data;
}
