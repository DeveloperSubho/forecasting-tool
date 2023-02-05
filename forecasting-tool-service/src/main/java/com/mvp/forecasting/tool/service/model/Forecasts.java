package com.mvp.forecasting.tool.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "forecasts")
@Getter
@Setter
public class Forecasts implements Serializable {

    @Id
    private Date date;

    private String location;

    private double forecasted_sales_quantity;

}
