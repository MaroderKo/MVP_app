package com.mvp.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mvp.exception.WrongFileFormatException;
import com.mvp.service.CsvObjectMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvObjectMapperServiceImpl implements CsvObjectMapperService {

    private final CsvMapper mapper;

    @Override
    public <T> List<T> decode(String data, Class<T> type) {
        CsvSchema schema = mapper.schemaFor(type).withLineSeparator("\n").withColumnSeparator(';');
        try {
            MappingIterator<T> it = mapper
                    .readerFor(type)
                    .with(schema)
                    .with(CsvParser.Feature.WRAP_AS_ARRAY)
                    .readValues(data);
            return it.readAll();
        } catch (IOException e) {
            throw new WrongFileFormatException("Wrong game file format");
        }
    }
}

