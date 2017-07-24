package com.books.share.smartbookshelf.lib.conf;

/**
 * Created by limjuhyun on 24/07/2017.
 */
public class Conf {
    /**
     * Is release?
     */
    public static final boolean ISRELEASE	= false;

    /**
     * The type of config
     */
    public static final boolean ISCONFIG	= ISRELEASE ? true : false;


    /**
     * The config of dev server
     */
    private class TestConfig {
        private static final String SITE_DOMAIN_URL 		= "http://smartbookshelf.teamnexters.com";

    }

    /**
     * The config of real server
     */
    private class RealConfig {
        private static final String SITE_DOMAIN_URL 		= "http://smartbookshelf.teamnexters.com";
    }

    /**
     * Server Url
     */
    public static final String SITE_DOMAIN_URL = ISCONFIG ? RealConfig.SITE_DOMAIN_URL : TestConfig.SITE_DOMAIN_URL;

}
