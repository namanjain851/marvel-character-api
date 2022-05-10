package com.example.demo.data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelCharacter {
	@JsonProperty("id")
    public long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("description")
    public String description;

    @JsonProperty("thumbnail")
    public Thumbnail thumbnail;
}
