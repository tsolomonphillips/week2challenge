package com.solstice.week2challenge.week2challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solstice.week2challenge.week2challenge.controller.QuoteController;
import com.solstice.week2challenge.week2challenge.model.Quote;
import com.solstice.week2challenge.week2challenge.service.QuoteService;
import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteController.class)
@ContextConfiguration(classes = {SecurityConfiguration.class})
public class QuoteControllerTest
{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuoteService quoteService;

	@MockBean
	private QuoteController quoteController;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddStockQuote() throws ParseException
	{

		Quote testQuote1 = new Quote();
		testQuote1.setSymbol("AAPL");


		Date date = new SimpleDateFormat("dd-MM-yyy HH:mm:ss").parse("2018-06-22 04:30:00");

		testQuote1.setDate(date);
		testQuote1.setSymbol("MSFT");
		testQuote1.setVolume(1000);
		testQuote1.setPrice(215.00);


        when(quoteService.addStock(any(Quote.class))).thenReturn(testQuote1);

        Quote testQuote2 = new Quote();
        testQuote2.setSymbol("AAPL");
        testQuote2.setPrice(387.00);
        testQuote2.setVolume(1000);
        testQuote2.setDate(date);

		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

			ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
			String requestJson = objectWriter.writeValueAsString(testQuote2);

			mockMvc.perform(post("/stocks")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJson))
                    .andExpect(status().isOk())
                    .andReturn();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e.getMessage();
		}

	}

//	@Test
//	public void testAddStockQuoteFails() throws ParseException
//	{
//
//		Quote testQuote1 = new Quote();
//		testQuote1.setSymbol("AAPL");
//
//
//		Date date = new SimpleDateFormat("dd-MM-yyy HH:mm:ss").parse("2018-06-22 04:30:00");
//
//		testQuote1.setDate(date);
//		testQuote1.setSymbol("MSFT");
//		testQuote1.setVolume(1000);
//		testQuote1.setPrice(215.00);
//
//		when(quoteService.addStock(any(Quote.class))).thenReturn(null);
//
//		Quote testQuote2 = new Quote();
//		testQuote2.setSymbol("AAPL");
//		testQuote2.setPrice(387.00);
//		testQuote2.setVolume(1000);
//		testQuote2.setDate(date);
//
//		try
//		{
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//
//			ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
//			String requestJson = objectWriter.writeValueAsString(testQuote2);
//
//			mockMvc.perform(post("/stocks")
//					.contentType(MediaType.APPLICATION_JSON)
//					.content(requestJson))     //, testQuote2))
//					.andExpect(status().is(null))
//					.andReturn();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			e.getMessage();
//		}
//
//	}

}
