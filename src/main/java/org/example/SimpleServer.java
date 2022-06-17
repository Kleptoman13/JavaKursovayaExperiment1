package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SimpleServer implements HttpHandler {

    static int requestCounter = 0;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST");
        String[] requestParams = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParams = getRequestParams(httpExchange);
        }
        if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParams = getRequestParams(httpExchange);
        }
        returnResponse(httpExchange, requestParams);
    }

    private String[] getRequestParams(HttpExchange httpExchange) {
        String parameters = httpExchange.getRequestURI().toString().split("\\?")[1];
        return parameters.split("&");
    }

    private void returnResponse(HttpExchange httpExchange, String[] requestParamValues) throws IOException {
        requestCounter++;
        System.out.println("Request received: " + requestCounter);
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder response = new StringBuilder();


        /*
        * Функция обращения до сервера для присвоения свободного номера другому владельцу
        * */
        if(requestParamValues[0].equals("addNumber"))
        {
            ObjectMapper objectMapper = new ObjectMapper();
            NumberOfSom numbers = objectMapper.readValue(new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).lines().collect(Collectors.joining("\n")), NumberOfSom.class);
            AtsList tempAtsList = Database.loadJSON("./src/main/Database/AtsDataBase.json");

//            tempAtsList.AssignNumber(numbers.getSelected_number(), numbers.getSelected_banner(), numbers.getSelected_numberI());

            System.out.println(tempAtsList);
            response.append(numbers);
        }

        if (requestParamValues[0].equals("info")) {
            response.append(new BufferedReader(new FileReader("./src/main/Database/AtsDataBase.json")).readLine());
        }

        httpExchange.sendResponseHeaders(0, response.length());
        outputStream.write(response.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }

}
