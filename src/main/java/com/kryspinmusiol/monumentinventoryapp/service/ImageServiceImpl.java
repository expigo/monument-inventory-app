package com.kryspinmusiol.monumentinventoryapp.service;

import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final MonumentRepository monumentRepository;

    public ImageServiceImpl(MonumentRepository monumentRepository) {
        this.monumentRepository = monumentRepository;
    }


    @Override
    public void saveImage(long monumentId, MultipartFile any) {

        // todo: add correct method instead of debug log


        log.debug("We' ve got this!");

        try {
            Monument monument = monumentRepository.findById(monumentId).get();

            Byte[] objBytes = new Byte[any.getBytes().length];

            int i = 0;

            for (byte b : any.getBytes()) {
                objBytes[i++] = b;
            }

            monument.setImage(objBytes);

            monumentRepository.save(monument);
        } catch (IOException ioe) {

            // todo get this exception handled properly
            log.error("Damn, image not loaded");

            ioe.printStackTrace();
        }
    }

}
