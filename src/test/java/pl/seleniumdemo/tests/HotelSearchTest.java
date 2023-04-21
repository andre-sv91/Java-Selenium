package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPages;

import java.util.List;

public class HotelSearchTest extends BaseTest {
    @Test
    public void searchHotelTest() throws InterruptedException {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        List<String> hotelNames = hotelSearchPage.setCity("Dubai")    //zapis fluent
                .setDates("27/04/2023", "29/04/2023")
                .setTravellers(1, 2)
                .performeSearch().getHotelNames();

        ResultPages resultPages = new ResultPages(driver);


        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));


    }

    @Test
    public void searchHotelWithoutNameTest() {
        ResultPages resultPages = new HotelSearchPage(driver)
                .setDates("25/04/2023", "30/04/2023")
                .setTravellers(0, 1)
                .performeSearch();


        Assert.assertEquals("No Results Found", resultPages.getHeadingText());

    }
}
