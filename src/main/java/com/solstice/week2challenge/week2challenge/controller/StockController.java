package com.solstice.week2challenge.week2challenge.controller;

import com.solstice.week2challenge.week2challenge.model.Stock;
import com.solstice.week2challenge.week2challenge.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class StockController
{
    @Autowired
    private StockService stockService;


//    @PostMapping("/stocks")
//    public List<Stock> addStockData()
//    {
//        try
//        {
//            return stockService.addStocksToDatabase();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @PostMapping("/stocks")
//    public void addAllStocks(@RequestBody Stock stock)
//    {
//        try
//        {
//            stockService.addStocksToDatabase();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//            e.getMessage();
//        }
//    }
}
