package org.example.v3;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class Crawler {
    private final static String URL = "https://www.vietcombank.com.vn/KHCN/Cong-cu-tien-ich/Ty-gia";
    private static Document document;
    private static Connection connection;

    static {
        connection = Jsoup.connect(URL);
        try {
            document = connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        for(Currency c : Crawler.getListDataSource()){
            System.out.println(c);
        }
    }

    public static List<Currency> getListDataSource() {
        List<Currency> list = null;
        Elements ul = document.select("ul.dropdown-options-wrapper li");
        list = ul.stream().map(CurrencyMapper::apply).collect(Collectors.toList());
        return list;
    }

    record Currency(
            String code,
            String cashBuying,
            String telegraphicBuying,
            String selling
    ) {
        private static final String DELIMITER = ",";

        @Override
        public String toString() {
            return code + DELIMITER + cashBuying + DELIMITER + telegraphicBuying + DELIMITER + selling;
        }
    }

    static class CurrencyMapper {
        public static Currency apply(Element e) {
            return new Currency(
                    e.attr("data-code"),
                    e.attr("data-cash-rate"),
                    e.attr("data-transfer-rate"),
                    e.attr("data-sell-rate")
            );
        }
    }

}
