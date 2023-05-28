package com.mvp.service;

import java.util.List;

public interface CsvObjectMapperService {
    <T> List<T> decode(String data, Class<T> type);
}
