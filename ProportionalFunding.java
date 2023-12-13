import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

class Stock{
    private String symbol;
    private BigDecimal price;
    private int quantity;
    public BigDecimal getValue(){
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return  "symbol : " + symbol  +
                "\nprice : " + price +
                "\nquantity : " + quantity;
    }

    public Stock(String symbol, BigDecimal price, int quantity) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }
}
public class ProportionalFunding {
    public static void main(String[] args) {
        Map<String,Stock> stockMap = new HashMap<>();
        Random random = new Random();
        for (int i=0;i<3;i++)
            stockMap.put("ABC"+(i+1),new Stock("ABC"+(i+1),new BigDecimal(random.nextInt(150)+100),random.nextInt(150)+50));
        stockMap.values().forEach(System.out::println);
        BigDecimal totalValue = stockMap.values().stream().map(Stock::getValue).reduce(BigDecimal.ZERO,BigDecimal::add);
        Map<String,BigDecimal> proportionalFunding = stockMap.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                stringStockEntry -> stringStockEntry.getValue().getValue().divide(totalValue,4,BigDecimal.ROUND_HALF_UP)
                        )
                );
        System.out.println("Proportional Funding : ");
        proportionalFunding.forEach((symbol,funding)->
                System.out.println(symbol+":"+funding.multiply(BigDecimal.valueOf(100))+"%"));
    }
}
