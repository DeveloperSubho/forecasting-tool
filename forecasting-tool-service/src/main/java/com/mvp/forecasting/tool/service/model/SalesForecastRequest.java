package com.mvp.forecasting.tool.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SalesForecastRequest {

    private String location;
    private String startDate;
    private String endDate;
}