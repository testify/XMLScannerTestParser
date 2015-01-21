package XMLScanner;

import org.codice.testify.objects.*;
import org.codice.testify.testParsers.TestParser;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * The XMLScannerTestParser class is a Testify TestParser service for extension type xml
 */
public class XMLScannerTestParser implements BundleActivator, TestParser {

    @Override
    public ParsedData parseTest(File file) {

        TestifyLogger.debug("Running XMLScannerTestParser", this.getClass().getSimpleName());

        //Scan the file into a String
        String testString = null;
        try {
            Scanner scanner = new Scanner(file);
            testString = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException e) {
            TestifyLogger.error(e.getMessage(), this.getClass().getSimpleName());
        }

        //If the test file string is null, set parsed data to null
        if (testString == null) {
            return null;

        //If test file string contains certain key words for this parser, then parse the string
        } else if (testString.contains("<type>") && testString.contains("<endpoint>") && testString.contains("<test>") && testString.contains("<assertion>")) {

            String type = testString.substring(testString.indexOf("<type>") + 6, testString.indexOf("</type>")).trim();
            String endpoint = testString.substring(testString.indexOf("<endpoint>") + 10, testString.indexOf("</endpoint>")).trim();
            String test = testString.substring(testString.indexOf("<test>") + 6, testString.indexOf("</test>")).trim();
            String assertion = testString.substring(testString.indexOf("<assertion>") + 11, testString.indexOf("</assertion>")).trim();

            String preTestSetterAction = null;
            if (testString.contains("<preTestSetterAction>")) {
                preTestSetterAction = testString.substring(testString.indexOf("<preTestSetterAction>") + 21, testString.indexOf("</preTestSetterAction>")).trim();
            }

            String preTestProcessorAction = null;
            if (testString.contains("<preTestProcessorAction>")) {
                preTestProcessorAction = testString.substring(testString.indexOf("<preTestProcessorAction>") + 24, testString.indexOf("</preTestProcessorAction>")).trim();
            }

            String postTestProcessorAction = null;
            if (testString.contains("<postTestProcessorAction>")) {
                postTestProcessorAction = testString.substring(testString.indexOf("<postTestProcessorAction>") + 25, testString.indexOf("</postTestProcessorAction>")).trim();
            }

            //Store the request, assertion, and action data in the parsed data object
            Request request = new Request(type, endpoint, test);
            ActionData actionData = new ActionData(preTestSetterAction, preTestProcessorAction, postTestProcessorAction);
            return new ParsedData(request, assertion, actionData);

        //If the test file string does not contain certain key words, set parsed data to null
        } else {
            return null;
        }
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        //Register the TestParser service for extension xml
        Hashtable<String, String> extension = new Hashtable<>();
        extension.put("extension", "xml");
        bundleContext.registerService(TestParser.class.getName(), new XMLScannerTestParser(), extension);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
