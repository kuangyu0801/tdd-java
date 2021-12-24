package demo.xml.parser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DemoRead {
    private static Logger logger = LoggerFactory.getLogger(DemoRead.class);

    public static void main(String[] args){
        logger.info("Info log message {}" , 10);
        try {
            File inputFile = new File("src/main/resources/read-input.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("student");
            logger.info("nList length: {}" , nList.getLength());
            logger.info("Root element: {}", doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no : " + eElement.getAttribute("rollno"));
                    System.out.println("First Name : " + eElement.getElementsByTagName("firstname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Marks : " + eElement.getElementsByTagName("marks")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
