# forecasting-tool
A web application for helping clients to see forecasted sales and weather.
The approach to this project was simple. I first developed the backend API so that I can conect to respective API's and database to fetch required forecast data. Then once the API's were ready, I started working on UI to connect to the backend API service.

_The project Postman collection has been attached with the repository_

![WeatherForecastPostman](/Screenshots/WeatherForecastPostman.png)

![WeatherForecastConsole](/Screenshots/WeatherForecastConsole.png)

![SalesForecastPostman](/Screenshots/SalesForecastPostman.png)

![SalesForecastConsole](/Screenshots/SalesForecastConsole.png)


_The flow diagram is also given below:_
![Flow Diagram](/Screenshots/FlowDiagram.png)

#UI Project#
The UI project has been built using latest react version (18.2.0). The dependencies are available in package.json folder. For simplicity I am using material-table for showing forecasted data. I am also using material and bootstrap as styling component. We are just providing text field for dates which can be modified later.

##Running Project locally##
- Install the dependencies using following command:
```
npm install
```
- After installing dependencies start the project using following command:
```
npm start
```

##UI Project Stucture##
![UI Project](/Screenshots/FrontendProjectStucture.png)

#Backend Project#
The backend project has been developed using SpringBoot 2.7.1 (Java 1.8). There are 2 main controllers and services classes. One for Weather forecast and one for connecting to AWS postgres DB and fetching sales forecasted data. For simplicity, I am using properties file for now to store variables which can be transferred to secret vaults once the application is set to be deployed in any kubernates environment.
The project can be run in either of 2 ways:
1. Setting up project in IDE and running the main class.
2. Using docker:
Build image using dockerfile of the poroject
```
docker build -t forecasting-tool-service .
```

![DockerBuild](/Screenshots/DockerBuild.png)

Docker run to start the project:
```
docker run -p 8081:80 forecasting-tool-service
```

![DockerRun](/Screenshots/DockerRun.png)

![ApplicationRunFromDockerDesktop](/Screenshots/ApplicationRunFromDockerDesktop.png)


##Backend Project Stucture##
![Backend Project](/Screenshots/BackendProjectStucture.png)


##Some important features of the project##

###UIHomepage###
![UIHomepage](/Screenshots/UIHomepage.png)

###DateInput###
![DateInput](/Screenshots/DateInput.png)

###WeatherForecastAPICall###
![WeatherForecastAPICall](/Screenshots/WeatherForecastAPICall.png)

###SalesForecastAPICall###
![SalesForecastAPICall](/Screenshots/SalesForecastAPICall.png)

###GeneratingAlertData###
![GeneratingAlertData](/Screenshots/GeneratingAlertData.png)

###TwoWeeksFunctionality###
![TwoWeeksFunctionality](/Screenshots/TwoWeeksFunctionality.png)
