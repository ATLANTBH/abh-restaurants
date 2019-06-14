package com.atlantbh.devdays.demo.abh.restaurants.web.controller;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by Kenan Klisura on 2019-06-13.
 *
 * @author Kenan Klisura
 */
@Controller
public class FileController {
    private static final String IMAGE_API_PATH = "/api/v1/file/image/";

    @ResponseBody
    @PostMapping("/api/v1/file/upload")
    public UploadResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        final String rootPath = Paths.get(".").toAbsolutePath().normalize().toString();

        final File storageRoot = new File(rootPath, ".storage");
        if (!storageRoot.exists()) {
            storageRoot.mkdir();
        }

        final String fileName = UUID.randomUUID().toString();
        final Path uploadFilePath = storageRoot.toPath().resolve(fileName);

        final File uploadFile = uploadFilePath.toFile();
        uploadFile.createNewFile();

        file.transferTo(uploadFile);

        return new UploadResponse(fileName);
    }

    @GetMapping(IMAGE_API_PATH + "{image}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("image") String image) {
        Resource file = new PathResource(".storage/" + image);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(file);
    }

    public static class UploadResponse {
        private String path;

        UploadResponse(String fileName) {
            this.path = IMAGE_API_PATH + fileName;
        }

        public String getPath() {
            return path;
        }
    }
}
