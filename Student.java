package com.shivila.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data

@Entity
public class Student {
@Id
@GeneratedValue
	private Integer id;
	private String name;
	private String email;
	private Double fees;
	private String course;
	private String address;
}
