package com.kryspinmusiol.monumentinventoryapp.controller;


import com.kryspinmusiol.monumentinventoryapp.command.MonumentCommand;
import com.kryspinmusiol.monumentinventoryapp.controllers.AppController;
import com.kryspinmusiol.monumentinventoryapp.controllers.ControllerGlobalExceptionHandler;
import com.kryspinmusiol.monumentinventoryapp.controllers.IndexController;
import com.kryspinmusiol.monumentinventoryapp.exception.NotFoundException;
import com.kryspinmusiol.monumentinventoryapp.model.Monument;
import com.kryspinmusiol.monumentinventoryapp.service.MonumentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void testGetMonument() throws Exception {

        Monument monument = new Monument();
        monument.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        when(monumentService.findById(anyLong())).thenReturn(monument);

        mockMvc.perform(get("/app/1/app-show-monument"))
                .andExpect(status().isOk())
                .andExpect(view().name("app/app-show-monument"))
                .andExpect(model().attributeExists("monument"));
    }


    @Test
    public void testGetMonumentNotFound() throws Exception {

        when(monumentService.findById(anyLong())).thenThrow(NotFoundException.class);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/app/8/app-show-monument"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error404"));
    }

    @Test
    public void testGetMonumentNumberFormatException() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ControllerGlobalExceptionHandler()).build();

        mockMvc.perform(get("/app/dummyStringInput/app-show-monument"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error400"));
    }

    @Test
    public void testGetAddMonument() throws Exception {
        MonumentCommand monumentCommand = new MonumentCommand();

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/app/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("app/app-add-monument"))
                .andExpect(model().attributeExists("monument"));
    }

    @Test
    public void testSaveMonumentPost() throws Exception {

        MonumentCommand monumentCommand = new MonumentCommand();
        monumentCommand.setId(8L);

        when(monumentService.saveMonumentCommand(any())).thenReturn(monumentCommand);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("/app/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "any string")
                .param("description", "any string")
                .param("timeOfOrigin", "any string")
                .param("formsOfProtection", "any string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/app/8/app-show-monument"));

    }

    @Test
    public void testUpdateMonument() throws Exception {
        MonumentCommand monumentCommand = new MonumentCommand();
        monumentCommand.setId(8L);

        when(monumentService.findMonumentCommandById(anyLong())).thenReturn(monumentCommand);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/app/8/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("app/app-add-monument"))
                .andExpect(model().attributeExists("monument"));
    }

    @Test
    public void testDeleteMonument() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/app/8/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/app/all"));

        verify(monumentService, times(1)).deleteById(anyLong());
    }

    @Test
    public void testValidationFailPost() throws Exception {

        MonumentCommand monumentCommand = new MonumentCommand();
        monumentCommand.setId(8L);

        when(monumentService.saveMonumentCommand(any())).thenReturn(monumentCommand);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("/app/save").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("monument"))
                .andExpect(view().name("app/app-add-monument"));


    }
}
