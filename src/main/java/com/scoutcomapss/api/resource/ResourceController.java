package com.scoutcomapss.api.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

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
    public ResponseEntity<?> uploadResourceToFIleSystem(@RequestParam("resource") MultipartFile file) throws IOException {
        String uploadImage = resourceService.uploadResouceToFileSystem(file);
        if(uploadImage != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(uploadImage);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Resource already exists!");
        }

    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadResourceFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = resourceService.downloadResouceFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(imageData);

    }


    @GetMapping("/resourceList")
    public ResponseEntity<?> getResourceList() {
        ArrayList<Resource> resourceArrayList = resourceService.getResourceList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resourceArrayList);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> deleteResourceFromFileSystem(@PathVariable String fileName) {
        boolean deletionStatus = resourceService.deleteResource(fileName);

        if (deletionStatus) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Resource deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Resource not found or unable to delete");
        }
    }

    @GetMapping("/count")
    public Long countAllResources() {
        return resourceService.countAllResources();
    }
}
