package com.tsypalev.okvedtestcase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Model representing OKVED entry
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Okved {

  private String code;
  private String name;
  private List<Okved> items;

  public Okved() {
  }

  public Okved(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Okved> getItems() {
    return items;
  }

  public void setItems(List<Okved> items) {
    this.items = items;
  }
}
