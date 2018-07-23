package com.solstice.week2challenge.week2challenge;

import com.solstice.week2challenge.week2challenge.model.Quote;
import com.solstice.week2challenge.week2challenge.service.QuoteService;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class QuoteServiceTest
{
    @Mock
    private QuoteService quoteService;

    @Test
    public void testAddStock() throws ParseException
    {
        // create a quote

        Quote quote = new Quote();

        Date date = new SimpleDateFormat("dd-MM-yyy HH:mm:ss").parse("2018-06-22 04:30:00");

        quote.setSymbol("MSFT");
        quote.setPrice(108.00);
        quote.setDate(date);
        quote.setVolume(200);

        // testing add the contact

        quoteService.addStock(quote);

        assertEquals("MSFT", quote.getSymbol());

    }
}
