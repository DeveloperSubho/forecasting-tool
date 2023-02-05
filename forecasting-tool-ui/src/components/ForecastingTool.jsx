import { Button, TextField } from "@material-ui/core";
import MaterialTable from "material-table";
import React from "react";

class ForecastingTool extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            startDate: '',
            endDate: '',
            weatherData: [],
            salesData: [],
            showAlerts: false
        };
    }

    setStartDate(e) {
        this.setState({
            startDate: e.target.value
        });
    }

    setEndDate(e) {
        this.setState({
            endDate: e.target.value
        });
    }

    fetchHamburgData() {
        const postData = {
            'startDate': this.state.startDate,
            'endDate': this.state.endDate,
            'location': 'Hamburg'
        };  
        
        fetch('http://localhost:8081/forecasting-weather-service/v1/weather-forecasts', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData)})
        .then(response => {
          if(response.ok) return response.json();
          throw new Error('Request failed.');
        })
        .then(data => {
            this.setState({
                weatherData: data
            });;
        })
        .catch(error => {
          console.log(error);
        });
        
        fetch('http://localhost:8081/sales-forecast-service/v1/sales-forecasts', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData)})
        .then(response => {
          if(response.ok) return response.json();
          throw new Error('Request failed.');
        })
        .then(data => {
            this.setState({
                salesData: data
            });;
        })
        .catch(error => {
          console.log(error);
        });
    }

    fetchMunichData() {
        const postData = {
            'startDate': this.state.startDate,
            'endDate': this.state.endDate,
            'location': 'Munich'
        };  
        fetch('http://localhost:8081/forecasting-weather-service/v1/weather-forecasts', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData)})
        .then(response => {
        if(response.ok) return response.json();
        throw new Error('Request failed.');
        })
        .then(data => {
            this.setState({
                weatherData: data
            });;
        })
        .catch(error => {
        console.log(error);
        });
        fetch('http://localhost:8081/sales-forecast-service/v1/sales-forecasts', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData)})
        .then(response => {
        if(response.ok) return response.json();
        throw new Error('Request failed.');
        })
        .then(data => {
            this.setState({
                salesData: data
            });;
        })
        .catch(error => {
        console.log(error);
        });
    }

    fetchTwoWeeksHamburgData() {
        let today = new Date();
        var fortnightAway = new Date(Date.now() + 12096e5);
        const postData = {
            'startDate': today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate(),
            'endDate': fortnightAway.getFullYear()+'-'+(fortnightAway.getMonth()+1)+'-'+fortnightAway.getDate(),
            'location': 'Hamburg'
        };  
        
        fetch('http://localhost:8081/forecasting-weather-service/v1/weather-forecasts', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData)})
        .then(response => {
          if(response.ok) return response.json();
          throw new Error('Request failed.');
        })
        .then(data => {
            this.setState({
                weatherData: data
            });;
        })
        .catch(error => {
          console.log(error);
        });
        
        fetch('http://localhost:8081/sales-forecast-service/v1/sales-forecasts', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData)})
        .then(response => {
          if(response.ok) return response.json();
          throw new Error('Request failed.');
        })
        .then(data => {
            this.setState({
                salesData: data
            });;
        })
        .catch(error => {
          console.log(error);
        });
    }

    fetchTwoWeeksMunichData() {
        let today = new Date();
        var fortnightAway = new Date(Date.now() + 12096e5);
        const postData = {
            'startDate': today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate(),
            'endDate': fortnightAway.getFullYear()+'-'+(fortnightAway.getMonth()+1)+'-'+fortnightAway.getDate(),
            'location': 'Munich'
        };  
        fetch('http://localhost:8081/forecasting-weather-service/v1/weather-forecasts', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData)})
        .then(response => {
        if(response.ok) return response.json();
        throw new Error('Request failed.');
        })
        .then(data => {
            this.setState({
                weatherData: data
            });;
        })
        .catch(error => {
        console.log(error);
        });
        
        fetch('http://localhost:8081/sales-forecast-service/v1/sales-forecasts', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData)})
        .then(response => {
        if(response.ok) return response.json();
        throw new Error('Request failed.');
        })
        .then(data => {
            this.setState({
                salesData: data
            });;
        })
        .catch(error => {
        console.log(error);
        });
    }

    showAlerts() {
        this.setState({
            showAlerts: true
        });
    }

    render() {
      return (
        <div id="parent-div">
            <div className="row">
                <div className="col-md-6 date-fields">
                    <TextField id="outlined-basic" 
                        label="Start Date" variant="outlined" 
                        value={this.state.startDate}
                        onChange={this.setStartDate.bind(this)}
                    />
                    <TextField id="outlined-basic" label="End Date" 
                        variant="outlined"
                        value={this.state.endDate}
                        onChange={this.setEndDate.bind(this)}
                    />
                </div>
                <div className="col-md-6 location-buttton">
                    <Button variant="contained" onClick={() => {this.fetchHamburgData();}}>Hamburg</Button>
                    <Button variant="outlined" onClick={() => {this.fetchMunichData();}}>Munich</Button>
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-md-6">
                <Button variant="contained" onClick={() => {this.fetchTwoWeeksHamburgData();}}>Two Weeks Hamburg Forecasting</Button>
                </div>
                <div className="col-md-6">
                    <Button variant="outlined" onClick={() => {this.fetchTwoWeeksMunichData();}}>Two Weeks Munich Forecasting</Button>
                </div>
            </div>
            <br />
            <div className="row">
            <div className="col-md-4"></div>
            <div className="col-md-4">
                <Button variant="contained" onClick={() => {this.showAlerts();}}>Generate Alert Data</Button>
                </div>
            </div>
            <div className="col-md-4"></div>
            <br />
            <div className="row">
                <div className="col-md-6">
                    {
                        this.state.weatherData && 
                        <MaterialTable
                        title="Weather Forecasting Information"
                        options={{
                            search: false,
                            paging: false
                        }}
                        columns={[
                            { title: "Date", field: "datetime" },
                            { title: "Conditions", field: "conditions" },
                            { title: "Max Temp", field: "tempmax" },
                            { title: "Min Temp", field: "tempmin" }
                        ]}
                        data={this.state.weatherData.days}
                    />
                    }
                
                </div>
                <div className="col-md-6">
                    <MaterialTable
                        title="Sales Forecasting Information"
                        options={{
                            search: false,
                            paging: false
                        }}
                        columns={[
                            { title: "Date", field: "date" },
                            { title: "Location", field: "location" },
                            { title: "Forecasted Sales Quantity", field: "forecasted_sales_quantity" }
                        ]}
                        data={this.state.salesData}
                    />
                </div>
            </div>
            <br />
            <div className="row">
                <div className="col-md-12">
                    {
                        this.state.showAlerts && 
                        <MaterialTable
                        title="Weather Forecasting Alerts"
                        options={{
                            search: false,
                            paging: false
                        }}
                        columns={[
                            { title: "Event", field: "event" },
                            { title: "Description", field: "description" },
                            { title: "Ends", field: "ends" },
                            { title: "Headline", field: "headline" },
                            { title: "Link", field: "link" }
                        ]}
                        data={this.state.weatherData.alerts}
                    />
                    }
                
                </div>
            </div>
        </div>
		);
    }
  }

export default ForecastingTool;