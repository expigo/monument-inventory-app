package com.kryspinmusiol.monumentinventoryapp.service;

import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.repository.MonumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ImageServiceImplTest {

    private static final Long MONUMENT_ID = 8L;


    @Mock
    MonumentRepository monumentRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(monumentRepository);

    }

    @Test
    public void saveImage() throws Exception {

        // given
        MultipartFile multipartFile = new MockMultipartFile("image", "test.txt", "text/plain", "Kryspin Musiol".getBytes());

        Monument monument = new Monument();
        monument.setId(MONUMENT_ID);
        Optional<Monument> monumentOptional = Optional.of(monument);

        when(monumentRepository.findById(anyLong())).thenReturn(monumentOptional);

        ArgumentCaptor<Monument> monumentArgumentCaptor = ArgumentCaptor.forClass(Monument.class);

        // when
        imageService.saveImage(MONUMENT_ID, multipartFile);


        // then
        verify(monumentRepository, times(1)).save(monumentArgumentCaptor.capture());
        Monument monumentSaved = monumentArgumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, monumentSaved.getImage().length);
    }

}