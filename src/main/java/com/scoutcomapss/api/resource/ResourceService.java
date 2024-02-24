package com.scoutcomapss.api.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author : Kanchana Kalansooriya
 * @since 2/19/2024
 */


@Service
public class ResourceService {

   @Autowired
    private  ResourceRepository resourceRepository;



    private  final String RESOURCE_PATH ="./resources/";


    public String uploadResouceToFileSystem(MultipartFile file) throws IOException {

        File resourceFile = new File(RESOURCE_PATH);
        if(!resourceFile.exists()){
            resourceFile.mkdir();
        }

        String filePath= RESOURCE_PATH +file.getOriginalFilename();

        Resource resource = Resource.builder()
                .resourceName(file.getOriginalFilename())
                .resourceType(file.getContentType())
                .resourceFilePath(filePath).build();


                resourceRepository.save(resource);

        file.transferTo(new File(filePath).toPath());

        if (resource != null) {
            return "resource uploaded successfully : " + filePath;
        }
        return null;
    }



    public byte[] downloadResouceFromFileSystem(String fileName) throws IOException {
        Optional<Resource> fileData = resourceRepository.findByResourceName(fileName);
        String filePath=fileData.get().getResourceFilePath();
        byte[] resource = Files.readAllBytes(new File(filePath).toPath());
        return resource;
    }

    public boolean deleteResource(String fileName){
        Optional<Resource> resourceOptional = resourceRepository.findByResourceName(fileName);
        if (resourceOptional.isPresent()) {
            Resource resource = resourceOptional.get();
            resourceRepository.delete(resource);
        } else {
            return false;
        }

        Path filePath = Paths.get(RESOURCE_PATH + fileName);
        try {
            Files.delete(filePath);
            return true; // Delete successful
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Delete failed
        }
    }

}
