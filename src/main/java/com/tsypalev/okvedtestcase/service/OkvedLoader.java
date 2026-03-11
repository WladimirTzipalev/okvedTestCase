package com.tsypalev.okvedtestcase.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsypalev.okvedtestcase.model.Okved;
import com.tsypalev.okvedtestcase.util.HttpClientUtil;

import java.util.List;

/**
 * Loads OKVED list from remote JSON
 */
public class OkvedLoader {

  private static final String OKVED_URL = "https://raw.githubusercontent.com/bergstar/testcase/master/okved.json";

  public static List<Okved> load() throws Exception {

    ObjectMapper mapper = new ObjectMapper();

    String json = HttpClientUtil.get(OKVED_URL);

    return mapper.readValue(json, new TypeReference<List<Okved>>() {
    });
  }
}
