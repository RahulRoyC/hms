package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.entity.Images;
import com.hms.entity.Property;
import com.hms.service.BucketService;
import com.hms.service.ImagesService;
import com.hms.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {

    private BucketService bucketService;
    private PropertyService propertyService;
    private ImagesService imagesService;

    public ImageController(BucketService bucketService, PropertyService propertyService,ImagesService imagesService) {
        this.bucketService = bucketService;
        this.propertyService = propertyService;
        this.imagesService = imagesService;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}", consumes =
            MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file,
                                        @PathVariable String bucketName,
                                        @PathVariable Long propertyId,
                                        @AuthenticationPrincipal AppUser user
    ) {
        Property property = propertyService.getProperty(propertyId);

        String imageUrl = bucketService.uploadFile(file, bucketName);
        Images images = new Images();
        images.setProperty(property);
        images.setUrl(imageUrl);
        Images savedImages = imagesService.saveImages(images);

        return new ResponseEntity<>(savedImages, HttpStatus.OK);
    }
//http://localhost:8080/api/v1/images/getImages
    @GetMapping("/{id}")
    public ResponseEntity<List<Images>> getImages(@PathVariable Long id){
        Property property = propertyService.getProperty(id);
        List<Images> allImages = propertyService.getAllImages(property);
        return new ResponseEntity<>(allImages, HttpStatus.OK);
    }

}
