import { API_ROOT } from "../utils/api-config";

export async function getWeatherForecasting(fromDate, toDate, place) {
  const jsonData = {
	  startDate: fromDate,
      endDate: toDate,
      location: place
	};
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(jsonData)
  };
  const response = await fetch(
    `${API_ROOT}/forecasting-weather-service/v1/weather-forecasts`,
    requestOptions
  );
  return response.json();
}

export async function getSalesForecasting(fromDate, toDate, place) {
  const jsonData = {
	  startDate: fromDate,
      endDate: toDate,
      location: place
	};
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(jsonData)
  };
  const response = await fetch(
    `${API_ROOT}/sales-forecast-service/v1/sales-forecasts`,
    requestOptions
  );
  return response.json();
}
