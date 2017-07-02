/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Herman
 */
public class Dom4JParser {
    private File inputFile;
    private SAXReader reader;
    private Document document;
    private Element rootElement;

    public Dom4JParser() {
        try {
            inputFile = new File("src/main/java/parser/jdbc-data.xml");
            reader = new SAXReader();
            document = reader.read(inputFile);
            rootElement = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public String getUrl() {
        Node node = rootElement.selectSingleNode("url");
        return (node.getText());
    }
    public String getDriver() {
        Node node = rootElement.selectSingleNode("driver");
        return (node.getText());
    }
    public String getUser() {
        Node node = rootElement.selectSingleNode("user");
        return (node.getText());
    }
    public String getPassword() {
        Node node = rootElement.selectSingleNode("password");
        return (node.getText());
    }
}
