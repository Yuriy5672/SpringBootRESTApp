package com.app.service;

import com.app.dto.CsvSuggestionDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvSuggestionWriter {
    public void write(@NonNull String fileName, @NonNull List<CsvSuggestionDto> data){

    }
}
