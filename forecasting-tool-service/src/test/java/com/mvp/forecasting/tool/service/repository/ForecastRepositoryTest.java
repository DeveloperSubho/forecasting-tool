//package com.mvp.forecasting.tool.service.repository;
//
//import com.mvp.forecasting.tool.service.entity.Forecasts;
//import com.mvp.forecasting.tool.service.model.SalesForecastRequest;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@DataJpaTest
//public class ForecastRepositoryTest {
//
//    @Mock
//    private ForecastRepository forecastRepository;
//
//    private Forecasts forecasts;
//
//    private List<Forecasts> listOfForecasts = new ArrayList<>();
//
//    private SalesForecastRequest salesForecastRequest;
//
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
//
//    @BeforeEach
//    public void initUseCase() throws ParseException {
//
//        forecasts = forecasts.builder()
//                .date(formatter.parse("2023-02-05 00:00:00"))
//                .location("Hamburg")
//                .forecasted_sales_quantity(394.1765034648572)
//                .build();
//
//        listOfForecasts.add(forecasts);
//
//        salesForecastRequest = salesForecastRequest.builder()
//                .location("Hamburg")
//                .startDate("2023-02-05")
//                .endDate("2023-02-07")
//                .build();
//        forecastRepository.saveAll(listOfForecasts);
//    }
//
//    @AfterEach
//    public void destroyAll(){
//        forecastRepository.deleteAll();
//    }
//
//    @Test
//    void saveAll_success() throws ParseException {
//        List<Forecasts> forecasts = Arrays.asList(
//                new Forecasts(formatter.parse("2023-02-05 00:00:00"),
//                        "Hamburg",
//                        394.1765034648572),
//                new Forecasts(formatter.parse("2023-02-06 00:00:00"),
//                        "Hamburg",
//                        245.1765034648565),
//                new Forecasts(formatter.parse("2023-02-07 00:00:00"),
//                        "Hamburg",
//                        414.1765034648543)
//        );
//
//        Iterable<Forecasts> allForecasts = forecastRepository.saveAll(forecasts);
//
//        AtomicInteger validRecordFound = new AtomicInteger();
//        allForecasts.forEach(forecast -> {
//            if(forecast.getForecasted_sales_quantity()>0){
//                validRecordFound.getAndIncrement();
//            }
//        });
//
//        assertThat(validRecordFound.intValue()).isEqualTo(3);
//    }
//
//
//    // JUnit test for get forecast for location and date operation
//    //@DisplayName("JUnit test for getForecastForLocationAndDate")
//    @Test
//    public void givenLocationAndDates_whenFetchTimelineSalesForecasts_thenReturnForecastList() throws ParseException {
//
//        //given - precondition or setup
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
//
//        // when - action or the behaviour that we are going test
//        List<Forecasts> fetchTimeline = forecastRepository
//                .getForecastForLocationAndDate("Hamburg",
//                        formatter.parse("2023-02-05 00:00:00"),
//                        formatter.parse("2023-02-07 00:00:00"));
//
//        // then - verify the output
//        assertThat(fetchTimeline).isNotNull();
//        assertThat(fetchTimeline.size()).isGreaterThan(0);
//
//    }
//
//}
