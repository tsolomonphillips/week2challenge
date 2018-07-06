package com.solstice.week2challenge.week2challenge.controller;

import com.solstice.week2challenge.week2challenge.model.Stock;
import com.solstice.week2challenge.week2challenge.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@RestController
@RequestMapping("/stocks")
public class StockController
{
    @Autowired
    public StockService stockService;


    @PostMapping("/load")
    public void addAllStocks() throws IOException
    {
        this.stockService.createStockList();
    }

    @GetMapping("/{symbol}")
    public ArrayList<Stock> searchBySymbol(@PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return stockService.searchBySymbol(upperCaseSymbol);
    }

    @GetMapping("/maxprice/{date}/{symbol}")
    public Double searchForMaxPrice(@PathVariable Date date, @PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return stockService.getMaxPrice(date, upperCaseSymbol);
    }

    @GetMapping("/minprice/{date}/{symbol}")
    public Double searchforLowestPrice(@PathVariable Date date, @PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return stockService.getLowestPrice(date, upperCaseSymbol);
    }

    @GetMapping("/volume/{date}/{symbol}")
    public Integer searchForTotalVolume(@PathVariable Date date, @PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return stockService.getTotalVolume(date, upperCaseSymbol);
    }

    @GetMapping("/closingprice/{date}/{symbol}")
    public Double searchForClosingPrice(@PathVariable Date date, @PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return stockService.getClosing(date, upperCaseSymbol);
    }

}
