package com.kryspinmusiol.monumentinventoryapp.controllers;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.service.ImageService;
import com.kryspinmusiol.monumentinventoryapp.service.MonumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final MonumentService monumentService;
    private final ImageService imageService;

    public ImageController(MonumentService monumentService, ImageService imageService) {
        this.monumentService = monumentService;
        this.imageService = imageService;
    }

    @GetMapping("app/{id}/image")
    public String getImageForm(@PathVariable String id, Model model) {
        model.addAttribute("monument", monumentService.findMonumentCommandById(Long.valueOf(id)));

        return "app/app-image-form";
    }


    @PostMapping("app/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("image")MultipartFile multipartFile) {


        imageService.saveImage(Long.valueOf(id), multipartFile);

        return "redirect:/app/" + id + "/app-show-monument";
    }

    @GetMapping("app/{id}/img")
    public void renderImage(@PathVariable String id, HttpServletResponse response) throws IOException {

        MonumentCommand monumentCommand = monumentService.findMonumentCommandById(Long.valueOf(id));

        if(monumentCommand.getImage() != null) {

            byte[] bytes = new byte[monumentCommand.getImage().length];

            int i = 0;

            for (Byte b : monumentCommand.getImage()) {
                bytes[i++] = b;
            }

            response.setContentType("image/jpeg");

            InputStream is = new ByteArrayInputStream(bytes);

            org.apache.tomcat.util.http.fileupload.IOUtils.copy(is, response.getOutputStream());
        }
    }

}
