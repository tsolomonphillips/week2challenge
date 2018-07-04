package com.solstice.week2challenge.week2challenge.repository;

import com.solstice.week2challenge.week2challenge.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer>
{

}
