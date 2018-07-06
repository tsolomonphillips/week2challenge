package com.solstice.week2challenge.week2challenge.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.week2challenge.week2challenge.model.Stock;
import com.solstice.week2challenge.week2challenge.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

@Service
public class StockService
{
    public ArrayList<Stock> stockList = new ArrayList<>();

    @Autowired
    public StockRepository stockRepository;


    public void createStockList() throws IOException
    {
        File jsonFile = new File("week1-stocks.json").getAbsoluteFile();
        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            this.stockList = objectMapper.readValue(jsonFile, new TypeReference<List<Stock>>() {});

            for (int i = 0; i < stockList.size(); i++)
            {
                Stock stock = new Stock();

                stock.setSymbol(stockList.get(i).getSymbol());
                stock.setPrice(stockList.get(i).getPrice());
                stock.setVolume(stockList.get(i).getVolume());
                stock.setDate(stockList.get(i).getDate());

                stockRepository.save(stock);
                System.out.println("Stocks saved to database");
            }

        }
        catch (IOException e)
        {
            e.getMessage();
        }

    }

    public ArrayList<Stock> searchBySymbol(String symbol)
    {
        return stockRepository.listStockBySymbol(symbol);
    }

    public Double getMaxPrice(Date date, String symbol)
    {
        return stockRepository.getMaxPrice(date, symbol);
    }

    public Double getLowestPrice(Date date, String symbol)
    {
        return stockRepository.getLowestPrice(date, symbol);
    }

    public Integer getTotalVolume(Date date, String symbol)
    {
        return stockRepository.getTotalVolume(date, symbol);
    }

}
