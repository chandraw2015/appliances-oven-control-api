package com.electric.appliances.oven.controller;


import com.electric.appliances.oven.OvenControlApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OvenControlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OvenControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate;
    HttpHeaders headers;
    HttpEntity<String> entity;


   @BeforeEach
   public void setUp(){

        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
        entity = new HttpEntity<>(null , headers);
   }


}
