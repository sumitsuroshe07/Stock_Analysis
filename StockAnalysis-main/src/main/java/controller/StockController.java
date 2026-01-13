package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.StockQuote;
import service.StockService;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<StockQuote> getQuote(@PathVariable String symbol) {
        StockQuote quote = stockService.getQuote(symbol);
        return quote != null ? ResponseEntity.ok(quote) : ResponseEntity.notFound().build();
    }

    @GetMapping("/batch")
    public ResponseEntity<List<StockQuote>> getBatchQuotes(@RequestParam List<String> symbols) {
        List<StockQuote> quotes = stockService.getBatchQuotes(symbols);
        return ResponseEntity.ok(quotes);
    }
    
    @PostMapping("/addToPortfolio")
    public ResponseEntity<Void> addStock(@RequestParam String symbol) {
        stockService.addStock(symbol);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("removeFromPortfolio/{symbol}")
    public ResponseEntity<Void> removeStock(@PathVariable String symbol) {
        stockService.removeStock(symbol);
        return ResponseEntity.ok().build();
   }

   @GetMapping("/getPortfolio")
    public List<String> getAllTrackedStocks() {
        return stockService.getTrackedSymbols();
    }
}
