package com.tsypalev.okvedtestcase;

import com.tsypalev.okvedtestcase.model.Okved;
import com.tsypalev.okvedtestcase.service.OkvedLoader;
import com.tsypalev.okvedtestcase.service.OkvedMatcher;
import com.tsypalev.okvedtestcase.service.PhoneNormalizer;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Entry point of the application
 */
public class OkvedTestCaseApplication {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java Main <phone>");
      return;
    }
    System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    String rawPhone = args[0];

    try {

      String normalized = PhoneNormalizer.normalize(rawPhone);

      List<Okved> okveds = OkvedLoader.load();

      OkvedMatcher.MatchResult result =
          OkvedMatcher.findBestMatch(normalized, okveds);

      Okved okved = result.okved();

      if (okved == null) {
        System.out.println("No match found");
        return;
      }

      System.out.println("Phone: " + normalized);
      System.out.println("OKVED: " + okved.getCode());
      System.out.println("Name: " + okved.getName());
      System.out.println("Match length: " + result.matchLength());

    } catch (Exception e) {

      System.out.println("Error: " + e.getMessage());
    }
  }
}
