package com.scoutcomapss.api.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/18/2024
 */

@RestController
@RequestMapping("/api/scoutcompass/resource")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ResourceController {


    private final ResourceService resourceService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("resource") MultipartFile file) throws IOException {
        String uploadImage = resourceService.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData=resourceService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(imageData);

    }
}
