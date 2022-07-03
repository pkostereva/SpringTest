package com.example.SpringTest.utils;

import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class TemperatureParser {
    public String getTemperature(Document html) {
        return html
                .selectFirst("table[id=realdata]")
                .selectFirst("tr")
                .select("td[class=meteodata]")
                .first()
                .ownText();
    }
}
