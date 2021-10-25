package com.app.service;

import com.app.domain.Suggestion;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WebappAPIClient {
    private RestTemplate restTemplate;
    @Value("${application.suggestionUrlTemplate}")
    private String suggestionUrl;

    public List<Suggestion> findSuggestionsByCity(@NonNull String city){
        restTemplate.getForEntity(suggestionUrl, Suggestion[].class, )
    }
}
