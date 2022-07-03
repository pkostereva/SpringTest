package com.example.SpringTest.utils;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Class TemperatureParser:")
public class TemperatureParserTests {
    @Mock
    private UrlGenerator urlGenerator;

    private TemperatureParser parser;

    @BeforeEach
    void SetUp() {
        parser = new TemperatureParser();
    }

    @Test
    @DisplayName("get temperature by user")
    void shouldGetTemperature() throws IOException {
        File file = ResourceUtils.getFile("classpath:realmeteo.txt");
        String content = new String(Files.readAllBytes(file.toPath()));
        var doc = Jsoup.parse(content);

        given(urlGenerator.getHtmlDocument(any())).willReturn(doc);

        var document = urlGenerator.getHtmlDocument("sochi");
        var temperature = parser.getTemperature(document);

        assertThat(temperature).isEqualTo("19");
    }
}
