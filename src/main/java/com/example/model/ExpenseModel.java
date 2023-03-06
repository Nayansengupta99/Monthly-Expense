package com.example.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "EXPENSE")

public class ExpenseModel {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private Long id;
	
	private Long orderId;
	private String orderName;
	private Instant purchaseDate;
	private boolean toBePaid;

}
