package com.solstice.week2challenge.week2challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "week2quotedb")
public class Quote
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stockId")
    private Integer stockId;

    private String symbol;
    private Double price;
    private Integer volume;

    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
    private Date date;

    public Quote()
    {

    }

    public Quote(Integer stockId, String symbol, Double price, Integer volume, Date date)
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
