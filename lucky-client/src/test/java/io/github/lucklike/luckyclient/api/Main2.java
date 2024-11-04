package io.github.lucklike.luckyclient.api;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Main2 {

    public static void main(String[] args) throws URISyntaxException {
        URI u1 = new URI("http://localhost:8080/book/get?id=13");
        URI u2 = new URI("http://127.0.0.1:8080/book/get?id=13");

        System.out.println(u1.equals(u2));
    }

}
