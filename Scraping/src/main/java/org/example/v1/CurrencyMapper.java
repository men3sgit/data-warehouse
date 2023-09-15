package org.example.v1;

import org.example.v1.Currency;
import org.jsoup.select.Elements;

import java.util.function.Function;

public class CurrencyMapper implements Function<Elements, Currency> {

    @Override
    public Currency apply(Elements td) {
        String value = td.get(4).text();
        return new Currency(
                td.get(0).text(),
                td.get(1).text(),
                parse(value));
    }
    public double parse(String text){
        try {
            if (text.contains(".")) {
                text = text.substring(0, text.indexOf(".")) + text.substring(text.indexOf(".") + 1);
                return Double.parseDouble(text.replace(",", "."));
            } else {
                return Double.parseDouble(text.replace(",", "."));
            }
        }catch (NumberFormatException e){
            return 0;
        }
    }
}
