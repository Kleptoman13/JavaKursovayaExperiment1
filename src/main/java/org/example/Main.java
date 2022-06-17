package org.example;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void atsServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/back", new SimpleServer());
        server.start();
        System.out.println(System.lineSeparator() + "\033[1;32mServer started at:\tlocalhost:8080\u001B[0m");
    }

    public static void main(String[] args) throws IOException {
        AtsList atsList = Database.loadJSON("./src/main/Database/AtsDataBase.json");
        Database.saveJSON(atsList, "AtsDataBase");
        atsServer();
    }
}