package org.example.v2;

import org.jsoup.nodes.Element;

public class CurrencyMapper {
    public static Currency apply(Element e) {
        return new Currency(
                e.attr("data-code"),
                e.attr("data-cash-rate"),
                e.attr("data-transfer-rate"),
                e.attr("data-sell-rate")
        );
    }

}
