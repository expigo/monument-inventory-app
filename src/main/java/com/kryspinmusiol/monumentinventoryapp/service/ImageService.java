package com.kryspinmusiol.monumentinventoryapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void saveImage(long monumentId, MultipartFile any);


}
