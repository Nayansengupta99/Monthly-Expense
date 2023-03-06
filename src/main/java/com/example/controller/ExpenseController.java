package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ExpenseModel;
import com.example.service.ExpenseService;

@RestController
@RequestMapping("expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expService;

	@GetMapping("/all")
	public ResponseEntity<List<ExpenseModel>> getExpenses() {
		return new ResponseEntity<List<ExpenseModel>>(expService.getExpenses(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveExpense(@RequestBody ExpenseModel model) {
		return new ResponseEntity<String>(expService.saveExpense(model), HttpStatus.OK);
	}
	
	

}
