package com.example.demo.data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDataContainer {
	@JsonProperty("results")
    public List<MarvelCharacter> results;

    @JsonProperty("count")
    public long count;

    @JsonProperty("total")
    public long total;
}
