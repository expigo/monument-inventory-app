package com.kryspinmusiol.monumentinventoryapp.controller;

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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class IndexControllerTest {

    @Mock
    MonumentService monumentService;

    @Mock
    Model model;

    IndexController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(monumentService);
    }

    @Test
    public void testMockMvc() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    // BDD
    @Test
    public void getIndexPage() {

        // given
        List<Monument> monuments = new ArrayList<>();
        monuments.add(new Monument());

        Monument monument = new Monument();
        monument.setId(1L);

        monuments.add(monument);

        when(monumentService.getMonuments()).thenReturn(monuments);

        ArgumentCaptor<List<Monument>> listArgumentCaptor = ArgumentCaptor.forClass(List.class);

        // when
        String viewName = controller.getIndexPage(model);

        // then
        assertEquals("index", viewName);
        verify(monumentService, times(1)).getMonuments();
        verify(model, times(1)).addAttribute(eq("monuments"), listArgumentCaptor.capture());
        List<Monument> monumentList = listArgumentCaptor.getValue();
        assertEquals(2, monumentList.size());
    }


}
