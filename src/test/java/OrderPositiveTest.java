import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page_objects.DriverFactory;
import page_objects.OrderPage;

@RunWith(Parameterized.class)
public class OrderPositiveTest {

    private WebDriver driver;
    private OrderPage orderPage;

    @Before
    public void startOrderPage() {
        driver = driverFactory.getDriver();
        orderPage = new OrderPage(driver);
        orderPage.openWebsite();
    }

    @After
    public void tearDown() {
        driverFactory.quitDriver();
    }

    private DriverFactory driverFactory = new DriverFactory();

    private final String name;
    private final String familyName;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String date;
    private final String duration;
    private final boolean colour;
    private final String comment;
    private final boolean useTopButton;


    public OrderPositiveTest(String name, String familyName, String address, String metro, String phoneNumber, String date, String duration, boolean colour, String comment, boolean useTopButton) {
        this.name = name;
        this.familyName = familyName;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
        this.useTopButton = useTopButton;
    }



    @Parameterized.Parameters
    public static Object[][] get() {
        return new Object[][]{
                {"Аристарх", "Аристархов", "улица Маяковского", "1",
                        "89101231212", "30", "сутки", true, "Везите скорее", true},
                {"Лидия", "Иванова", "Тверская", "2",
                        "89990009988", "11", "двое суток", false, "Позвоните за 10 минут", false}
        };
    }


    @Test
    public void orderScooterPositive() {
        orderPage.clickCookieButton();

        if (useTopButton) {
            orderPage.clickOnOrderButtonTop();
        } else {
            orderPage.clickOnOrderButtonMiddle();
        }

        orderPage.fillInPersonalDetailsPage(name, familyName, address, metro, phoneNumber);
        orderPage.fillInAboutRentPage(date, duration, colour, comment);
        orderPage.confirmOrder();
        Assert.assertTrue(orderPage.isOrderSuccessful());

    }

}
