package com.hnfealean.sport.web;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;

public class SitemapTool {

	public static void addsitemapElement(String fileSourceLocate,String filedestination,Element ele){
		
		DocumentBuilderFactory dbf = DocumentBuilderFactoryImpl.newInstance();
		try{
		DocumentBuilder db =dbf.newDocumentBuilder();
		Document doc = db.parse(fileSourceLocate);
		NodeList nl = doc.getElementsByTagName("loc");
	/*	Text textmsg;
		Element url = doc.createElement("url");
		textmsg =doc.createTextNode("http://www.hnfealean.cn");
		url.appendChild(textmsg);
		*/
		doc.getDocumentElement().appendChild(ele);
		
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new java.io.File(filedestination));
		transformer.transform(source, result);
		System.out.print(nl.getLength());
		//for(int i=0;i<nl.getLength();i++){
			
		//	System.out.println(nl.item(i).getFirstChild().getNodeValue());
//		}
		}catch(Exception e){
			
		}
		System.out.print("done");
		}

	public static void addsitemapElements(String fileSourceLocate,String filedestination,Element[] elements){
		DocumentBuilderFactory dbf = DocumentBuilderFactoryImpl.newInstance();
		try{
		DocumentBuilder db =dbf.newDocumentBuilder();
		Document doc = db.parse(fileSourceLocate);
		NodeList nl = doc.getElementsByTagName("loc");
		/*
		 * Text textmsg;
		Element url = doc.createElement("url");
		textmsg =doc.createTextNode("http://www.hnfealean.cn");
		
		url.appendChild(textmsg);
		*/
		for(int i=0;i<elements.length;i++){
			doc.getDocumentElement().appendChild(elements[i]);
		}		
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new java.io.File(filedestination));
		transformer.transform(source, result);
		System.out.print(nl.getLength());
		}catch(Exception e){
			
		}

		}	
	public static void updateElement(String fileSourceLocate,String filedestination,Element element){
		DocumentBuilderFactory dbf = DocumentBuilderFactoryImpl.newInstance();
		try{
		DocumentBuilder db =dbf.newDocumentBuilder();
		Document doc = db.parse(fileSourceLocate);
		NodeList nl = doc.getElementsByTagName("loc");
		/*
		 * Text textmsg;
		Element url = doc.createElement("url");
		textmsg =doc.createTextNode("http://www.hnfealean.cn");
		
		url.appendChild(textmsg);
		*/
		
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new java.io.File(filedestination));
		transformer.transform(source, result);
		System.out.print(nl.getLength());
		}catch(Exception e){
			
		}

		}		
	}


