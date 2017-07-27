package com.books.share.smartbookshelf.lib.conf;

/**
 * Created by limjuhyun on 24/07/2017.
 */
public class Conf {
    /**
     * Is release?
     */
    public static final boolean ISRELEASE = false;

    /**
     * The type of config
     */
    public static final boolean ISCONFIG = ISRELEASE ? true : false;


    /**
     * The config of dev server
     */
    private class TestConfig {
        private static final String SITE_DOMAIN_URL = "http://smartbookshelf.teamnexters.com";
        private static final String APPLICATION_ID = "com.books.share.smartbookshelf";
        private static final String API_LOGIN_URL = "http://smartbookshelf.teamnexters.com/o/authorize/";
        private static final String API_OAUTH_CLIENTID = "HCte2sGHTu7U5KdvILK8NSa6DHwuCFlxlL6aPbpf";
        private static final String API_OAUTH_CLIENTSECRET = "Uy2hsBjbmovNo0piMveuxfZ0vAAP9LSb1DgFNw2hxlPTXEudXBrT6YGB94HD394zHE2qG9csrPZgahIVspfyeQvLrmogWdcapr4bLPngg48VHD09bIWYywSGIpGFJyAz";
        private static final String API_OAUTH_REDIRECT = "com.books.share.smartbookshelf://oauth";

    }

    /**
     * The config of real server
     */
    private class RealConfig {
        private static final String SITE_DOMAIN_URL = "http://smartbookshelf.teamnexters.com";
        private static final String APPLICATION_ID = "com.books.share.smartbookshelf";
        private static final String API_LOGIN_URL = "http://smartbookshelf.teamnexters.com/o/authorize/";
        private static final String API_OAUTH_CLIENTID = "HCte2sGHTu7U5KdvILK8NSa6DHwuCFlxlL6aPbpf";
        private static final String API_OAUTH_CLIENTSECRET = "Uy2hsBjbmovNo0piMveuxfZ0vAAP9LSb1DgFNw2hxlPTXEudXBrT6YGB94HD394zHE2qG9csrPZgahIVspfyeQvLrmogWdcapr4bLPngg48VHD09bIWYywSGIpGFJyAz";
        private static final String API_OAUTH_REDIRECT = "com.books.share.smartbookshelf://oauth";
    }

    /**
     * Server Url
     */
    public static final String SITE_DOMAIN_URL = ISCONFIG ? RealConfig.SITE_DOMAIN_URL : TestConfig.SITE_DOMAIN_URL;
    public static final String APPLICATION_ID = ISCONFIG ? RealConfig.APPLICATION_ID : TestConfig.APPLICATION_ID;
    public static final String API_LOGIN_URL = ISCONFIG ? RealConfig.API_LOGIN_URL : TestConfig.API_LOGIN_URL;
    public static final String API_OAUTH_CLIENTID = ISCONFIG ? RealConfig.API_OAUTH_CLIENTID : TestConfig.API_OAUTH_CLIENTID;
    public static final String API_OAUTH_CLIENTSECRET = ISCONFIG ? RealConfig.API_OAUTH_CLIENTSECRET : TestConfig.API_OAUTH_CLIENTSECRET;
    public static final String API_OAUTH_REDIRECT = ISCONFIG ? RealConfig.API_OAUTH_REDIRECT : TestConfig.API_OAUTH_REDIRECT;

}
