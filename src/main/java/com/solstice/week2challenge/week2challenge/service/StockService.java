package com.solstice.week2challenge.week2challenge.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.week2challenge.week2challenge.model.Stock;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService
{
    private List<Stock> stockList = new ArrayList<>();

    public List<Stock> addStocksToDatabase() throws IOException
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
            }

            if (stockList.size() > 1)
            {
                System.out.println("Data inserted successfully");
            }
            else
            {
                System.out.println("Error inserting data into database");
            }

        }
        catch (IOException e)
        {
            e.getMessage();
        }

        return stockList;
    }

}
