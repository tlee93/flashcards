import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.*;

public class parser{
    public static void main(String[] argv){
        Document doc;
        try {
            doc = Jsoup.parse(new File("src/ch.html"), "GBK");
            Elements table = doc.select("table[border]").select("tbody").select("tr").next();
            //start parsing into json
            JSONObject jso = new JSONObject();
            JSONArray jsa = new JSONArray();

            int index = 1;
            for(Element e : table){
                Elements td = e.select("td").next(); //skip straight to the character
                JSONObject currentjso = new JSONObject();
                String character = td.select("font").text();
                if(character.length() > 1)
                    character = character.charAt(0) + "(" + character.charAt(2) + ")";
                String definitions = td.next().text();
                JSONArray definitionlist = new JSONArray();
                for(String s : definitions.split("\\["))
                    if (s.isEmpty()) continue;
                    else definitionlist.put("[" + s);
                currentjso.put("number", index);
                currentjso.put("character", character);
                currentjso.put("definitions", definitionlist);
                jsa.put(currentjso);
                index++;
            }
            jso.put("list", jsa);
            PrintWriter pw = new PrintWriter(new File("ch.json"));
            pw.write(jso.toString());
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}