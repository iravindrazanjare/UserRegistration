package com.dex.serviceimpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dex.service.FilesStorageService;

@Transactional
@Service
public class FilesStorageServiceImpl implements FilesStorageService
{
    private Path root;
    public String userId;
    
    public FilesStorageServiceImpl() {
        root = Paths.get("C:\\Documents_and_Photo\\", new String[0]);
        userId = "ABCD";
    }
    
    @Override
    public void init() {
    }
    
    @Override
    public void save(MultipartFile file) {
        try {
            Path userFolder = root.resolve(userId);
            if (!Files.isDirectory(userFolder, new LinkOption[0])) {
                Files.createDirectory(userFolder, (FileAttribute<?>[])new FileAttribute[0]);
            }
            Files.copy(file.getInputStream(), userFolder.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    
    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(String.valueOf(userId) + "\\" + filename);
            Resource resource = (Resource)new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new RuntimeException("Could not read the file!");
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    
    @Override
    public Stream<Path> loadAll() {
        try {
            Path userFolder = root.resolve(userId);
            return Files.walk(userFolder, 1, new FileVisitOption[0]).filter(path -> !path.equals(userFolder)).map((Function<? super Path, ? extends Path>)userFolder::relativize);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
