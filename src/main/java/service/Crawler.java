package service;

import org.jsoup.nodes.Document;

import java.util.List;

public interface Crawler {
    Document getConnection(String url) throws Exception;

    List analysisDoc(Object o);

    void writeIn(Object o, String path);


}
