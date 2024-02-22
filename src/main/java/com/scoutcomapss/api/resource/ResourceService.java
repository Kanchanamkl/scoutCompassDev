package com.scoutcomapss.api.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {

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



    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Resource> fileData = resourceRepository.findByResourceName(fileName);
        String filePath=fileData.get().getResourceFilePath();
        byte[] resource = Files.readAllBytes(new File(filePath).toPath());
        return resource;
    }

}
