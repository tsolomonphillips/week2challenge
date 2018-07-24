package com.solstice.week2challenge.week2challenge.controller;

import com.solstice.week2challenge.week2challenge.model.Quote;
import com.solstice.week2challenge.week2challenge.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequestMapping("/stocks")
public class QuoteController
{
    private QuoteService quoteService;

    public QuoteController(@Autowired QuoteService quoteService)
    {
        this.quoteService = quoteService;
    }

    @PostMapping("/load")
    public void addAllStocks() throws IOException, ParseException
    {
        this.quoteService.createStockList();
    }

    @GetMapping("")
    public Iterable<Quote> getAllStocks()
    {
        return quoteService.getAllStocks();
    }

    @GetMapping("/{symbol}")
    public ArrayList<Quote> searchBySymbol(@PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return quoteService.searchBySymbol(upperCaseSymbol);
    }

    @GetMapping("/maxprice/{date}/{symbol}") ///maxprice/{date}/AAPL
    public Double searchForMaxPrice(@PathVariable String date, @PathVariable String symbol)
    {
        //make call to symbol to get id = 1
        //look up in db to get max price for 1
        String upperCaseSymbol = symbol.toUpperCase();
        return quoteService.getMaxPrice(date, upperCaseSymbol);
    }

    @GetMapping("/minprice/{date}/{symbol}")
    public Double searchforLowestPrice(@PathVariable String date, @PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return quoteService.getLowestPrice(date, upperCaseSymbol);
    }

    @GetMapping("/volume/{date}/{symbol}")
    public Integer searchForTotalVolume(@PathVariable String date, @PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return quoteService.getTotalVolume(date, upperCaseSymbol);
    }

    @GetMapping("/closingprice/{date}/{symbol}")
    public Double searchForClosingPrice(@PathVariable String date, @PathVariable String symbol)
    {
        String upperCaseSymbol = symbol.toUpperCase();
        return quoteService.getClosing(date, upperCaseSymbol);
    }

    @GetMapping("/symbol/{symbol}")
    public Integer getIdFromSymbol(@PathVariable String symbol) throws IOException
    {
        return quoteService.getStockSymbolId(symbol);
    }


    @PostMapping("")
    public Quote addStock(@RequestBody Quote quote)
    {
        return quoteService.addStock(quote);
    }


}
