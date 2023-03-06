package com.example.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.model.DatabaseSequence;
import com.example.model.ExpenseModel;
import com.example.repository.ExpenseRepository;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expRepo;

	@Autowired
	private MongoOperations mongoOperations;

	public long generateSequence(String seqName) {

		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;

	}

	public List<ExpenseModel> getExpenses() {
		return expRepo.findAll();
	}

	public String saveExpense(ExpenseModel model) {
		model.setId(generateSequence(ExpenseModel.SEQUENCE_NAME));
//		Instant now=Instant.now();
//		model.setPurchaseDate(now);
//		model.setId(model.getId());
//		model.setOrderId(model.getOrderId());
//		model.setOrderName(model.getOrderName());
		System.out.println(model);
		expRepo.save(model);
		return "Expense Model Saved Successfully";
	}

}
