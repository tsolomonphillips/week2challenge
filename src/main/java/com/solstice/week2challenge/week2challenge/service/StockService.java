package com.solstice.week2challenge.week2challenge.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.week2challenge.week2challenge.model.Stock;
import com.solstice.week2challenge.week2challenge.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService
{
    private List<Stock> stockList = new ArrayList<>();

    @Autowired
    private StockRepository stockRepository;

    public void addAllStocksToDatabase() throws IOException
    {
        try
        {
            stockRepository.save(createStockList());
        }
        catch (IOException e)
        {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public List<Stock> createStockList() throws IOException
    {
        File jsonFile = new File("week1-stocks.json").getAbsoluteFile();
        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            this.stockList = objectMapper.readValue(jsonFile, new TypeReference<List<Stock>>() {});

            Stock stock = new Stock();

            for (int i = 0; i < stockList.size(); i++)
            {
                stock.setSymbol(stockList.get(i).getSymbol());
                stock.setPrice(stockList.get(i).getPrice());
                stock.setVolume(stockList.get(i).getVolume());
                stock.setDate(stockList.get(i).getDate());

                stockList.add(i, stock);
            }

            return stockList;

        }
        catch (IOException e)
        {
            e.getMessage();
        }

        return null;
    }

}
