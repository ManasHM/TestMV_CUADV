import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public boolean checkTitle(String Url, String title) {
        driver = LoadUrl(Url);
        String actualTitle = driver.getTitle();
        if (actualTitle.startsWith(title)) {
            return true;
        } else {
            System.out.println("The actual title does not match the expected title for this page!:"
                    + actualTitle + "---"
                    + title);
        }
        return false;
    }


    public boolean validatePageTitles() {

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

    public String getTagNamebyID(String ID) {

        String tagName = driver.findElement(By.id("menu-item-18")).getTagName();
        if (tagName == null) {
            throw new IllegalStateException("Element id not found : " + ID);
        }
        return tagName;
    }

    public boolean NavigateToByElement(WebElement link, String title) {
        if (link == null) {
            throw new IllegalStateException("Link is blank: ");
        }
        link.click();
        if (driver.getTitle().startsWith(title)) {
            return true;
        } else {
            System.out.println("The actual title does not match the expected title for this page!:"
                    + driver.getTitle() + "---"
                    + GlobalData.getPage1Title());
            return false;
        }

    }

    public boolean NavigateToByID(String pageID, String title) {
        // Go to home Page first
        // driver.get(GlobalData.getHomeUrl());
        WebElement link = driver.findElement(By.id(pageID));
        if (link == null) {
            throw new IllegalStateException("Element id not found : " + pageID);
        }
        return NavigateToByElement(link, title);
    }

    public boolean NavigateToByLinkText(String linkText, String title) {
        // Stay on same page
        //driver.get(GlobalData.getHomeUrl());
        WebElement link = driver.findElement(By.linkText(linkText));
        if (link == null) {
            throw new IllegalStateException("Element id not found for linkText : " + linkText);
        }
        return NavigateToByElement(link, title);
    }

    public boolean validateLinksByLinkText(String url) {
        // Begin with the specified page
        LoadUrl(url);

        if (!NavigateToByLinkText(GlobalData.getHomeLinkText(), GlobalData.getHomeTitle())){
                throw new IllegalStateException("Page 1 LInk Validation failed on" + url);
            }


        if (GlobalData.getPage1() != null) {
           if (!NavigateToByLinkText(GlobalData.getPage1LinkText(), GlobalData.getPage1Title())){
               throw new IllegalStateException("Page 1 LInk Validation failed on" + url);
           }
        }
        //Back to home page
        driver.navigate().back();

        if (GlobalData.getPage2() != null) {
            if (!NavigateToByLinkText(GlobalData.getPage2LinkText(), GlobalData.getPage2Title())){
                throw new IllegalStateException("Page 2 LInk Validation failed on" + url);
            }
        }
        //Back to home page
        driver.navigate().back();

        if (GlobalData.getPage3() != null) {
            if (!NavigateToByLinkText(GlobalData.getPage3LinkText(), GlobalData.getPage3Title())){
                throw new IllegalStateException("Page 3 LInk Validation failed on" + url);
            }
        }
        //Back to home page
        driver.navigate().back();
        return true;
    }

    public boolean validateLinksByID(String url) {
        // Begin with the page which needs to be validated
        LoadUrl(url);

        if (GlobalData.getPage1() != null) {
            if (!NavigateToByID(GlobalData.getPage1ID(), GlobalData.getPage1Title())) {
                throw new IllegalStateException("Page 1 Navigation failed ");
            }
        }
        //Back to home page
        driver.navigate().back();

        if (GlobalData.getPage2() != null) {
            if (!NavigateToByID(GlobalData.getPage2ID(), GlobalData.getPage2Title())) {
                throw new IllegalStateException("Page 2 Navigation failed ");
            }
        }
        //Back to home page
        driver.navigate().back();
        if (GlobalData.getPage3() != null) {
            if (!NavigateToByID(GlobalData.getPage3ID(), GlobalData.getPage3Title())) {
                throw new IllegalStateException("Page 3 Navigation failed ");
            }
        }
        return true;
    }
public boolean validateLinksAllPages(){
        validateLinksByLinkText(GlobalData.getHomeUrl());
    if (GlobalData.getPage1() != null) {
        validateLinksByLinkText(GlobalData.getPage1());
    }
    if (GlobalData.getPage2() != null) {
        validateLinksByLinkText(GlobalData.getPage2());
    }
    if (GlobalData.getPage3() != null) {
        validateLinksByLinkText(GlobalData.getPage3());
    }
    return true;
}
}


