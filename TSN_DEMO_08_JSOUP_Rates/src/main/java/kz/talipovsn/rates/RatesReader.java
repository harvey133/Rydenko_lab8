package kz.talipovsn.rates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// СОЗДАТЕЛЬ КОТИРОВОК ВАЛЮТ
public class RatesReader {

    private static final String BASE_URL = ""; // Адрес с котировками

    // Парсинг котировок из формата html web-страницы банка, при ошибке доступа возвращаем null
    public static String getRatesData() {
        StringBuilder data = new StringBuilder();
        try {

              Document doc = Jsoup.connect(BASE_URL).timeout(5000).get();
              data.append("Post.KZ News: \n\n");
              Elements news = doc.select("p.css-yb61mr");
              Elements date = doc.select("p.mr-4");
              Elements box = doc.select("div.row");
              for (int i = 0; i < box.size(); i++) {

                  Element d = date.get(i);
                  Element b = news.get(i);

                  data.append("Date: " + b.text() + "\n");
                  data.append("News: " + d.text() + "\n\n");
              }
        } catch (Exception ignored) {
            return null; // При ошибке доступа возвращаем null
        }
        return data.toString().trim(); // Возвращаем результат
    }

}