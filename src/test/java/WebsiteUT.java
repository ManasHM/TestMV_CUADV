import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertTrue;

public class WebsiteUT {
    static Website Ws;

    @BeforeClass
    public static void initiate () {
        Ws = new Website();
    }
     @Test
    //UT for CHeckDriver
    public void checkDriver() {
        {
            if (Ws == null) {
                assertTrue(false);
                System.out.println("Website class instantiated Passed!");
            }

            if (Ws.getDriver() == null) {
                assertTrue(false);
                System.out.println("Driver class instantiation failed!");
            }
            System.out.println("Driver class instantiation successful!");
        }
    }

    @Test
    public void CheckLoadUrl() {
        WebDriver driver = Ws.LoadUrl(GlobalData.getHomeUrl());
        if (driver == null) {
            System.out.println("The url load failed!");
            assertTrue(false);
        }
        if (!driver.getCurrentUrl().contentEquals(GlobalData.getHomeUrl())) {
            System.out.println("The url does not match the home url!" + driver.getCurrentUrl() );
            assertTrue(false);

        }
        else {
            System.out.println("The actual url match the home url!:"
                    + driver.getCurrentUrl() + "---"
                    + GlobalData.getHomeUrl());
        }
    }
    @Test
    public void TestCheckTitle(){
        if (!Ws.checkTitle(GlobalData.getHomeUrl(),GlobalData.getHomeTitle())){
            System.out.println("The actual Title does not match the expected title for home page!:"
                    + GlobalData.getHomeUrl() + "---"
                    + GlobalData.getHomeTitle());
            assertTrue(false);
        }
        else {
            System.out.println("The actual Title matches the expected title for home page!:"
                    + GlobalData.getHomeUrl() + "---"
                    + GlobalData.getHomeTitle());
        }
    }
    @Test
    public void testValidateWebsite(){
        if (!Ws.validatePageTitles()){
            System.out.println("One of the pages have incorrect Title!") ;
            assertTrue(false);
        }
        else {
            System.out.println("All the pages have correct Title!") ;
        }
    }
@Test
public void testGetTagNamebyID( ){
        Ws.LoadUrl(GlobalData.getHomeUrl());
     String Tag = Ws.getTagNamebyID(GlobalData.getPage1ID());
    if (Tag == null){
        System.out.println("The element was not found on the page!") ;
        assertTrue(false);
    }
    else {
        System.out.println("Tag:" +
                Tag +" ID: "
                + GlobalData.getPage1ID()) ;
    }
}
@Test
public void testNavigateTobyID(){
Ws.LoadUrl(GlobalData.getHomeUrl());
    if (Ws.NavigateToByID(GlobalData.getPage1ID(),GlobalData.getPage1Title())){
        System.out.println("The navigation was successful!") ;
    }
    else{
        System.out.println("The Navigation failed!") ;
        assertTrue(false);
    }

}
@Test
    public void testValidateLinksByID(){
    if (!Ws.validateLinksByID(GlobalData.getHomeUrl())){
        System.out.println("One of the pages have incorrect Link on Home page!") ;
        assertTrue(false);
    }
    else {
        System.out.println("All the links are working fine on Home page!") ;
    }
}
@Test
public void testValidateAllPages(){
       if (!Ws.validateLinksAllPages()){
           System.out.println("One of the pages have incorrect Link on the website!") ;
           assertTrue(false);
       }
       else {
           System.out.println("All the links are working fine on the website!") ;
       }
       }

    @AfterClass
    public static void cleanup(){
        Ws.cleanupSession();
    }
}