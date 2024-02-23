package com.ukukhula.bursaryapi.entities;

import lombok.Data;

@Data
public class University {
  private int id;
  private String name;

  public University(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
