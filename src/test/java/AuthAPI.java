import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AuthAPI {
    public String[] userInfo() {
        String email = "tech@netprice.io";
        String password = "password1!";
        String loginUrl = "https://api-test.netprice.io/User/Login?email="+email+"&password="+password;

        Response loginResponse = RestAssured.given()
                .param("email", email)
                .param("password", password)
                .when()
                .post(loginUrl);

        //Bearer Token
        String token = loginResponse.jsonPath().getString("data.token");
        System.out.println("Token: " + token);

        String companyUrl = "https://api-test.netprice.io/Company/List";

        Response companyResponse = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(companyUrl);

        JsonPath jsonPath = companyResponse.jsonPath();
        String companyId = jsonPath.getString("data[0].id");

        //Company ID
        System.out.println("Company-ID: " + companyId);

        String companyMarketplaceAccountUrl = "https://api-test.netprice.io/CompanyMarketplaceAccount/List?companyid="+companyId;

        Response companyMarketAccountResponse = RestAssured.given()
                .param("companyid", companyId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(companyMarketplaceAccountUrl);

        JsonPath companyMarketAccountJson = companyMarketAccountResponse.jsonPath();
        String companyUsername = companyMarketAccountJson.getString("data[0].username");
        String companyPassword = companyMarketAccountJson.getString("data[0].password");
        String marketPlaceId = companyMarketAccountJson.getString("data[0].marketplaceid");

        String companyMarketplaceProductUrl = "https://api-test.netprice.io/CompanyMarketplaceProduct/List?companyid="+companyId;

        Response companyMarketplaceProductResponse = RestAssured.given()
                .param("companyid", companyId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(companyMarketplaceProductUrl);

        JsonPath companyMarketplaceProductJson = companyMarketplaceProductResponse.jsonPath();
        String barcode = companyMarketplaceProductJson.getString("data[0].barcode");

        //Company Market Account
        System.out.println("UserName: " + companyUsername);
        System.out.println("Password: " + companyPassword);
        System.out.println("Barcode: " + barcode);
        System.out.println("Barcode: " + marketPlaceId);

        String[] userInfo = new String[6];
        userInfo[0] =companyUsername;
        userInfo[1] = companyPassword;
        userInfo[2] = companyId;
        userInfo[3] = marketPlaceId;
        userInfo[4] = token;
        userInfo[5] = barcode;
        return userInfo;
    }
}