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

import com.example.exception.ExpenseNotFoundException;
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
		Instant now = Instant.now();
		model.setPurchaseDate(now);
		model.setToBePaid(false);
		expRepo.save(model);
		return "Expense Model Saved Successfully";
	}

	public ExpenseModel updateExpenseById(Long id, ExpenseModel model) {

		if (expRepo.findById(id).isPresent()) {
			if (model != null) {
				model.setOrderName(model.getOrderName());
				model.setPurchaseDate(model.getPurchaseDate());
				model.setToBePaid(model.isToBePaid());

			}
			return expRepo.save(model);

		} else {
			throw new ExpenseNotFoundException();
		}

	}

	public ExpenseModel getExpenseById(long id) {
		if (expRepo.findById(id).isPresent()) {
			return expRepo.findById(id).get();
		}

		else {
			throw new ExpenseNotFoundException();
		}
	}

	public String deleteExpenseById(long id) {

		if (expRepo.findById(id).isPresent()) {
			expRepo.deleteById(id);
			return "Deletion successfull for orderId " + id;
		} else
			throw new ExpenseNotFoundException();
	}

}
