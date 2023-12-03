import Base.BaseLibrary;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpsertAPI extends BaseLibrary {
    public void upsertCommissions(String token, String companyid, String marketplaceid, String barcodeCode, String date, String[] prices, String[] commissions) {
        String jsonPath;
        var results = commissions[1];

        if (results == null) {
            jsonPath = "["
                    + "{"
                    + "\"displayorder\":0,"
                    + "\"name\":\"@name\","
                    + "\"companyid\":\"@companyid\","
                    + "\"marketplaceid\":\"@marketplaceid\","
                    + "\"barcode\":\"@barcodeCode\","
                    + "\"lowerprice\":0,"
                    + "\"higherprice\":0,"
                    + "\"commission\":" + convertNumeric(commissions[0])
                    + "}"
                    + "]";
        } else {
            jsonPath = "["
                    + "{"
                    + "\"displayorder\":0,"
                    + "\"name\":\"@name\","
                    + "\"companyid\":\"@companyid\","
                    + "\"marketplaceid\":\"@marketplaceid\","
                    + "\"barcode\":\"@barcodeCode\","
                    + "\"lowerprice\":" + convertNumeric(prices[0]) + ","
                    + "\"higherprice\":0,"
                    + "\"commission\":" + convertNumeric(commissions[0])
                    + "},"
                    + "{"
                    + "\"displayorder\":1,"
                    + "\"name\":'@name',"
                    + "\"companyid\":\"@companyid\","
                    + "\"marketplaceid\":\"@marketplaceid\","
                    + "\"barcode\":\"@barcodeCode\","
                    + "\"lowerprice\":" + convertNumeric(prices[1]) + ","
                    + "\"higherprice\":" + convertNumeric(prices[2]) + ","
                    + "\"commission\":" + convertNumeric(commissions[1])
                    + "},"
                    + "{"
                    + "\"displayorder\":2,"
                    + "\"name\":'@name',"
                    + "\"companyid\":\"@companyid\","
                    + "\"marketplaceid\":\"@marketplaceid\","
                    + "\"barcode\":\"@barcodeCode\","
                    + "\"lowerprice\":" + convertNumeric(prices[3]) + ","
                    + "\"higherprice\":" + convertNumeric(prices[4]) + ","
                    + "\"commission\":" + convertNumeric(commissions[2])
                    + "},"
                    + "{"
                    + "\"displayorder\":3,"
                    + "\"name\":'@name',"
                    + "\"companyid\":\"@companyid\","
                    + "\"marketplaceid\":\"@marketplaceid\","
                    + "\"barcode\":\"@barcodeCode\","
                    + "\"lowerprice\":0,"
                    + "\"higherprice\":" + convertNumeric(prices[5]) + ","
                    + "\"commission\":" + convertNumeric(commissions[3])
                    + "}"
                    + "]";
        }
        jsonPath = jsonPath.replace("@name", date);
        jsonPath = jsonPath.replace("@companyid", companyid);
        jsonPath = jsonPath.replace("@marketplaceid", marketplaceid);
        jsonPath = jsonPath.replace("@barcodeCode", barcodeCode);
        System.out.println(jsonPath);

        String apiUrl = "https://api-test.netprice.io/CompanyMarketplaceProduct/UpsertCommissions";

        RestAssured.given()
                .header("accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json-patch+json")
                .body(jsonPath)
                .contentType(ContentType.JSON)
                .when()
                .post(apiUrl)
                .then()
                .statusCode(200);
    }
}
