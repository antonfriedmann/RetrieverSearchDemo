package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestConsumer {
    RestTemplate restTemplate = new RestTemplate();

    public List<Document> getPosts() {
        String URI = "https://www.retriever-info.com/doccyexample/pulse.json";

        ResponseEntity<ResponseData> responseEntity = restTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                ResponseData.class
        );
        
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ResponseData responseData = responseEntity.getBody();
            if (responseData != null) {
                return responseData.getDocuments();
            }
        }
        
        return new ArrayList<>(); // Return an empty list in case of error
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {
        private List<Document> documents;

        public List<Document> getDocuments() {
            return documents;
        }

        public void setDocuments(List<Document> documents) {
            this.documents = documents;
        }
    }
}
