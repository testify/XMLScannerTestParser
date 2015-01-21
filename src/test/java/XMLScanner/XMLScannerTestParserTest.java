package XMLScanner;

import org.codice.testify.objects.ParsedData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.File;

@RunWith(JUnit4.class)
public class XMLScannerTestParserTest {

    //Set objects
    private final String currentDir = System.getProperty("user.dir");
    private final XMLScannerTestParser xmlScannerTestParser = new XMLScannerTestParser();
    private ParsedData parsedData = null;

    @Test
    public void testDataReturn() {
        File file = new File (currentDir + "/src/test/resources/Test1.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData != null );
        assert( parsedData.getRequest() != null );
    }

    @Test
    public void testTypeContent() {
        File file = new File (currentDir + "/src/test/resources/Test1.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getRequest().getType() != null );
        assert( parsedData.getRequest().getType().equals("SomeProcessor") );
    }

    @Test
    public void testEndpointContent() {
        File file = new File (currentDir + "/src/test/resources/Test1.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getRequest().getEndpoint() != null );
        assert( parsedData.getRequest().getEndpoint().equals("SomeEndpoint") );
    }

    @Test
    public void testTestblockContent() {
        File file = new File (currentDir + "/src/test/resources/Test1.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getRequest().getTestBlock() != null );
        assert( parsedData.getRequest().getTestBlock().equals("SomeTest") );
    }

    @Test
    public void testAssertionsContent() {
        File file = new File (currentDir + "/src/test/resources/Test1.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getAssertionBlock() != null );
        assert( parsedData.getAssertionBlock().equals("Assertion1" + System.lineSeparator() + "Assertion2") );
    }

    @Test
    public void testPreTestSetterActionContent() {
        File file = new File (currentDir + "/src/test/resources/Test1.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getActionData().getPreTestSetterAction() != null );
        assert( parsedData.getActionData().getPreTestSetterAction().equals("SomePreTestSetterAction") );
    }

    @Test
    public void testNoPreTestSetterActionContent() {
        File file = new File (currentDir + "/src/test/resources/Test2.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getActionData().getPreTestSetterAction() == null );
    }

    @Test
    public void testPreTestProcessorActionContent() {
        File file = new File (currentDir + "/src/test/resources/Test1.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getActionData().getPreTestProcessorAction() != null );
        assert( parsedData.getActionData().getPreTestProcessorAction().equals("SomePreTestProcessorAction") );
    }

    @Test
    public void testNoPreTestProcessorActionContent() {
        File file = new File (currentDir + "/src/test/resources/Test3.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getActionData().getPreTestProcessorAction() == null );
    }

    @Test
    public void testPostTestProcessorActionContent() {
        File file = new File (currentDir + "/src/test/resources/Test1.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getActionData().getPostTestProcessorAction() != null );
        assert( parsedData.getActionData().getPostTestProcessorAction().equals("SomePostTestProcessorAction") );
    }

    @Test
    public void testNoPostTestProcessorActionContent() {
        File file = new File (currentDir + "/src/test/resources/Test4.xml");
        parsedData = xmlScannerTestParser.parseTest(file);
        assert( parsedData.getActionData().getPostTestProcessorAction() == null );
    }
}