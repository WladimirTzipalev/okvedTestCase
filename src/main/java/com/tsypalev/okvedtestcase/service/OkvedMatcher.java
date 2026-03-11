package com.tsypalev.okvedtestcase.service;

import com.tsypalev.okvedtestcase.model.Okved;

import java.util.List;

/**
 * Matches phone number with OKVED code
 */
public class OkvedMatcher {

  public static MatchResult findBestMatch(String phone, List<Okved> okveds) {
    String digits = phone.startsWith("+7") ? phone.substring(2) : phone;
    return findBestMatchRecursive(digits, okveds);
  }

  private static MatchResult findBestMatchRecursive(String digits, List<Okved> okveds) {
    Okved bestOkved = null;
    int bestLength = 0;

    for (Okved okved : okveds) {
      String codeDigits = okved.getCode().replaceAll("\\D", "");
      int matchLen = getMatchLength(digits, codeDigits);

      if (matchLen > bestLength) {
        bestLength = matchLen;
        bestOkved = okved;
      }


      if (okved.getItems() != null && !okved.getItems().isEmpty()) {
        MatchResult childResult = findBestMatchRecursive(digits, okved.getItems());
        if (childResult.matchLength() > bestLength) {
          bestLength = childResult.matchLength();
          bestOkved = childResult.okved();
        }
      }
    }

    return new MatchResult(bestOkved, bestLength);
  }


  private static int getMatchLength(String phoneDigits, String codeDigits) {
    int len = Math.min(phoneDigits.length(), codeDigits.length());
    int match = 0;
    for (int i = 1; i <= len; i++) {
      if (phoneDigits.charAt(phoneDigits.length() - i) == codeDigits.charAt(codeDigits.length() - i)) {
        match++;
      } else {
        break;
      }
    }
    return match;
  }

  public record MatchResult(Okved okved, int matchLength) {
  }
}
