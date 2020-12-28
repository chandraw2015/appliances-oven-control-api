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

    @Test
    public void test_StartOvenApi() throws JSONException {

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/start/oven"),
                HttpMethod.GET, entity, String.class);
       String expected ="{\n" +
               "    \"id\": 1,\n" +
               "    \"name\": \"Murphy\",\n" +
               "    \"model\": \"TN-202\",\n" +
               "    \"version\": \"2.0.5\",\n" +
               "    \"ovenState\": \"STARTED\"\n" +
               "}";

                JSONAssert.assertEquals(expected , response.getBody(), false);

    }


    @Test
    public void test_GetOvenStateApi() throws JSONException{

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/state/oven/1"),
                HttpMethod.GET, entity, String.class);

        Assertions.assertEquals("\"STOPPED\"", response.getBody());


    }

    @Test
    public void test_GetOvenProgramsApi() throws JSONException {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/oven/1/programs"),
                HttpMethod.GET, entity, String.class);
        String expected ="{\n" +
                "    \"programs\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Recipe1\",\n" +
                "            \"time\": 6000,\n" +
                "            \"temperature\": 350\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"name\": \"Recipe2\",\n" +
                "            \"time\": 5000,\n" +
                "            \"temperature\": 420\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JSONAssert.assertEquals(expected , response.getBody(), false);
    }

    @Test
    public void test_SetOvenProgramsApi() throws JSONException{

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/start/oven"),
                HttpMethod.GET, entity, String.class);

        ResponseEntity<String> responseProcess = restTemplate.exchange(
                createURLWithPort("/api/oven/1/program/2"),
                HttpMethod.GET, entity, String.class);
        String expected ="{\n" +
                "    \"id\": 1,\n" +
                "    \"program\": {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Recipe2\",\n" +
                "        \"time\": 5000,\n" +
                "        \"temperature\": 420\n" +
                "    },\n" +
                "    \"oven\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Murphy\",\n" +
                "        \"model\": \"TN-202\",\n" +
                "        \"version\": \"2.0.5\",\n" +
                "        \"ovenState\": \"PROCESS\"\n" +
                "    },\n" +
                "    \"processStatus\": \"ACTIVE\"\n" +
                "}";
        JSONAssert.assertEquals(expected , responseProcess.getBody() , true);
    }

    @Test
    public void test_StopOven() throws JSONException{
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/start/oven"),
                HttpMethod.GET, entity, String.class);

        ResponseEntity<String> responseStop = restTemplate.exchange(
                createURLWithPort("/api/stop/oven/1"),
                HttpMethod.GET, entity, String.class);
        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Murphy\",\n" +
                "    \"model\": \"TN-202\",\n" +
                "    \"version\": \"2.0.5\",\n" +
                "    \"ovenState\": \"STOPPED\"\n" +
                "}";

        JSONAssert.assertEquals(expected , responseStop.getBody() , true);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
