package com.app.service;

import com.app.domain.Suggestion;
import com.app.dto.CsvSuggestionDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CsvSuggestionConverter {

    public CsvSuggestionDto toCsvSuggestionDto(@NonNull Suggestion suggestion) {
        CsvSuggestionDto dto = new CsvSuggestionDto();
        dto.setId(suggestion.getId());
        dto.setName(suggestion.getName());
        dto.setType(suggestion.getType());
        dto.setLatitude(suggestion.getGeoPosition().getLatitude());
        dto.setLatitude(suggestion.getGeoPosition().getLongitude());

        return dto;
    }
}
