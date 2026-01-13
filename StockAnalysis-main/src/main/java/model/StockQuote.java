package model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockQuote {
    private String symbol;
    private BigDecimal price;
    private BigDecimal change;
    private BigDecimal changesPercentage;
    private Long timestamp;
}
