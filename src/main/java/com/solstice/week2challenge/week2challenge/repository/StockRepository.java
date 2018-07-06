package com.solstice.week2challenge.week2challenge.repository;

import com.solstice.week2challenge.week2challenge.model.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer>
{
    @Query(value = "SELECT * FROM week2stockdb WHERE symbol = ?", nativeQuery = true)
    ArrayList<Stock> listStockBySymbol(@Param("symbol") String symbol);

    @Query(value = "SELECT max(price) FROM week2stockdb WHERE date = ?1 AND symbol = ?2", nativeQuery = true)
    Double getMaxPrice(@Param("date")Date date, @Param("symbol") String symbol);

    @Query(value = "SELECT min(price) FROM week2stockdb WHERE date = ?1 AND symbol = ?2", nativeQuery = true)
    Double getLowestPrice(@Param("date") Date date, @Param("symbol") String symbol);

    @Query(value = "SELECT sum(volume) FROM week2stockdb WHERE date = ?1 AND symbol = ?2", nativeQuery = true)
    Integer getTotalVolume(@Param("date") Date date, @Param("symbol") String symbol);

    @Query(value = "SELECT price AS closingPrice FROM week2stockdb WHERE date = ? and symbol = ? " +
            "ORDER BY price DESC LIMIT 1", nativeQuery = true)
    Double getClosingPrice(@Param("date") Date date, @Param("symbol") String symbol);

}
