package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import exception.ExternalApiException;
import exception.InvalidSymbolException;
import model.StockQuote;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class StockService {

    @Value("${fmp.api.base-url}")
    private String baseUrl;

    @Value("${fmp.api.token}")
    private String token;
    
    @Value("#{'${stock.symbols}'.split(',')}")
    private HashSet<String> list;

    private final RestTemplate restTemplate;
    
    


    public StockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StockQuote getQuote(String symbol) {
    	if(!list.contains(symbol))
    	throw new InvalidSymbolException("Invalid Symbol");
    	
    try {
        String url = String.format("%s/quote/%s?apikey=%s", baseUrl, symbol, token);
        StockQuote[] response = restTemplate.getForObject(url, StockQuote[].class);
        return (response != null && response.length > 0) ? response[0] : null;	
    }catch(Exception e) {
    	throw new ExternalApiException(e.getMessage());
    }
    }

    public List<StockQuote> getBatchQuotes(List<String> symbols) {
    	for(String s:symbols)
    	if(!list.contains(s))
    	throw new InvalidSymbolException("Invalid Symbol");
    	
    	try {
        String joinedSymbols = String.join(",", symbols);
        String url = String.format("%s/quote/%s?apikey=%s", baseUrl, joinedSymbols, token);
        StockQuote[] response = restTemplate.getForObject(url, StockQuote[].class);
        return (response != null) ? Arrays.asList(response) : List.of();
    	}
    	catch(Exception e) {
    		throw new ExternalApiException(e.getMessage());
    	}
    }
}

