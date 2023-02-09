package com.mvp.forecasting.tool.service.service.impl;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.mvp.forecasting.tool.service.model.WeatherForecastRequest;
import com.mvp.forecasting.tool.service.service.WeatherForecastService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Value("${weather.api_key}")
    private String weather_api_key;

    @Value("${weather.url}")
    private String weather_url;
    @Override
    public String timelineRequestHttpClient(WeatherForecastRequest weatherForecastRequest) throws Exception {
        // set up the end point
        String apiEndPoint = weather_url;

        String location = weatherForecastRequest.getLocation();
        String startDate = weatherForecastRequest.getStartDate();
        String endDate = weatherForecastRequest.getEndDate();

        String unitGroup = "metric";
        String apiKey = weather_api_key;

        StringBuilder requestBuilder = new StringBuilder(apiEndPoint);
        requestBuilder.append(URLEncoder.encode(location, StandardCharsets.UTF_8.toString()));

        if (startDate != null && !startDate.isEmpty()) {
            requestBuilder.append("/").append(startDate);
            if (endDate != null && !endDate.isEmpty()) {
                requestBuilder.append("/").append(endDate);
            }
        }

        URIBuilder builder = new URIBuilder(requestBuilder.toString());

        builder.setParameter("unitGroup", unitGroup)
                .setParameter("key", apiKey);

        HttpGet get = new HttpGet(builder.build());

        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = httpclient.execute(get);

        String rawResult = null;
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
                return null;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rawResult = EntityUtils.toString(entity, Charset.forName("utf-8"));
            }

        } finally {
            response.close();
        }

        parseTimelineJson(rawResult);
        return rawResult;

    }

    private static void parseTimelineJson(String rawResult) {

        if (rawResult == null || rawResult.isEmpty()) {
            System.out.printf("No raw data%n");
            return;
        }

        JSONObject timelineResponse = new JSONObject(rawResult);

        ZoneId zoneId = ZoneId.of(timelineResponse.getString("timezone"));

        System.out.printf("Weather data for: %s%n", timelineResponse.getString("resolvedAddress"));

        JSONArray values = timelineResponse.getJSONArray("days");

        System.out.printf("Date\tMaxTemp\tMinTemp\tPrecip\tSource%n");
        for (int i = 0; i < values.length(); i++) {
            JSONObject dayValue = values.getJSONObject(i);

            ZonedDateTime datetime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(dayValue.getLong("datetimeEpoch")),
                    zoneId);

            double maxtemp = dayValue.getDouble("tempmax");
            double mintemp = dayValue.getDouble("tempmin");
            double pop = dayValue.getDouble("precip");
            String source = dayValue.getString("source");
            System.out.printf("%s\t%.1f\t%.1f\t%.1f\t%s%n", datetime.format(DateTimeFormatter.ISO_LOCAL_DATE), maxtemp,
                    mintemp, pop, source);
        }
    }

}
