package no.tripletex;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

public class TripletexTest {

    private Tripletex  tripletex = null;

    private Logger log = Logger.getLogger(TripletexTest.class.getName());

    private LocalDate fromDate = LocalDate.now();
    private LocalDate toDate = LocalDate.now();

    private String syncSystem = null;
    private String syncPassword = null;
    private String userName = null;
    private String userPassword = null;
    private Integer employeeId = Integer.MIN_VALUE;

    @Before
    public void before() throws Exception {
        syncSystem = System.getProperty("syncSystem");
        syncPassword = System.getProperty("syncPassword");
        userName = System.getProperty("userName");
        userPassword = System.getProperty("userPassword");
        employeeId = Integer.parseInt( System.getProperty("employeeId") );

        fromDate = fromDate.withDayOfMonth(1);

        tripletex = new Tripletex();

        testLogin();
    }

    @After
    public void after() throws Exception {
       testLogout();
    }


    @Test
    public void testLogin() throws Exception {
        JSONObject result = tripletex.login(syncSystem, syncPassword, userName, userPassword);

        Assert.assertNotNull(result);
        log.info("Login: " + result.toString(4) );
    }


    @Test
    public void testgetLoginCompanyName() throws Exception {
        JSONObject result = tripletex.getLoginCompanyName();

        Assert.assertNotNull(result);
        log.info("GetLoginCompanyName: " + result.toString(4) );
    }


    @Test
    public void testSearchForCustomersAndVendors() throws Exception {
        JSONObject result = tripletex.searchForCustomersAndVendors(0,-1,"");

        Assert.assertNotNull(result);

        log.info("SearchForCustomersAndVendors: " + result.toString(4) );
    }

    @Test
    public void testSearchForCustomersAndVendorsByName() throws Exception {
        JSONObject result = tripletex.searchForCustomersAndVendors(0,-1,"Ak-");

        Assert.assertNotNull(result);

        log.info("SearchForCustomersAndVendors: " + result.toString(4) );
    }


    @Test
    public void testGetCustomersOpenRecordsBalanceOut()throws Exception {
        JSONObject result = tripletex.getCustomersOpenRecordsBalanceOut();

        Assert.assertNotNull(result);

        log.info("GetCustomersOpenRecordsBalanceOut: " + result.toString(4) );
    }


    @Test
    public void testGetSumCompanyChargeableHours ( )throws Exception {
        JSONObject result = tripletex.getSumCompanyChargeableHours(fromDate, toDate, true );

        Assert.assertNotNull(result);

        log.info("GetSumCompanyChargeableHours: " + result.toString(4) );
    }

    @Test
    public void testGetCompanyChargeablePercentage  ()throws Exception {
        JSONObject result = tripletex.getCompanyChargeablePercentage(fromDate, toDate, true );

        Assert.assertNotNull(result);

        log.info("GetCompanyChargeablePercentage: " + result.toString(4) );
    }


    @Test
    public void testGetCompanyProjectsFee  ()throws Exception {
        JSONObject result = tripletex.getCompanyProjectsFee(fromDate, toDate, true);

        Assert.assertNotNull(result);

        log.info("GetCompanyProjectsFee: " + result.toString(4) );
    }


    @Test
    public void testGetCompanyProjectsNetAmount  ()throws Exception {
        JSONObject result = tripletex.getCompanyProjectsNetAmount(fromDate, toDate, true);

        Assert.assertNotNull(result);

        log.info("getCompanyProjectsNetAmount: " + result.toString(4) );
    }

    @Test
    public void testGetSumEmployeeChargeableHours()throws Exception {
        JSONObject result = tripletex.getSumEmployeeChargeableHours(employeeId, fromDate, toDate);

        Assert.assertNotNull(result);

        log.info("GetSumEmployeeChargeableHours: " + result.toString(4) );
    }

    @Test
    public void testGetEmployeeChargeablePercentage()throws Exception {
        JSONObject result = tripletex.getEmployeeChargeablePercentage( employeeId , fromDate, toDate);

        Assert.assertNotNull(result);

        log.info("GetEmployeeChargeablePercentage: " + result.toString(4) );
    }

    @Test
    public void testGetCompanyProjectsFeeByDates()throws Exception {
        LocalDate startDate = LocalDate.now();
        startDate = startDate.withDayOfMonth(1);

        LocalDate endDate = LocalDate.now();
        endDate = endDate.plusDays(1);

        JSONObject result = tripletex.getCompanyProjectsFeeByDate( startDate,endDate );

        log.info("GetCompanyProjectsFee - JSON: " +  result.toString(4) );

    }

    @Test
    public void testGetCompanyProjectsFeeByDate()throws Exception {
        LocalDate date = LocalDate.now();

        JSONObject result = tripletex.getCompanyProjectsFeeByDate(date);

        log.info("GetCompanyProjectsFeeByDate - JSON: " +  result.toString(4) );

    }

    @Test
    public void testGetDepartmentChargeablePercentage  ()throws Exception {
        JSONObject result = tripletex.getDepartmentChargeablePercentage(1,fromDate, toDate, true );

        Assert.assertNotNull(result);

        log.info("GetDepartmentChargeablePercentage: " + result.toString(4) );
    }


    @Test
    public void testLogout() throws Exception {
        JSONObject result = tripletex.logout();

        Assert.assertNotNull(result);
        log.info("Logout: " + result.toString(4) );
    }
}
