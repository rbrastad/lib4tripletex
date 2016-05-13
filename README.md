# Tripletex API

Tripletex is an Norwegian Company that deliver a economy system in the cloud. This library leverages some of the available APIs from that system. Read more here: https://www.tripletex.no/vart-system/partnere-og-integrasjoner/

## Compatibility

 Enonic
 --------
  >6.4.1

## Including in project

To use this library in your Enonic XP-project, you need a add the rbrastad maven repository from Bintray, and maven dependency.

### Using the last released build

A release build is final, and the code cannot change in a given version number. Make sure that a production-ready project always uses the last released build (and not snapshot).

Update your `build.gradle` file with:

```gradle
dependencies {
    include 'no.rbrastad.xp:lib4tripletex:0.1.0'
}

repositories {
    maven {
        url 'https://dl.bintray.com/rbrastad/XP'
    }
}

You can visit my Bintray repo from Enonic XP here: https://bintray.com/rbrastad/XP

```

## Usage

The API is developed in JAVA. But you can use it in an Javascript controller.

```
var portalLib = require('/lib/xp/portal');

var tripleTexBean =  __.newBean("no.tripletex.Tripletex");
var tripleTexLoggedIn = false;

var syncSystem = "<SYNC KEY>"
var syncPassword = "<SYNC PASSWORD>"
var userName = "<USERNAME>"
var userPassword = "<PASSWORD>"


exports.login = function() {
    return tripleTexBean.login(syncSystem,syncPassword, userName, userPassword);
};

exports.logout = function() {
    tripleTexLoggedIn = false;

    return tripleTexBean.logout();
};

exports.tripleTexBean = function() {

    if( !tripleTexLoggedIn ) {
        exports.login();
        tripleTexLoggedIn = true;
    }

    return tripleTexBean;
};


exports.getLoginCompanyName = function() {
    return exports.tripleTexBean().getLoginCompanyName();
};

exports.getLoginEmployeeUsers = function() {
    return exports.tripleTexBean().getLoginEmployeeUsers();
};

exports.searchForCustomersAndVendors = function(customerVendorType, isActive , searchString ) {
    return exports.tripleTexBean().searchForCustomersAndVendors( customerVendorType , isActive, searchString);
};

exports.getSumCompanyChargeableHours = function(fromDate , toDate ,includeContacts ) {
    return exports.tripleTexBean().getSumCompanyChargeableHours( fromDate , toDate ,includeContacts );
};

exports.getCompanyProjectsFee = function(fromDate , toDate ,includeContacts ) {
    return exports.tripleTexBean().getCompanyProjectsFee( fromDate , toDate ,includeContacts );
};

exports.getCompanyProjectsFee = function(fromDate , toDate ,includeContacts ) {
    return exports.tripleTexBean().getCompanyProjectsFee( fromDate , toDate ,includeContacts );
};


exports.getCompanyChargeablePercentage = function(fromDate , toDate ,includeContacts ) {
    return exports.tripleTexBean().getCompanyChargeablePercentage( fromDate , toDate ,includeContacts );
};

exports.getCompanyProjectsFeeByDate = function(fromDate , toDate ) {
    return exports.tripleTexBean().getCompanyProjectsFeeByDate( fromDate , toDate );
};

```

### Development testing

To develop and test the API you need to add some properties to <USER_HOME>/.gradle/gradle.properties

```
#TripleTex API Properties
tripleTexSyncSystem= <SYNCSYSTEM KEY>
tripleTexSyncPassword=<PASSOWRD>
tripleTexUserName=<USERNAME>
tripleTexUserPassword=<PASSWORD>
tripleTexEmployeeId=<EMPLOYEEID TO TEST ON>
```

This is done so you don't expose the secret keys to anybody else.