package com.solstice.week2challenge.week2challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Stock
{
    @Id
    @GeneratedValue
    private Integer stockId;

    private String symbol;
    private Double price;
    private Integer volume;
    private Date date;

    public Stock()
    {

    }

    public Stock(int stockId, String symbol, double price, int volume, Date date)
    {
        this.stockId = stockId;
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }


    public int getStockId()
    {
        return stockId;
    }

    public void setStockId(int stockId)
    {
        this.stockId = stockId;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getVolume()
    {
        return volume;
    }

    public void setVolume(int volume)
    {
        this.volume = volume;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
