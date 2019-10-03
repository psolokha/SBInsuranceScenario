import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ScenarioTest {

    @BeforeClass
    public static void scenarioConfigurator() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }


    @Test
    public void scenarioTest() {
        Utils driver = Utils.getDriver();
        driver.get("http://www.sberbank.ru/ru/person");
        new Actions(driver).moveToElement(driver.getElement("//li//span[contains(text(), 'Страхование')]")).moveToElement(driver.getElement("//li[@class='lg-menu__sub-item']/a[contains(text(), 'Страхование путешественников')]")).click().perform();
        Assert.assertTrue(driver.getElement("//title[contains(text(), 'Страхование путешественников')]").isEnabled());
        driver.clickElement("//a/img[contains(@src, 'zashita-traveler')]");
        driver.switchTo().window(driver.getOtherWindow());

        try {
            driver.waitElement("//span[text()='Выбор полиса']");
        } catch (Exception ex) {
            System.out.println(driver.getTitle());
            return;
        }

        driver.clickElement("//div[@class='b-form-prog-block ng-scope'][1]");
        driver.clickElement("//span[text()='Оформить']");
        driver.fillInput("//input[@name='insured0_birthDate']", "11112011\t");
        driver.fillInput("//input[@name='insured0_surname']", "PETROV");
        driver.fillInput("//input[@name='insured0_name']", "PETR");
        driver.fillInput("//input[@name='surname']", "Иванов");
        driver.fillInput("//input[@name='name']", "Иван");
        driver.fillInput("//input[@name='middlename']", "Иваныч");
        driver.fillInput("//input[@name='birthDate']", "11111991\t");
        driver.clickElement("//span[contains(@ng-class, 'GENDER == 1')]");
        driver.fillInput("//input[@name='passport_series']", "1111");
        driver.fillInput("//input[@name='passport_number']", "111111");
        driver.fillInput("//input[@name='issueDate']", "1112011\t");
        driver.fillInput("//textarea", "111111");
        Assert.assertFalse(driver.findElement(By.xpath("//*[contains(@class, 'text-field-error')]")).isDisplayed());
        driver.clickElement("//span[contains(text(), 'Продолжить')]");
        Assert.assertTrue(driver.getElement("//div[text()='Заполнены не все обязательные поля']").isDisplayed());

    }

    @AfterClass
    public static void closeDriver(){
        Utils.getDriver().quit();
    }

}
