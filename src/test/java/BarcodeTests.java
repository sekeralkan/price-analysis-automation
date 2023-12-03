import Base.BaseTest;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.ProductsPage;
import Pages.PromotionPage;
import org.testng.annotations.Test;

public class BarcodeTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    PromotionPage promotionPage = new PromotionPage();
    ProductsPage productsPage = new ProductsPage();
    AuthAPI authAPI = new AuthAPI();
    UpsertAPI upsertAPI = new UpsertAPI();

    @Test(description = "Barcode Price Analysis")
    public void BarcodeAnalysis() throws Exception {
        var result = authAPI.userInfo();
        String username = result[0];
        String password = result[1];
        String companyId = result[2];
        String marketplaceId = result[3];
        String token = result[4];
        String barcode = result[5];

        String barcodeCode = "7640128140917";

        loginPage
                .userName("ssekeralkan@gmail.com")
                .password("Qwert123!.")
                .login();

        sleep(5000);

        mainPage
                .menuSelect("Ürün")
                .downMenuSelect("Ürün Listesi");

        sleep(5000);

        productsPage
                .fillModelCode(barcodeCode);

        sleep(5000);

        var commissions = productsPage.getCommissionInfo();
        String[] prices = productsPage.getPriceInfo();
        String date = productsPage.getTabDate();

        upsertAPI.upsertCommissions(token, companyId, marketplaceId, barcodeCode, date, prices, commissions);
    }

}
