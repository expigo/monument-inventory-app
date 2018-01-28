package com.kryspinmusiol.monumentinventoryapp.controller;


import com.kryspinmusiol.monumentinventoryapp.controllers.AppController;
import com.kryspinmusiol.monumentinventoryapp.controllers.IndexController;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.service.MonumentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AppControllerTest {

    @Mock
    MonumentService monumentService;

    @Mock
    Model model;

    AppController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new AppController(monumentService);
    }


    @Test
    public void getIndexPage() throws Exception {

        Monument monument = new Monument();
        monument.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        when(monumentService.findById(anyLong())).thenReturn(monument);

        mockMvc.perform(get("/app/show-monument/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("app/show-monument"))
                .andExpect(model().attributeExists("monument"));
    }

}
