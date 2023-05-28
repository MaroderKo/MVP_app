package com.mvp.service;


import java.io.IOException;

public interface FileReaderService {
    String getData(String path) throws IOException;
}
