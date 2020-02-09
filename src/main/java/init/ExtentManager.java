package init;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.LoggerFactory;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static String reportFileName = "Test-Automaton-Report"+".html";
    private static String fileSeparator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") + fileSeparator + "testReport";
    private static String reportFileLocation =  reportFilepath + fileSeparator + reportFileName;
    private static final LoggerFactory logger = new LoggerFactory(ExtentManager.class);


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }


    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }


    private static String getReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                logger.info("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                logger.info("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            logger.info("Directory already exists: " + path);
        }
        return reportFileLocation;
    }
}
