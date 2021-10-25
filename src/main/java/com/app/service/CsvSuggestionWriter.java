package com.app.service;

import com.app.dto.CsvSuggestionDto;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Cleanup;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@Component
public class CsvSuggestionWriter {
    private CsvMapper csvMapper = new CsvMapper();
    //Writing data to 'csv' format in order
    private CsvSchema schema = csvMapper.schemaFor(CsvSuggestionDto.class)
            .withHeader()
            .sortedBy("_id", "name", "type", "latitude", "longitude");

    //Writing a list of objects to a file
    public void write(@NonNull String fileName, @NonNull List<CsvSuggestionDto> data){
        try {
            @Cleanup
            Writer write = new PrintWriter(new FileWriter(fileName));
            doWrite(write, data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Write to a file
    private void doWrite(@NonNull Writer writer, @NonNull List<CsvSuggestionDto> data) throws IOException {
        csvMapper.writer().with(schema).writeValues(writer).writeAll(data);
    }
}
