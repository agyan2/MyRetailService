package com.myretail.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.myretail.model.Price;

//@Repository
public interface PriceRepo extends MongoRepository<Price, String>
{
	 @Query("{'id' : ?0}")
	 public Price searchById(long id);


}
