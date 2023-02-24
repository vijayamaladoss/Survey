//package com.boa.cde.survey.controller;
//
//import com.boa.cde.survey.service.CategoryService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWireMock(port = 0)
//public class CategoryControllerIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @Before
//    public void setup() {
//        // Configure WireMock stubs
//        wireMockServer.stubFor(get(urlPathMatching("/categorys/.*/questions"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("[{\"id\":1,\"description\":\"What is your name?\",\"answerOptions\":[{\"id\":1,\"description\":\"John\"},{\"id\":2,\"description\":\"Jane\"}]},{\"id\":2,\"description\":\"How old are you?\",\"answerOptions\":[{\"id\":3,\"description\":\"Under 18\"},{\"id\":4,\"description\":\"18-30\"},{\"id\":5,\"description\":\"Over 30\"}]}]")));
//    }
//
//    @Test
//    public void testGetAllQuestions() throws Exception {
//        given().port(port)
//                .when().get("/categorys/questions")
//                .then().assertThat().statusCode(200)
//                .and().assertThat().contentType("application/json")
//                .and().assertThat().body(equalTo("[{\"id\":1,\"description\":\"What is your name?\",\"answerOptions\":[{\"id\":1,\"description\":\"John\"},{\"id\":2,\"description\":\"Jane\"}]},{\"id\":2,\"description\":\"How old are you?\",\"answerOptions\":[{\"id\":3,\"description\":\"Under 18\"},{\"id\":4,\"description\":\"18-30\"},{\"id\":5,\"description\":\"Over 30\"}]}]")));
//    }
//}
