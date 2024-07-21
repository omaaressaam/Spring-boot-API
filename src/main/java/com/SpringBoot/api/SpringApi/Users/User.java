package com.SpringBoot.api.SpringApi.Users;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private LocalDate birth_data;
	
	public User() {}
	
	public User(String name, LocalDate birth_data) {
		super();
		this.name = name;
		this.birth_data = birth_data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth_data() {
		return birth_data;
	}

	public void setBirth_data(LocalDate birth_data) {
		this.birth_data = birth_data;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birth_data=" + birth_data + "]";
	}
	
}
