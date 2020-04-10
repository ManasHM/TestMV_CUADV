import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Website {
    WebDriver driver;

    public WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        String browserName = GlobalData.getBrowserName();
        switch (browserName) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", GlobalData.getWDLocation());
                driver = new ChromeDriver();
                return driver;
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", GlobalData.getWDLocation());
                driver = new FirefoxDriver();
                return driver;
            default:
                throw new IllegalStateException("Unexpected value: " + browserName);
        }
    }

    public WebDriver LoadUrl(String Url) {
        getDriver();
        driver.get(Url);
        return driver;
    }

    public boolean checkTitle(String Url, String Title) {
        driver = LoadUrl(Url);
        String actualTitle = driver.getTitle();
        if (actualTitle.startsWith(Title)) {
            return true;
        }
        else{
            System.out.println("The actual Title does not match the expected title for this page!:"
                    + actualTitle + "---"
                    + Title);
        }
        return false;
    }


    public boolean validateWebsite() {

        if (!checkTitle(GlobalData.getHomeUrl(), GlobalData.getHomeTitle())) {
            throw new IllegalStateException("Home page title wrong ");
        }
        if (GlobalData.getPage1() != null) {
            if (!checkTitle(GlobalData.getPage1(), GlobalData.getPage1Title())) {
                throw new IllegalStateException("Page1 title wrong ");
            }
        }
        if (GlobalData.getPage2() != null) {
            if (!checkTitle(GlobalData.getPage2(), GlobalData.getPage2Title())) {
                throw new IllegalStateException("Page2 title wrong ");
            }
        }
        if (GlobalData.getPage3() != null) {
            if (!checkTitle(GlobalData.getPage3(), GlobalData.getPage3Title())) {
                throw new IllegalStateException("Page3 title wrong ");
            }
        }

        return true;
    }

    public void cleanupSession() {
        if (driver != null) {
            driver.quit();
        }
    }
}