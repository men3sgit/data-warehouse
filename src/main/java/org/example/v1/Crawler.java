package org.example.v1;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler {
    //    private static final String DOMAIN = "https://www.vietcombank.com.vn/KHCN/Cong-cu-tien-ich/Ty-gia?devicechannel=default";
    private static final String DOMAIN = "https://chogia.vn/ty-gia/vietcombank/";
    private static Connection connection;
    private static Document document;
    private static List<Currency> list;
    private static CurrencyMapper currencyMapper = new CurrencyMapper();

    static {
        try {
            connection = Jsoup.connect(DOMAIN);
            document = connection.get();
            if (connection.response().statusCode() == HttpStatusCodeConstant.OK) {
//                System.out.println(document);
//                extract();
//                System.out.println(document);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Currency> extract() {
        if (list != null) return list;

        list = new ArrayList<Currency>();
        Elements tbody = document.getElementById("tbl_ty_gia").select("tbody tr");
        list = tbody.stream().map(td -> currencyMapper.apply(td.select("td"))
        ).collect(Collectors.toList());

//          }
//            list.add(currencyMapper.apply(td));
//          list = td.stream().map(currencyMapper).collect(Collectors.toList());



//        list = ul.stream()
//                .map(currencyMapper)
//                .collect(Collectors.toList());
//        list = ul.forEach();

        return list;

    }

    public static void main(String[] args) {
        extract().forEach(System.out::println);
    }
}
