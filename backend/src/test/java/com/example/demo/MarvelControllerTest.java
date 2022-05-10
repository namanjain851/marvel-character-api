package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.data.MarvelCharacterWrapper;
import com.example.demo.service.MarvelService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
class MarvelControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    CacheManager cacheManager;
    
    @Autowired
    MarvelService marvelService;
    
    @Test
    void get_list_of_character_by_prefix() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/" + "spider?page=1"))
                .andExpect(status().is(202))
                .andExpect(jsonPath("$.data.count").value(10))
                .andReturn();
    }
    
    @Test
    void cacheTest() {
        MarvelCharacterWrapper characterWrapper = marvelService.getMarvelCharacter("hulk", "1");
        ArrayList<String> key = new ArrayList<String>();
        key.add("hulk");
        key.add("1");
        assertEquals(characterWrapper, cacheManager.getCache("characters").get(key, MarvelCharacterWrapper.class));
    }
    
    
}
