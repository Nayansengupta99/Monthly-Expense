package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.ExpenseModel;

public interface ExpenseRepository extends MongoRepository<ExpenseModel, Long>{

}
