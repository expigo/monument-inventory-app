package com.kryspinmusiol.monumentinventoryapp.controller;

import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.controllers.ControllerGlobalExceptionHandler;
import com.kryspinmusiol.monumentinventoryapp.controllers.ImageController;
import com.kryspinmusiol.monumentinventoryapp.service.ImageService;
import com.kryspinmusiol.monumentinventoryapp.service.MonumentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    ImageController imageController;

    @Mock
    private MonumentService monumentService;

    @Mock
    ImageService imageService;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(monumentService, imageService);

    }

    @Test
    public void testGetUploadForm() throws Exception {

        // given
        MonumentCommand monumentCommand = new MonumentCommand();
        monumentCommand.setId(8L);

        when(monumentService.findMonumentCommandById(anyLong())).thenReturn(monumentCommand);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();

        // when
        mockMvc.perform(get("/app/8/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("monument"));

        verify(monumentService, times(1)).findMonumentCommandById(anyLong());
    }

    @Test
    public void testImagePost() throws Exception {

        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.txt", "text/plain","Politechnika".getBytes());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();

        mockMvc.perform(multipart("/app/8/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/app/8/app-show-monument"));

        verify(imageService, times(1)).saveImage(anyLong(), any());
    }

    @Test
    public void testGetImageIdNumberFormatException() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).setControllerAdvice(new ControllerGlobalExceptionHandler()).build();

        mockMvc.perform(get("/app/dummy/img"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error400"));

    }
}