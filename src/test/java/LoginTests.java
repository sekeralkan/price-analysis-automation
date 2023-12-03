import Base.BaseTest;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.PromotionPage;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    PromotionPage promotionPage = new PromotionPage();
    AuthAPI authAPI = new AuthAPI();
    UpsertAPI upsertAPI = new UpsertAPI();

    @Test(description = "Price Analysis")
    public void PriceAnalysis() throws Exception {
        var result = authAPI.userInfo();
        String username = result[0];
        String password = result[1];
        String companyId = result[2];
        String marketplaceid = result[3];
        String token = result[4];
        String barcode = result[5];
        String barcodeCode = "8697432095593";

        loginPage
                .userName("ssekeralkan@gmail.com")
                .password("Qwert123!.")
                .login();

        sleep(5000);

        mainPage
                .menuSelect("Promosyon & Fiyat")
                .downMenuSelect("Ürün Komisyon Tarifeleri");

        sleep(5000);

        promotionPage
                .fillModelCode(barcode);

        sleep(5000);

        var commissions = promotionPage.commissionAreas();
        var prices = promotionPage.priceAreas();
        String date = promotionPage.getTabDate();

        upsertAPI.upsertCommissions(token, companyId, marketplaceid, barcodeCode, date, prices, commissions);
    }

}
