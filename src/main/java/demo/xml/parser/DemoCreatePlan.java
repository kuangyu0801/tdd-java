package demo.xml.parser;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DemoCreatePlan {

    public static Document createEmptyPlan(int id, String fileName) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();

            Element root = doc.createElement("bpmn:definitions");
            doc.appendChild(root);

            String[][] pairs = {
                    {"xmlns:bpmn", "http://www.omg.org/spec/BPMN/20100524/MODEL"},
                    {"xmlns:bpmndi", "http://www.omg.org/spec/BPMN/20100524/DI"},
                    {"xmlns:dc", "http://www.omg.org/spec/DD/20100524/DC"},
                    {"xmlns:camunda", "http://camunda.org/schema/1.0/bpmn"},
                    {"xmlns:di", "http://www.omg.org/spec/DD/20100524/DI"},
                    {"xmlns:qa", "http://some-company/schema/bpmn/qa"},
                    {"id", "Definitions_0ym33az"},
                    {"targetNamespace", "http://bpmn.io/schema/bpmn"}
            };

            // set all required ns
            for (String[] pair : pairs) {
                Attr attr = doc.createAttribute(pair[0]);
                attr.setValue(pair[1]);
                root.setAttributeNode(attr);
            }

            Element process = doc.createElement("bpmn:process");
            Element diagram = doc.createElement("bpmndi:BPMNDiagram");

            root.appendChild(process);
            root.appendChild(diagram);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    public static void writeToXml(Document doc, String fileName) {
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            // Export to file
            FileWriter writer = new FileWriter(new File("src/main/resources/" + fileName));
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToConsole(Document doc, String fileName) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            // Export to std
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
