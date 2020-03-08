package com.github.jdavies100.debt.controller.helpers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@AutoConfigureMockMvc()
@SpringBootTest
public class PersonMvcBuilder {


    private MockMvc mockMvc;
    private int status;

    public PersonMvcBuilder(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.params = new LinkedMultiValueMap<>();
    }

    private HttpTypes requestType;
    private String endpoint;
    private MultiValueMap<String, String> params;

    public PersonMvcBuilder param(String key, String value) {
        params.add(key, value);
        return this;
    }

    public PersonMvcBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public PersonMvcBuilder queryType(HttpTypes type) {
        this.requestType = type;
        return this;
    }

    public PersonMvcBuilder httpStatus(int status) {
        this.status = status;
        return this;
    }


    public String build() throws Exception {
        return getPerform().andExpect(status().is(status))
            .andReturn().getResponse().getContentAsString();
    }

    private ResultActions getPerform() throws Exception {
        RequestBuilder builder;
        switch (requestType) {
            case GET:
                builder = params.size() == 0 ?
                    get(endpoint) :
                    get(endpoint).params(params);
                break;
            case PUT:
                builder = params.size() == 0 ?
                    put(endpoint) :
                    put(endpoint).params(params);
                break;
            case POST:
                builder = params.size() == 0 ?
                    post(endpoint) :
                    post(endpoint).params(params);
                break;
            case DELETE:
                builder = params.size() == 0 ?
                    delete(endpoint) :
                    delete(endpoint).params(params);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestType);
        }
        return mockMvc.perform(builder);
    }
}
