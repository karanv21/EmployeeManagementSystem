package network;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DataJsoup {
    //Effects: prints out a list of UBC residence
    public static void main(String[] args) throws IOException {
        String html = "https://vancouver.housing.ubc.ca/other-housing/more-campus-housing/";
        try {
            Document dc = Jsoup.connect(html).get();
            Elements tableElements = dc.select("table");
            System.out.println();

            Elements namesinRow = tableElements.select(":not(thead) tr");
            System.out.println();
            for (int i = 0; i < namesinRow.size(); i++) {
                Element row = namesinRow.get(i);
                System.out.println("---------------------------------");
                Elements rowItems = row.select("td");
                System.out.println(rowItems.get(0).text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}