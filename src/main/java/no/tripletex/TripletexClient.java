package no.tripletex;

import com.squareup.okhttp.*;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by runar on 15.02.16.
 */
public class TripletexClient {

    public static final MediaType MEDIA_TYPE_JSON  = MediaType.parse("application/json");
    private static final String serverUrl = "https://tripletex.no/JSON-RPC";

    public static final String METHOD_LOGIN = "{\"method\": \"Sync.login\", \"params\":[%s,\"%s\",\"%s\",\"%s\"],\"id\": 1}";
    public static final String METHOD_LOGOUT = "{\"method\": \"Sync.logout\", \"params\": [], \"id\": 1}";
    public static final String METHOD_LOGIN_COMPANY_NAME = "{\"method\": \"Company.getLoginCompanyName\", \"params\": [], \"id\": 1}";
    public static final String METHOD_SEARCH_FOR_CUSTOMER_AND_VENDORS = "{\"method\": \"Company.searchForCustomersAndVendors\",\"params\": [%s,%s,\"%s\"], \"id\": 1}";
    public static final String METHOD_LOGIN_EMPLOYEE_USERS = "{\"method\": \"Employee.getLoginEmployeeUsers\", \"params\": [], \"id\": 1}";
    public static final String METHOS_GET_CUSTOMERS_OPEN_RECORDS_BALANCE_OUT  = "{\"method\": \"AccountingReport.getCustomersOpenRecordsBalanceOut\", \"params\": [], \"id\": 1}";
    public static final String METHOS_GET_SUM_COMPANY_CHARGEABLE_HOURS = "{\"method\": \"ProjectReport.getSumCompanyChargeableHours\", \"params\": [\"%s\", \"%s\",%s], \"id\": 1}\"";
    public static final String METHOS_GET_COMPANY_CHARGEABLE_PERCENTAGE = "{\"method\": \"ProjectReport.getCompanyChargeablePercentage\", \"params\": [\"%s\", \"%s\",%s], \"id\": 0}\"";
    public static final String METHOS_GET_COMPANY_PROJECTS_FEE ="{\"method\": \"ProjectReport.getCompanyProjectsFee\", \"params\": [\"%s\", \"%s\",%s], \"id\": 1}\"";
    public static final String METHOS_GET_COMPANY_PROJECTS_NET_AMOUNT ="{\"method\": \"ProjectReport.getCompanyProjectsNetAmount\", \"params\": [\"%s\", \"%s\",%s], \"id\": 1}\"";
    public static final String METHOS_GET_SUM_EMPLOYEE_CHARGEABLE_HOURS ="{\"method\": \"ProjectReport.getSumEmployeeChargeableHours\", \"params\": [%s, \"%s\",\"%s\"], \"id\": 1}\"";
    public static final String METHOS_GET_SUM_EMPLOYEE_CHARGEABLE_PERCENTAGE ="{\"method\": \"ProjectReport.getEmployeeChargeablePercentage\", \"params\": [%s, \"%s\",\"%s\"], \"id\": 1}\"";
    public static final String METHOD_GET_DEPARTMENT_CHARGEABLE_PERCENTAGE = "{\"method\": \"ProjectReport.getDepartmentChargeablePercentage\", \"params\": [ %s,\"%s\", \"%s\",%s] }\"";

    private OkHttpClient client = null;

    public TripletexClient(){
        client = new OkHttpClient();
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client.setCookieHandler(cookieManager);

    }

    public JSONObject getRequestResponseAsJSon(String postBody)throws Exception{
        Response response = getRequest(postBody);
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        return new JSONObject(response.body().string());
    }

    public Response getRequest(String postBody) throws IOException{
        Request request = new Request.Builder()
                .url(serverUrl)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(MEDIA_TYPE_JSON, postBody))
                .build();

        return client.newCall(request).execute();
    }
}
