package parser;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomXmlParser {
	DocumentBuilder builder = null;
	Document document = null;
	Element rootElement = null;
	
	public DomXmlParser(){
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		try {
		    builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		    e.printStackTrace();  
		}
		try {
		    document = builder.parse(new FileInputStream("src/main/java/parser/jdbc-data.xml"));
		} catch (SAXException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		rootElement = document.getDocumentElement();
	}
	
	public String getUrl(){
		NodeList urlNodes = rootElement.getElementsByTagName("url");
		return urlNodes.item(0).getTextContent();
	}
	
	public String getDriver(){
		NodeList driverNodes = rootElement.getElementsByTagName("driver");
		return driverNodes.item(0).getTextContent();
	}
	
	public String getUser(){
		NodeList userNodes = rootElement.getElementsByTagName("user");
		return userNodes.item(0).getTextContent();
	}
	
	public String getPassword(){
		NodeList passNodes = rootElement.getElementsByTagName("password");
		return passNodes.item(0).getTextContent();
	}
}
