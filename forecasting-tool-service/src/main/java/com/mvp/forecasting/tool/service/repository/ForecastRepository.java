package com.mvp.forecasting.tool.service.repository;

import com.mvp.forecasting.tool.service.entity.Forecasts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecasts, Date> {

    @Query(value = "SELECT distinct * FROM oneglass.forecasts", nativeQuery = true)
    List<Forecasts> getAllForecasts();

    @Query(value = "SELECT distinct * FROM oneglass.forecasts " +
            "WHERE location= :location", nativeQuery = true)
    List<Forecasts> getForecastForLocation(String location);

    @Query(value = "SELECT distinct * FROM oneglass.forecasts " +
            "WHERE date between :startDate and :endDate", nativeQuery = true)
    List<Forecasts> getForecastForDate(Date startDate, Date endDate);

    @Query(value = "SELECT * FROM oneglass.forecasts " +
            "WHERE location= :location and date between :startDate and :endDate", nativeQuery = true)
    List<Forecasts> getForecastForLocationAndDate(String location, Date startDate, Date endDate);
}
