package demo.xml.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DemoQuery {
    public static void main(String[] args) {
        try {
            File input = new File("src/main/resources/query-input.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(input);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("supercars");
            for (int i = 0; i < nodeList.getLength(); i += 1) {
                Node node = nodeList.item(i);
                System.out.println("Current Element : " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("company :" + element.getAttribute("company"));
                    NodeList carList = element.getElementsByTagName("carname");
                    for (int j = 0; j < carList.getLength(); j += 1) {
                        Node carNode = carList.item(0);

                        if (carNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element car = (Element) carNode;
                            System.out.println("car name: " + car.getTextContent());
                            System.out.println("car type: " + car.getAttribute("type"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
