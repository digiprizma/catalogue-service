package com.digiprizma.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"category"})
public class Product {

	private String id;
	private String designation;
	private double price;
	@DBRef
	private Category category;
	
}
