package com.solstice.week2challenge.week2challenge.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "week2stockdb")
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stockId")
    private Integer stockId;

    private String symbol;
    private Double price;
    private Integer volume;
    private Date date;

    public Stock()
    {

    }

    public Stock(Integer stockId, String symbol, Double price, Integer volume, Date date)
    {
        this.stockId = stockId;
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    public Integer getStockId()
    {
        return stockId;
    }

    public void setStockId(Integer stockId)
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

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getVolume()
    {
        return volume;
    }

    public void setVolume(Integer volume)
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
