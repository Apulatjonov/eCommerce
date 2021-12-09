package com.textmagic.sdk;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class HttpApiTester {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.print("Insert Phone number: ");
        String number = new Scanner(System.in).nextLine();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://phonenumbervalidatefree.p.rapidapi.com/ts_PhoneNumberValidateTest.jsp?number=%2B" + number + "&country=UY"))
                .header("x-rapidapi-host", "phonenumbervalidatefree.p.rapidapi.com")
                .header("x-rapidapi-key", "b97e98c900msh6dee4b057f6dd19p17178fjsnd25aca669516")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}