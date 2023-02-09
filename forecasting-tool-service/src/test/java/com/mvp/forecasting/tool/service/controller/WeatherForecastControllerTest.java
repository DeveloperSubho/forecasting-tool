package com.mvp.forecasting.tool.service.controller;

import com.mvp.forecasting.tool.service.model.WeatherForecastRequest;
import com.mvp.forecasting.tool.service.service.WeatherForecastService;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(WeatherForecastController.class)
public class WeatherForecastControllerTest {

    private Logger logger = LoggerFactory.getLogger(WeatherForecastControllerTest.class);
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherForecastService weatherForecastServiceMock;

    private JSONObject weatherForecast;

    private JSONObject inputObject;

    @BeforeEach
    public void init() throws JSONException {
        logger.info("Startup");
        inputObject = new JSONObject("{\n" +
                "    \"location\": \"Hamburg\",\n" +
                "    \"startDate\": \"2023-02-05\",\n" +
                "    \"endDate\": \"2023-02-07\"\n" +
                "}");

        weatherForecast = new JSONObject(
                "{\n" +
                        "    \"queryCost\": 1,\n" +
                        "    \"latitude\": 52.516,\n" +
                        "    \"longitude\": 13.3769,\n" +
                        "    \"resolvedAddress\": \"Berlin, Deutschland\",\n" +
                        "    \"address\": \"Berlin\",\n" +
                        "    \"timezone\": \"Europe/Berlin\",\n" +
                        "    \"tzoffset\": 1.0,\n" +
                        "    \"description\": \"Similar temperatures continuing with no rain expected.\",\n" +
                        "    \"days\": [\n" +
                        "        {\n" +
                        "            \"datetime\": \"2023-02-05\",\n" +
                        "            \"datetimeEpoch\": 1675551600,\n" +
                        "            \"tempmax\": 0.5,\n" +
                        "            \"tempmin\": -6.0,\n" +
                        "\t\t\t\"temp\": -2.1,\n" +
                        "            \"conditions\": \"Partially cloudy\",\n" +
                        "            \"description\": \"Partly cloudy throughout the day.\",\n" +
                        "            \"stations\": [\n" +
                        "                \"EDDB\",\n" +
                        "                \"E2835\"\n" +
                        "            ],\n" +
                        "            \"source\": \"comb\",\n" +
                        "            \"hours\": [\n" +
                        "                {\n" +
                        "                    \"datetime\": \"00:00:00\",\n" +
                        "                    \"datetimeEpoch\": 1675551600,\n" +
                        "                    \"temp\": -4.0,\n" +
                        "                    \"stations\": [\n" +
                        "                        \"EDDB\"\n" +
                        "                    ],\n" +
                        "                    \"source\": \"obs\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"datetime\": \"01:00:00\",\n" +
                        "                    \"datetimeEpoch\": 1675555200,\n" +
                        "                    \"temp\": -3.0,\n" +
                        "                    \"stations\": [\n" +
                        "                        \"EDDB\"\n" +
                        "                    ],\n" +
                        "                    \"source\": \"obs\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"datetime\": \"02:00:00\",\n" +
                        "                    \"datetimeEpoch\": 1675558800,\n" +
                        "                    \"temp\": -4.0,\n" +
                        "                    \"stations\": [\n" +
                        "                        \"EDDB\"\n" +
                        "                    ],\n" +
                        "                    \"source\": \"obs\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"datetime\": \"03:00:00\",\n" +
                        "                    \"datetimeEpoch\": 1675562400,\n" +
                        "                    \"temp\": -4.0,\n" +
                        "                    \"stations\": [\n" +
                        "                        \"EDDB\"\n" +
                        "                    ],\n" +
                        "                    \"source\": \"obs\"\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"datetime\": \"2023-02-06\",\n" +
                        "            \"datetimeEpoch\": 1675638000,\n" +
                        "            \"tempmax\": 0.0,\n" +
                        "            \"tempmin\": -3.7,\n" +
                        "            \"temp\": -2.1,\n" +
                        "            \"conditions\": \"Partially cloudy\",\n" +
                        "            \"description\": \"Partly cloudy throughout the day.\",\n" +
                        "            \"hours\": [\n" +
                        "                {\n" +
                        "                    \"datetime\": \"00:00:00\",\n" +
                        "                    \"datetimeEpoch\": 1675638000,\n" +
                        "                    \"temp\": -2.8 \n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"datetime\": \"01:00:00\",\n" +
                        "                    \"datetimeEpoch\": 1675641600,\n" +
                        "                    \"temp\": -3.1\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"alerts\": [],\n" +
                        "    \"stations\": {\n" +
                        "        \"C6421\": {\n" +
                        "            \"distance\": 3799.0,\n" +
                        "            \"latitude\": 52.532,\n" +
                        "            \"longitude\": 13.427,\n" +
                        "            \"useCount\": 0,\n" +
                        "            \"id\": \"C6421\",\n" +
                        "            \"name\": \"CW6421 Berlin DE\",\n" +
                        "            \"quality\": 0,\n" +
                        "            \"contribution\": 0.0\n" +
                        "        },\n" +
                        "        \"ETSH\": {\n" +
                        "            \"distance\": 84127.0,\n" +
                        "            \"latitude\": 51.77,\n" +
                        "            \"longitude\": 13.18,\n" +
                        "            \"useCount\": 0,\n" +
                        "            \"id\": \"ETSH\",\n" +
                        "            \"name\": \"ETSH\",\n" +
                        "            \"quality\": 2,\n" +
                        "            \"contribution\": 0.0\n" +
                        "        },\n" +
                        "        \"EDDB\": {\n" +
                        "            \"distance\": 17985.0,\n" +
                        "            \"latitude\": 52.38,\n" +
                        "            \"longitude\": 13.52,\n" +
                        "            \"useCount\": 0,\n" +
                        "            \"id\": \"EDDB\",\n" +
                        "            \"name\": \"EDDB\",\n" +
                        "            \"quality\": 50,\n" +
                        "            \"contribution\": 0.0\n" +
                        "        },\n" +
                        "        \"E2835\": {\n" +
                        "            \"distance\": 11812.0,\n" +
                        "            \"latitude\": 52.412,\n" +
                        "            \"longitude\": 13.34,\n" +
                        "            \"useCount\": 0,\n" +
                        "            \"id\": \"E2835\",\n" +
                        "            \"name\": \"EW2835 Berlin DE\",\n" +
                        "            \"quality\": 0,\n" +
                        "            \"contribution\": 0.0\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"currentConditions\": {\n" +
                        "        \"datetime\": \"12:15:31\",\n" +
                        "        \"datetimeEpoch\": 1675595731,\n" +
                        "        \"temp\": 0.5,\n" +
                        "        \"conditions\": \"Partially cloudy\",\n" +
                        "        \"stations\": [\n" +
                        "            \"ETSH\",\n" +
                        "            \"C6421\",\n" +
                        "            \"EDDB\"\n" +
                        "        ],\n" +
                        "        \"source\": \"obs\"\n" +
                        "    }\n" +
                        "}"
        );
    }

    @Test
    public void givenCorrectPICall_whenWeatherForecastsIsReceived_then200IsReceived() throws Exception {

        // Given
        when(weatherForecastServiceMock.timelineRequestHttpClient(any(WeatherForecastRequest.class)))
                .thenReturn(String.valueOf(weatherForecast));

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/forecasting-weather-service/v1/weather-forecasts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(String.valueOf(inputObject)))
                .andReturn();

        int status = result.getResponse().getStatus();
        logger.info("Status: "+ status);

        // Then
        assertEquals(HttpStatus.SC_OK, status);

    }

    @Test
    public void givenWrongAPICall_whenWeatherForecastsIsReceived_then404IsReceived() throws Exception {

        // Given
        when(weatherForecastServiceMock.timelineRequestHttpClient(any(WeatherForecastRequest.class)))
                .thenReturn(String.valueOf(inputObject));

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/forecasting-weather-service/v1/weather-forecast")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(weatherForecast)))
                .andReturn();

        int status = result.getResponse().getStatus();
        logger.info("Status: "+ status);

        // Then
        assertEquals(HttpStatus.SC_NOT_FOUND, status);
    }

    @Test
    public void givenPostAPICall_whenWeatherForecastsIsReceived_thenValidJSONIsReceived() throws Exception {

        // Given
        when(weatherForecastServiceMock.timelineRequestHttpClient(any(WeatherForecastRequest.class)))
                .thenReturn(String.valueOf(weatherForecast));

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/forecasting-weather-service/v1/weather-forecasts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(inputObject)))
                .andReturn();

        int status = result.getResponse().getStatus();
        logger.info("Status: "+ status);

        // Then
        JSONAssert.assertEquals(String.valueOf(weatherForecast), result.getResponse()
                .getContentAsString(), true);    }

}
