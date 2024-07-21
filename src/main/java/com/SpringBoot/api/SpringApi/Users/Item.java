package com.SpringBoot.api.SpringApi.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Item {

  public Item(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
  public Item() {}
  
@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  public Item(String name) {
    this.name = name;
  }
}