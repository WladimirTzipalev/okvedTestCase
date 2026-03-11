package com.tsypalev.okvedtestcase.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Utility class for HTTP requests.
 * <p>
 * Uses Java 11 HttpClient.
 */
public class HttpClientUtil {

  private static final HttpClient CLIENT = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(10))
      .build();

  /**
   * Executes GET request and returns response body.
   *
   * @param url target url
   * @return response body
   */
  public static String get(String url) throws IOException, InterruptedException {

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofSeconds(10))
        .GET()
        .build();

    HttpResponse<String> response = CLIENT.send(
        request,
        HttpResponse.BodyHandlers.ofString()
    );

    if (response.statusCode() != 200) {
      throw new IOException("HTTP error: " + response.statusCode());
    }

    return response.body();
  }
}
