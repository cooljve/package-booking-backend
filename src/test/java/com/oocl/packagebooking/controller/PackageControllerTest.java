package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.service.PackageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PackageController.class)
class PackageControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private PackageService service;

  @Test
  void should_get_all_packages() throws Exception {
    when(service.getAll()).thenReturn(null);

    ResultActions result = mvc.perform(get("/manage/packages"));

    result.andExpect(status().isOk());
    verify(service).getAll();
  }

  @Test
  void should_get_not_take_packages() throws Exception {
    when(service.getPackagesByStatus(anyString())).thenReturn(null);

    ResultActions result = mvc.perform(get("/manage/packages?status=notTake"));

    result.andExpect(status().isOk());
    verify(service).getPackagesByStatus(anyString());
  }

  @Test
  void should_update_package() throws Exception {
    Package pack = new Package();
    ObjectMapper mapper = new ObjectMapper();
    String packJson = mapper.writeValueAsString(pack);

    ResultActions result = mvc.perform(put("/manage/packages/{id}",1)
    .contentType(MediaType.APPLICATION_JSON)
    .content(packJson));

    result.andExpect(status().isOk());
    verify(service).confirmReceived(anyInt(), any());
  }

  @Test
  void should_patch_package() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String packJson = mapper.writeValueAsString(new Date());

    String orderNumber = "123456";
    ResultActions result = mvc.perform(patch("/manage/packages/{orderNumber}", orderNumber)
    .contentType(MediaType.APPLICATION_JSON)
    .content(packJson));

    result.andExpect(status().isOk());
    verify(service).updatePackageWithBookDate(anyString(),any());
  }

  @Test
  void should_add_package() throws Exception {
    Package pack = new Package();
    ObjectMapper mapper = new ObjectMapper();
    String packJson = mapper.writeValueAsString(pack);
    when(service.add(any())).thenReturn(null);

    ResultActions result = mvc.perform(post("/manage/packages")
        .contentType(MediaType.APPLICATION_JSON)
        .content(packJson));

    result.andExpect(status().isOk());
    verify(service).add(any());
  }

}