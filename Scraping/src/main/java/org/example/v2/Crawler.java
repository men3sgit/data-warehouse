package org.example.v2;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class Crawler implements Runnable, DataSource<Currency> {
    private String url;
    private Connection conn;
    private Document document;

    public Crawler(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        conn = Jsoup.connect(url);
        try {
            document = conn.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Currency> getListDataSource() {
        List<Currency> list = null;
        Elements ul = document.select("ul.dropdown-options-wrapper li");

        list = ul.stream().map(CurrencyMapper::apply).collect(Collectors.toList());
        return list;
    }

    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("https://www.vietcombank.com.vn/KHCN/Cong-cu-tien-ich/Ty-gia");
        crawler.run();
        File file = new File("D:\\Exercise Java\\springs\\DataWarehouse\\data\\data.csv");
        PrintWriter pw = new PrintWriter(file);
        crawler.getListDataSource().forEach(pw::println);
        pw.close();
    }
}
