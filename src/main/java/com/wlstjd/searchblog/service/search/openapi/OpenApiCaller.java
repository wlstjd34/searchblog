package com.wlstjd.searchblog.service.search.openapi;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OpenApiCaller {
    public String get(Map<String, String> headers, String method, String requestContent) throws SocketTimeoutException {
        try {
            URL url = new URL(requestContent);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            insertHeader(con, headers);
            con.setConnectTimeout(5000);
            con.setRequestMethod(method);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
                return readBody(con.getErrorStream());
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Wrong Protocol. Expected : http");
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException("IOException");
        }
    }

    private void insertHeader(HttpURLConnection con, Map<String, String> headers) {
        for(Map.Entry<String, String> header : headers.entrySet()) {
            con.setRequestProperty(header.getKey(), header.getValue());
        }
    }

    private String readBody(InputStream body){
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(body, StandardCharsets.UTF_8))) {
            return lineReader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("API 응답 실패", e);
        }
    }
}
