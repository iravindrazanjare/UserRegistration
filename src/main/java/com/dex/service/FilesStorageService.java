package com.dex.service;


import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

	void init();
    
    void save(MultipartFile file);
    
    Resource load(String filename);
    
    Stream<Path> loadAll();
}
