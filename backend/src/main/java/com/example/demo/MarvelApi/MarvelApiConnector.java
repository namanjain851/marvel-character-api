package com.example.demo.MarvelApi;

import com.example.demo.data.MarvelCharacterWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file:application.yml")
public class MarvelApiConnector {
	@Value("${marvel.domain}")
    private String marvelDomain;
    @Value("${marvel.charactersPath}")
    private String marvelCharactersPath;
    @Value("${user.publicKey}")
    private String publicKey;
    @Value("${user.privateKey}")
    private String privateKey;

    public MarvelCharacterWrapper getCharacterFromAPI(String nameStartsWith, String pageNo) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String ts = Long.toString(System.currentTimeMillis());
            Integer page = Integer.parseInt(pageNo);
            String offset = String.valueOf((page-1)*10);
            URI uri = new URIBuilder(marvelDomain + marvelCharactersPath)
            		.addParameter("nameStartsWith", nameStartsWith)
            		.addParameter("limit", "10")
            		.addParameter("offset", offset)
                    .addParameter("apikey", publicKey)
                    .addParameter("ts", ts)
                    .addParameter("hash", DigestUtils.md5Hex(ts + privateKey + publicKey))
                    .build();
            HttpGet request = new HttpGet(uri);

            try (CloseableHttpResponse response = client.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    throw new RuntimeException("Response entity is null. Response: [" + response.toString() + "].");
                }
                String result = EntityUtils.toString(entity);

                // response entity expected to be JSON formatted to /data objects
                ObjectMapper mapper = new ObjectMapper();
                MarvelCharacterWrapper characterDataWrapper = mapper.readValue(result, MarvelCharacterWrapper.class);

                return characterDataWrapper;
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed when querying Marvel API.", e);
        }
    }
	
}
