package commons;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}


	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookies (WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		waitForAlertPresence(driver).sendKeys(value);
	}

	public void switchToWindowByID(WebDriver driver, String windowPageID) { // ham dung chi 2

		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(windowPageID)) {
				driver.switchTo().window(window);
			}
		}
	}

	public void switchToWindowByPageTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			driver.switchTo().window(window);
			sleepInSecond(2);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentPageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentPageID)) {
				driver.switchTo().window(window);
				sleepInSecond(2);

				driver.close();
			}
		}

		driver.switchTo().window(parentPageID);
		sleepInSecond(1);
	}
	

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
	}
	
	public void sleepInMilisecond(long timeoutInMilisecond) {
		try {
			Thread.sleep(timeoutInMilisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		if(locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		}else if(locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		}else if(locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		}else if(locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		}else if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		}else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;
	}
	
	/*
	 * dynamic - ep kieu
	 */
	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues); // ép kiểu
		}
		return locatorType;
	}
	
	
	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String value) {
		getWebElement(driver, locatorType).clear();
		getWebElement(driver, locatorType).sendKeys(value);
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String value, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).clear();
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).sendKeys(value);
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String locatorType, String itemText) {
		new Select(getWebElement(driver, locatorType)).selectByVisibleText(itemText);
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String locatorType, String itemText, String... dynamicValues) {
		new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).selectByVisibleText(itemText);
	}
	
	public String getSelectTextDefaultDropdown(WebDriver driver, String locatorType) {
		return new Select(getWebElement(driver, locatorType)).getFirstSelectedOption().getText();
	}
	
	public boolean isDefaultDropdownMultiple(WebDriver driver, String locatorType) {
		return new Select(getWebElement(driver, locatorType)).isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getHexaColorByRgbaColor(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}

	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	
	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	public void checkToRadioOrCheckbox(WebDriver driver, String locatorType) {
		if(!getWebElement(driver, locatorType).isSelected()) {
			getWebElement(driver, locatorType).click();
		}
	}
	
	public void checkToRadioOrCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		if(!getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected()) {
			getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locatorType) {
		if(getWebElement(driver, locatorType).isSelected()) {
			getWebElement(driver, locatorType).click();
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		if(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected()) {
			getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
		}
	}
	
	public boolean isElementEnabled(WebDriver driver, String xpathExpression) {
		return getWebElement(driver, xpathExpression).isEnabled();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
		
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementUndisplayed (WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement >elements = getListWebElement(driver, locator);
		overrideGlobalTimeout(driver, longTimeout);
		
		if(elements.size() == 0) {
			return true;
		}else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathExpression) {
		return getWebElement(driver, xpathExpression).isSelected();
	}

	public void switchToFrame(WebDriver driver, String xpathExpression) {
		driver.switchTo().frame(getWebElement(driver, xpathExpression));
	}

	public void switchToDefaultContentPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathExpression) {
		new Actions(driver).moveToElement(getWebElement(driver, xpathExpression)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locatorType), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		new Actions(driver).sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}
	
	public void pressKeyboardToElement(WebDriver driver, String xpathExpression, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, xpathExpression),key).perform();
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String xpathExpression) {
		WebElement element = getWebElement(driver, xpathExpression);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void highlightElement(WebDriver driver, String xpathExpression, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathExpression, dynamicValues));
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	
	public void clickToElementByJS(WebDriver driver, String xpathExpression) {

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpathExpression));
	}
	
	public void clickToElementByJS(WebDriver driver, String xpathExpression, String... dynamicValues) {

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(xpathExpression, dynamicValues)));
	}

	public void scrollToElement(WebDriver driver, String xpathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathExpression));
	}
	
	
	public String getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
		xpathLocator = xpathLocator.replace("xpath=", "");
		return (String) ((JavascriptExecutor) driver).executeScript("return $(document.evaluate(\""  + xpathLocator + " \", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
	}
	
	

	public void sendkeyToElementByJS(WebDriver driver, String xpathExpression, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, xpathExpression));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathExpression, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathExpression));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTimeout);
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitwait.until(jQueryLoad) && explicitwait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathExpression) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathExpression));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathExpression) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathExpression));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isImageLoaded(WebDriver driver, String xpathExpression, String... dynamicValues) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(xpathExpression, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}


	public void waitForElementVisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(xpathExpression)));
	}
	
	public void waitForElementVisible(WebDriver driver, String xpathExpression, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(xpathExpression, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(xpathExpression)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathExpression, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(xpathExpression, dynamicValues))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(xpathExpression)));
	}
	/*
	 * Wait for element undisplayed in DOM or not in DOME and override implicit timeout
	 */
	public void waitForElementUndisplayed(WebDriver driver, String xpathExpression) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(xpathExpression)));
		overrideGlobalTimeout(driver, longTimeout);
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpathExpression, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(xpathExpression, dynamicValues))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(xpathExpression)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpathExpression, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(xpathExpression, dynamicValues))));
	}
	
	public void waitForAllElementClickable(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathExpression)));
	}

	
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	
}
	
