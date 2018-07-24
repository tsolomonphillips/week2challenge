package com.solstice.week2challenge.week2challenge.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.week2challenge.week2challenge.model.Quote;
import com.solstice.week2challenge.week2challenge.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
public class QuoteService
{
    //private ArrayList<Quote> quoteList = new ArrayList<>();

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${stocks.remote.url}")
    private String remoteDataSource;

    public QuoteService()
    {

    }

    public QuoteService(QuoteRepository quoteRepository)
    {
        this.quoteRepository = quoteRepository;
    }

    public URI serviceUrl()
    {
        List<ServiceInstance> list = discoveryClient.getInstances("SYMBOL");
        if (list != null && list.size() > 0 )
        {
            return list.get(0).getUri();
        }
        return null;
    }

    public void createStockList() throws IOException, ParseException
    {
        //File jsonFile = new File("week4-stocks.json").getAbsoluteFile();
        //String stockUrl = "https://bootcamp-training-files.cfapps.io/week4/week4_stocks.json";
        URL url = new URL(remoteDataSource);
        URLConnection connection = url.openConnection();
        ObjectMapper objectMapper = new ObjectMapper();

//        try
//        {
            List<Quote> listData = objectMapper.readValue(connection.getInputStream(), new TypeReference<List<Quote>>() {});

//            for (int i = 0; i < quoteList.size(); i++)
//            {
//                Quote quote = new Quote();
//
//                quote.setSymbol(quoteList.get(i).getSymbol());
//                quote.setPrice(quoteList.get(i).getPrice());
//                quote.setVolume(quoteList.get(i).getVolume());
//                quote.setDate(quoteList.get(i).getDate());

                quoteRepository.save(listData);
                System.out.println("Stocks saved to database");
            }

        //}
//        catch (IOException e)
//        {
//            e.getMessage();
//        }

    //}

    public Iterable<Quote> getAllStocks()
    {
        return quoteRepository.findAll();
    }

    public ArrayList<Quote> searchBySymbol(String symbol)
    {
        return quoteRepository.listStockBySymbol(symbol);
    }

    public Double getMaxPrice(String date, String symbol)
    {
        return quoteRepository.getMaxPrice(date, symbol);
    }

    public Double getLowestPrice(String date, String symbol)
    {
        return quoteRepository.getLowestPrice(date, symbol);
    }

    public Integer getTotalVolume(String date, String symbol)
    {
        return quoteRepository.getTotalVolume(date, symbol);
    }

    public Double getClosing(String date, String symbol)
    {
        return quoteRepository.getClosingPrice(date, symbol);
    }

    public Quote addStock(Quote quote)
    {
        return quoteRepository.save(quote);
    }

    public void deleteStock(Quote quote)
    {
        quoteRepository.delete(quote);
    }

    public Integer getStockSymbolId(String stockSymbol) throws IOException
    {

        String url =  serviceUrl() + "/symbols/" + stockSymbol;
        return restTemplate.getForObject(url, Integer.class);
    }

}
