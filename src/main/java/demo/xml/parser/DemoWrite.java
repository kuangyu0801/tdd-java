package demo.xml.parser;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;

public class DemoWrite {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element root = doc.createElement("cars");
            doc.appendChild(root);

            Element supercar = doc.createElement("supercars");
            root.appendChild(supercar);

            Attr attrCompany  = doc.createAttribute("company");
            attrCompany.setValue("Ferrari");
            supercar.setAttributeNode(attrCompany);

            Element carname = doc.createElement("carname");
            Attr attrType = doc.createAttribute("type");
            attrType.setValue("formula one");
            carname.appendChild(doc.createTextNode("Ferrari 101"));
            supercar.appendChild(carname);

            carname = doc.createElement("carname");
            attrType = doc.createAttribute("type");
            attrType.setValue("sports");
            carname.appendChild(doc.createTextNode("Ferrari 202"));
            supercar.appendChild(carname);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            FileWriter writer = new FileWriter(new File("src/main/resources/output.xml"));
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
