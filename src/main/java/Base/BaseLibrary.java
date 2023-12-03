package Base;

import org.openqa.selenium.*;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseLibrary {
    Random rand = new Random();
    public static WebDriver driver;

    public int randomNumber(int length) throws IOException {
        return rand.nextInt(length);
    }

    public String convertNumeric(String text) {
        String regex = "\\b\\d+(\\.\\d+)?\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        matcher.find();
        return matcher.group();
    }

    public String splitNumeric(String text) {
        String regex = "\\b\\d+(\\.\\d+)?\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        matcher.find();
        return matcher.group().replace(',', '.');
    }

    public String[] splitMultiNumeric(String text) {
        String regex = "\\b\\d+(\\.\\d+)?\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        String[] values = new String[2];
        matcher.find();
        values[0] = matcher.group();

        matcher.find();
        values[1] = matcher.group().replace(',', '.');
        return values;
    }

    public String randomUser() throws IOException {
        RandomAccessFile file = new RandomAccessFile("documents/users.txt", "r");
        long fileLength = file.length();
        Random rand = new Random();
        long randomLineNumber = (long) (rand.nextDouble() * fileLength);
        file.seek(randomLineNumber);
        String randomLineUser = file.readLine();
        file.close();
        return randomLineUser;
    }

    public BaseLibrary writePhoneURL(int length) {
        try {
            int i = 0;
            while (length > i) {
                PrintWriter writer = new PrintWriter(new FileOutputStream(new File("documents/PhoneList.txt"), true));
                writer.println(driver.findElements(By.cssSelector("[class='phone-number']")).get(i).getText());
                writer.close();

                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String readPhone(int length) {
        String fileName = "documents/PhoneList.txt";
        String line = "";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int currentIndex = 1;

            while ((line = bufferedReader.readLine()) != null) {
                if (currentIndex == length) {
                    System.out.println("Satır " + length + ": " + line);
                    break;
                }
                currentIndex++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public BaseLibrary writeURL(String url) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("documents/UrlList.txt"), true));
            writer.println(url);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    public BaseLibrary fileDelete(String url) {
        String filePath = url;

        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Dosya başarıyla silindi.");
            } else {
                System.out.println("Dosya silinirken bir hata oluştu.");
            }
        } else {
            System.out.println("Dosya mevcut değil, silinemedi.");
        }
        return this;
    }

    public static String generateRandomPhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder();
        Random rand = new Random();

        phoneNumber.append("(");
        phoneNumber.append(5);
        phoneNumber.append(2);
        phoneNumber.append(5);
        phoneNumber.append(") ");

        phoneNumber.append(2);
        phoneNumber.append(rand.nextInt(10));
        phoneNumber.append(rand.nextInt(10));
        phoneNumber.append("-");

        phoneNumber.append(rand.nextInt(10));
        phoneNumber.append(rand.nextInt(10));
        phoneNumber.append(rand.nextInt(10));
        phoneNumber.append(rand.nextInt(10));

        return phoneNumber.toString();
    }

    public BaseLibrary tabOpen(int index) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[index].toString());
        return this;
    }

    public BaseLibrary newTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        return this;
    }

    public BaseLibrary openURL(String URL) {
        driver.get(URL);
        return this;
    }

    public BaseLibrary jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        return this;
    }

    public BaseLibrary sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
