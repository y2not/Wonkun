module.exports = {
    cmpdb: {
        userName: 'opus_fms',
        clientSecret: 'fms',
        database: 'opusfms_ui_impex_500bak_0504_portal',
        server: 'localhost',
        refreshSecond: 1000 * 60,
        maxResult: 30,
        countryQuery: 'SELECT CNT_CD code, CNT_ENG_NM name FROM COM_COUNTRY;',
        subdivisionQuery: `SELECT DIV_SUB_CD code, DIV_SUB_NM name, CONCAT ('{"countryCd":"', trim(CNT_CD),'"}') addInfo FROM COM_SUB_DIVISION`,
        locationQuery: `SELECT CNT_LOC_CD code, LOC_NM name, CONCAT('{"countryCd":"', trim(CNT_CD) ,'","subDivCd":"', trim(DIV_SUB_CD),'"}') addInfo FROM COM_LOCATION;`,
        userQuery: `SELECT USER_ID code, USER_NM name, CONCAT('{"organizationId":"', trim(ORG_ID) ,'"}') addInfo FROM COM_USR_PRFL;`,
        organizationQuery: 'SELECT ORG_ID code, ORG_NM name FROM COM_ORG_MGT;',
        serviceQuery: 'SELECT SVCE_ID code, SVCE_NM name FROM SVC_SVCE;',
        subscriptionQuery: 'SELECT SBSCR_ID code, SBSCR_NM name FROM SVC_SBSCR',
        billingPolicyQuery: 'SELECT BILL_PLCY_ID code, BILL_PLCY_NM name FROM SVC_BILL_PLCY;',    
        noMoreItem: { status : 0, message : 'no more item'},
        nextItem: { status : 2, message : 'next items'},
        lagTerm : 1000
    }
};