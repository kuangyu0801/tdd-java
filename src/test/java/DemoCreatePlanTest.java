import demo.xml.parser.DemoCreatePlan;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

public class DemoCreatePlanTest {

    final static Logger logger = LoggerFactory.getLogger(DemoCreatePlanTest.class);
    String fileName;
    int tid;
    @Before
    public void init() {
        logger.debug("Initializing Test");
        tid = 10;
        fileName = "bpmn-demo.xml";
        logger.debug("creating EmptyPlan with id: {} and file name {}", tid, fileName);

    }

    @Test
    public void testCreateEmptyPlan() {
        Document doc = DemoCreatePlan.createEmptyPlan(tid, fileName);
        assertThat(doc != null, is(true));
    }

    @Test
    public void testFileExist() {

        File file = new File("src/main/resources/" + fileName);
        logger.debug("Delete existing file: {}", file);
        file.delete();

        Document doc = DemoCreatePlan.createEmptyPlan(tid, fileName);
        DemoCreatePlan.writeToXml(doc, fileName);
        //assertThat(file.exists(), is(true));
    }


    // reference: https://docs.oracle.com/javase/7/docs/api/javax/xml/validation/package-summary.html
    @Test
    public void testXmlValid() {
        File file = new File("src/main/resources/" + fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            documentBuilder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Parsing Fail");
        }
    }

    @Test
    public void testBPMNValid() {

    }
}
