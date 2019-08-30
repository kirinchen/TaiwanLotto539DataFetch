package net.surfm.ft539;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MinerSetuper {

	private WebDriver driver = null;

	@PostConstruct
	void init() {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
	}

	public void login() throws IOException {

		System.out.println("Test Open");
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver(); // launch chrome
		driver.manage().window().maximize();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 126; i++) {
			sb.append(fetchOnePage(i));
		}
		System.out.println(sb.toString());

		File file = new File("D:/lotto.csv");

		// In case file does not exists, Create the file
		if (!file.exists()) {
			file.createNewFile();
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(sb.toString());
		writer.close();

	}

	private String fetchOnePage(int pageCount) {
		driver.get("https://www.lotto-8.com/Taiwan/listlto539.asp?indexpage=" + pageCount);
		WebElement tableE = driver.findElement(By.xpath("//table[@class='auto-style1']"));
		boolean b = tableE.isDisplayed();
		System.out.println("findElement:" + b);

		List<WebElement> trs = tableE.findElements(By.tagName("tr"));
		StringBuilder sb = new StringBuilder();
		for (WebElement we : trs) {
			String valueText = we.getText();
			// System.out.println(valueText);
			String[] strs = valueText.split("\\r?\\n");
			if (strs.length < 2)
				continue;
			sb.append(getRowCsvText(strs));
			sb.append(System.lineSeparator());
		}
		return sb.toString();

	}

	private String getRowCsvText(String[] strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			if (StringUtils.isBlank(s))
				continue;
			if (s.contains(",")) {
				String[] nums = s.trim().split(",");
				sb.append(getRowCsvText(nums));
			} else {
				sb.append(s.trim());
			}
			sb.append(",");
		}

		return sb.toString();
	}

	private void setupConfig(WebDriver d) {
		System.out.println("setupConfig~");
		WebElement element = d.findElement(By.cssSelector("[ng-model*='pool.url']"));
		// element.clear();
		// element.sendKeys("xxx");

	}

}