package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Component
public class PicCrawler implements Crawler {
    @Override
    public Document getConnection(String url) throws Exception {
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            throw new Exception("connect exception");
        }
        return document;
    }

    @Override
    public List analysisDoc(Object o) {
        Document d = (Document) o;
        List list = new LinkedList();
        Elements jpgs = d.select("img");
        for(Element e : jpgs){
            //String jpgName = e.attr("title");
            String jpgUrl = e.attr("src");
            list.add(jpgUrl);
        }
        return list;
    }

    public void writeIn(Object input,String filePath) {
        URL url;
        BufferedInputStream in;
        FileOutputStream file;
        try {
            String httpUrl = (String)input;
            //System.out.println("爬取网络图片");
            // 初始化url对象
            url = new URL(httpUrl);
            // 初始化in对象，也就是获得url字节流
            in = new BufferedInputStream(url.openStream());
            file = new FileOutputStream(new File(filePath));
            int t;
            while ((t = in.read()) != -1) {
                file.write(t);
            }
            file.close();
            in.close();
            //System.out.println("图片爬取成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
