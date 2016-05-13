package no.tripletex;


import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;

/**
 * Created by runar on 15.02.16.
 */
public class Tripletex extends TripletexClient {

    private static final String tripletexDatePattern = "yyyy-MM-dd";

    public JSONObject login( String syncSystem, String syncPassword ,String userName, String userPassword ) throws Exception{
       return getRequestResponseAsJSon( String.format(METHOD_LOGIN, syncSystem, syncPassword, userName, userPassword) );
    }

    public JSONObject logout() throws Exception{
        return getRequestResponseAsJSon( METHOD_LOGOUT );
    }
    public JSONObject getLoginCompanyName() throws Exception{
        return getRequestResponseAsJSon( METHOD_LOGIN_COMPANY_NAME );
    }

    public JSONObject searchForCustomersAndVendors(int customerVendorType, int isActive, String searchString )throws  Exception{
        return getRequestResponseAsJSon( String.format(METHOD_SEARCH_FOR_CUSTOMER_AND_VENDORS, customerVendorType,isActive, searchString ) );
    }

    public JSONObject getLoginEmployeeUsers() throws Exception{
        return getRequestResponseAsJSon( METHOD_LOGIN_EMPLOYEE_USERS );
    }


    public JSONObject getCustomersOpenRecordsBalanceOut() throws Exception{
        return getRequestResponseAsJSon( METHOS_GET_CUSTOMERS_OPEN_RECORDS_BALANCE_OUT );
    }


    public JSONObject getSumCompanyChargeableHours(LocalDate dateFrom, LocalDate dateTo, boolean includeContacts) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_SUM_COMPANY_CHARGEABLE_HOURS, DateUtil.formatDate(dateFrom,tripletexDatePattern), DateUtil.formatDate(dateTo,tripletexDatePattern), includeContacts ) );
    }

    public JSONObject getSumCompanyChargeableHours(String dateFrom, String dateTo, boolean includeContacts) throws Exception{
       return getRequestResponseAsJSon(  String.format(METHOS_GET_SUM_COMPANY_CHARGEABLE_HOURS, dateFrom,dateTo, includeContacts ) );
    }

    public JSONObject getCompanyChargeablePercentage(LocalDate dateFrom, LocalDate dateTo, boolean includeContacts) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_COMPANY_CHARGEABLE_PERCENTAGE, DateUtil.formatDate(dateFrom,tripletexDatePattern), DateUtil.formatDate(dateTo,tripletexDatePattern), includeContacts ) );
    }

    public JSONObject getCompanyChargeablePercentage(String dateFrom, String dateTo, boolean includeContacts) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_COMPANY_CHARGEABLE_PERCENTAGE, dateFrom, dateTo, includeContacts ) );
    }


    public JSONObject getCompanyProjectsFee(LocalDate dateFrom, LocalDate dateTo, boolean includeContacts) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_COMPANY_PROJECTS_FEE, DateUtil.formatDate(dateFrom,tripletexDatePattern), DateUtil.formatDate(dateTo,tripletexDatePattern), includeContacts ) );
    }

    public JSONObject getCompanyProjectsFee(String dateFrom, String dateTo, boolean includeContacts) throws Exception{
        return  getRequestResponseAsJSon(  String.format(METHOS_GET_COMPANY_PROJECTS_FEE, dateFrom,dateTo, includeContacts ) );
    }

    public JSONObject getCompanyProjectsNetAmount(LocalDate dateFrom, LocalDate dateTo, boolean includeContacts) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_COMPANY_PROJECTS_NET_AMOUNT, DateUtil.formatDate(dateFrom,tripletexDatePattern), DateUtil.formatDate(dateTo,tripletexDatePattern), includeContacts ) );
    }


    public JSONObject getCompanyProjectsNetAmount(String dateFrom, String dateTo, boolean includeContacts) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_COMPANY_PROJECTS_NET_AMOUNT, dateFrom, dateTo, includeContacts ) );
    }


    public JSONObject getSumEmployeeChargeableHours(Integer employeeId, LocalDate dateFrom, LocalDate dateTo) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_SUM_EMPLOYEE_CHARGEABLE_HOURS, employeeId, DateUtil.formatDate(dateFrom,tripletexDatePattern), DateUtil.formatDate(dateTo,tripletexDatePattern) ) );
    }

    public JSONObject getSumEmployeeChargeableHours(Integer employeeId, String dateFrom, String dateTo) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_SUM_EMPLOYEE_CHARGEABLE_HOURS, employeeId, dateFrom, dateTo ) );
    }

    public JSONObject getEmployeeChargeablePercentage(Integer employeeId, LocalDate dateFrom, LocalDate dateTo) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_SUM_EMPLOYEE_CHARGEABLE_PERCENTAGE, employeeId, DateUtil.formatDate(dateFrom,tripletexDatePattern), DateUtil.formatDate(dateTo,tripletexDatePattern) ) );
    }

    public JSONObject getEmployeeChargeablePercentage(Integer employeeId, String dateFrom, String dateTo) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOS_GET_SUM_EMPLOYEE_CHARGEABLE_PERCENTAGE, employeeId,dateFrom, dateTo ) );
    }

    public JSONObject getDepartmentChargeablePercentage(int departmentId, LocalDate dateFrom, LocalDate dateTo, boolean includeContacts) throws Exception{
        return getRequestResponseAsJSon(  String.format(METHOD_GET_DEPARTMENT_CHARGEABLE_PERCENTAGE, departmentId , DateUtil.formatDate(dateFrom,tripletexDatePattern), DateUtil.formatDate(dateTo,tripletexDatePattern), includeContacts ) );
    }


    public JSONObject getCompanyProjectsFeeByDate(LocalDate fromDate ,LocalDate toDate)throws Exception{
        JSONObject totalJson = new JSONObject();
        totalJson.put("fromDate", fromDate);
        totalJson.put("toDate", toDate);

        JSONArray jsonArrayResult = new JSONArray();
        Double sum = new Double("0");
        while( fromDate.isBefore( toDate ) ) {
            JSONObject result = this.getCompanyProjectsFee(fromDate, fromDate.plusDays(1), true);

            sum += result.getDouble("result");

            result.put("date", fromDate);

            jsonArrayResult.put(result);
            fromDate = fromDate.plusDays(1);
        }

        totalJson.put("sum", sum);

        return new JSONObject()
                .put("companyProjectsFeeByDate", jsonArrayResult)
                .put("companyProjectsFeeByDateSum", totalJson);
    }

    public JSONObject getCompanyProjectsFeeByDate(String fromDate ,String toDate)throws Exception{
        return getCompanyProjectsFeeByDate(
                DateUtil.parseToDate(fromDate, tripletexDatePattern),
                DateUtil.parseToDate(toDate, tripletexDatePattern)
        );

    }

    public JSONObject getCompanyProjectsFeeByDate(LocalDate date)throws Exception{
        return getCompanyProjectsFeeByDate( date, date.plusDays(1) );
    }

    public JSONObject getCompanyProjectsFeeByDate(String date)throws Exception{
        LocalDate localDate = DateUtil.parseToDate(date, tripletexDatePattern);

        return getCompanyProjectsFeeByDate( localDate, localDate.plusDays(1) );
    }

}