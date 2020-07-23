package com.integration;

import com.server.JavaServerApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= JavaServerApplication.class)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegistration() throws URISyntaxException
    {
        final String baseUrl = "http://localhost:11111/registration";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.MULTIPART_FORM_DATA_VALUE);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", "user");
        map.add("password", "pass");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> out = restTemplate.postForEntity(baseUrl, request, String.class);

        Assert.assertEquals(302, out.getStatusCodeValue());
    }

    @Test
    public void testLogin() throws URISyntaxException
    {
        final String baseUrl = "http://localhost:11111/login";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.MULTIPART_FORM_DATA_VALUE);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", "user");
        map.add("password", "pass");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> out = restTemplate.postForEntity(baseUrl, request, String.class);

        Assert.assertEquals(302, out.getStatusCodeValue());
    }
}
