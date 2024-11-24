package com.hms.service;

import com.hms.entity.Images;
import com.hms.repository.ImagesRepository;
import org.springframework.stereotype.Service;

@Service
public class ImagesService {

    private ImagesRepository imagesRepository;

    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public Images saveImages(Images images) {
        Images save = imagesRepository.save(images);
        return save;
    }
}
