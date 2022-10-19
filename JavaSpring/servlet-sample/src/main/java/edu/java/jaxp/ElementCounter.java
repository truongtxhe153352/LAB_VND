package edu.java.jaxp;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderAdapter;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class ElementCounter extends DefaultHandler {
    private int counter = 0;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.printf("start element ----" + qName);
    if ("book".equals(qName)) counter++;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.printf("en element ----" + qName);
        System.out.println("Found" + counter + "book element in the document!");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(" text data = | " + new String(ch, start, length) + "|");
    }

    public static void main(String[] args) throws SAXException, IOException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        ContentHandler handler = new ElementCounter();
        parser.setContentHandler(handler);
       InputStream stream = ElementCounter.class.getResource("Books.xml").openStream();
        parser.parse(new InputSource(stream));
        System.out.printf("done");
    }
}
