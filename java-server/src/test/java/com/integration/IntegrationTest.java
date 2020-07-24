package com.integration;

import com.server.JavaServerApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaServerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Tag(value = "integrationTests")
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    @Test
    public void testRegistration()
    {
        final String registerUrl = "http://localhost:11111/registration";
        String ts = String.format("%06d", new Random().nextInt(99999));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.MULTIPART_FORM_DATA_VALUE);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", ts);
        map.add("password", ts);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> out = restTemplate.postForEntity(registerUrl, request, String.class);

        Assert.assertEquals(302, out.getStatusCodeValue());
    }

    @Test
    public void testLogin()
    {
        // Register
        final String registerUrl = "http://localhost:11111/registration";
        String ts = "test" + String.format("%06d", new Random().nextInt(99999));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.MULTIPART_FORM_DATA_VALUE);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", ts);
        map.add("password", ts);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> out = restTemplate.postForEntity(registerUrl, request, String.class);
        Assert.assertEquals(302, out.getStatusCodeValue());

        // Login

        final String loginUrl = "http://localhost:11111/login";
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("Accept", MediaType.MULTIPART_FORM_DATA_VALUE);

        MultiValueMap<String, String> authMap = new LinkedMultiValueMap<String, String>();
        authMap.add("username", ts);
        authMap.add("password", ts);
        HttpEntity<MultiValueMap<String, String>> authRequest = new HttpEntity<MultiValueMap<String, String>>(authMap, authHeaders);

        ResponseEntity<String> authOut = restTemplate.postForEntity(loginUrl, authRequest, String.class);

        Assert.assertEquals(302, authOut.getStatusCodeValue());
    }
}
