package com.example.SpringTest.utils;

import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
@NoArgsConstructor
public class UrlGenerator {
    public Document getHtmlDocument(String cityName) throws IOException {
        String url = String.format("https://realmeteo.ru/%s/1/current", cityName);
        return Jsoup.parse(new URL(url), 3000);
    }
}
