import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Utils extends ChromeDriver{

    private static Utils instance;

    private Utils(){}

    static Utils getDriver() {
        if (instance == null) instance = new Utils();
        return instance;
    }

    WebElement getElement(String xpath) {
        return instance.findElement(By.xpath(xpath));
    }

    void fillInput(String xpath, String text) {
        instance.getElement(xpath).click();
        instance.getElement(xpath).sendKeys(text);
    }

    void clickElement(String xpath) {
        waitElement(xpath);
        getElement(xpath).click();
    }

    void waitElement(String xpath) {
        new WebDriverWait(instance,10, 1000).until(ExpectedConditions.elementToBeClickable(getElement(xpath)));
    }

    String getOtherWindow() {
        return (String)instance.getWindowHandles().stream().filter(str -> !str.equals(instance.getWindowHandle())).toArray()[0];
    }

}
