package data;

import com.testleaf.web.browser.BrowserCookie;
import com.testleaf.web.browser.SeBrowserCookieImpl;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.logging.Logger;

public class RedisDataEngine {

    // make a connection
    public static Jedis jedis = new Jedis("localhost", 6379);
    private static final Logger logger = Logger.getLogger(RedisDataEngine.class.getName());

    /**
     * Push the record into redis keys (table)
     * {@link LeadInfo} - The class that has all data information about the Lead
     * Key : LeadId is the unique parameter (Hset) >> O(1)
     */
    public static void saveLeadRedis(LeadInfo leadInfo) {

        // Get the lead id as key
        String leadId = "Lead: " + leadInfo.getLeadId();

        jedis.hset(leadId, "companyName", leadInfo.getCompanyName());
        jedis.hset(leadId, "firstName", leadInfo.getFirstName());
        jedis.hset(leadId, "lastName", leadInfo.getLastName());
        jedis.hset(leadId, "phoneNumber", leadInfo.getPhoneNumber());

        logger.info("The lead " + leadId + " is stored in the redis");

    }

    /**
     * Fetch the lead records (key info) from Redis - only one !!
     * Top 1 or Random one ?
     */
    public static LeadInfo getRandomLeadRedis() {
        String leadId = null;

        // Get all the keys
        Set<String> leadKeys = jedis.keys("Lead:*");

        // Get first lead and get all lead info from redis
        if (!leadKeys.isEmpty()) {

            // Fetch the first one
            leadId = leadKeys.iterator().next();

        } else {
            throw new RuntimeException("There are no leads matching in the redis");
        }

        // LeadInfo
        LeadInfo leadInfo = new LeadInfo();
        leadInfo.setLeadId(leadId.replaceAll("Lead: ", ""));
        leadInfo.setCompanyName(jedis.hget(leadId, "companyName"));
        leadInfo.setFirstName(jedis.hget(leadId, "firstName"));
        leadInfo.setLastName(jedis.hget(leadId, "lastName"));
        leadInfo.setPhoneNumber(jedis.hget(leadId, "phoneNumber"));

        return leadInfo;

    }

    public static LeadInfo getRetryLeadRedis() {
        String leadId = null;

        // Get all the keys for retry-specific leads
        Set<String> retryLeadKeys = jedis.keys("Retry:Lead:*");

        // Check if there are any retry-specific leads
        if (!retryLeadKeys.isEmpty()) {
            // Fetch the first retry-specific lead
            leadId = retryLeadKeys.iterator().next();
        } else {
            throw new RuntimeException("No retry-specific leads found in Redis.");
        }

        // Create and populate the LeadInfo object
        LeadInfo leadInfo = new LeadInfo();
        leadInfo.setLeadId(leadId.replaceAll("Retry:Lead: ", ""));
        leadInfo.setCompanyName(jedis.hget(leadId, "companyName"));
        leadInfo.setFirstName(jedis.hget(leadId, "firstName"));
        leadInfo.setLastName(jedis.hget(leadId, "lastName"));
        leadInfo.setPhoneNumber(jedis.hget(leadId, "phoneNumber"));

        return leadInfo;
    }

    /**
     * Delete an existing record from redis
     */
    public static void deleteLeadRedis(String leadId) {
        String deleteLeadId = "Lead: " + leadId;
        if (jedis.exists(deleteLeadId)) {
            jedis.del(deleteLeadId);
            logger.info("The lead " + leadId + " is removed from redis");
        }
    }

    /**
     * Fetch multiple lead records (key info) from Redis.
     *
     * @param count The number of leads to fetch.
     * @return A list of LeadInfo objects.
     */
    public static List<LeadInfo> getMultipleLeadsRedis(int count) {
        Set<String> leadKeys = jedis.keys("Lead:*");
        List<LeadInfo> leadInfos = new ArrayList<>();

        if (leadKeys.isEmpty()) {
            throw new RuntimeException("There are no leads matching in Redis");
        }

        Iterator<String> iterator = leadKeys.iterator();
        int fetched = 0;

        while (iterator.hasNext() && fetched < count) {
            String leadId = iterator.next();

            LeadInfo leadInfo = new LeadInfo();
            leadInfo.setLeadId(leadId.replaceAll("Lead: ", ""));
            leadInfo.setCompanyName(jedis.hget(leadId, "companyName"));
            leadInfo.setFirstName(jedis.hget(leadId, "firstName"));
            leadInfo.setLastName(jedis.hget(leadId, "lastName"));
            leadInfo.setPhoneNumber(jedis.hget(leadId, "phoneNumber"));

            leadInfos.add(leadInfo);
            fetched++;
        }

        return leadInfos;
    }

    public static List<LeadInfo> getMultipleRetryLeadsRedis(int count) {
        Set<String> retryLeadKeys = jedis.keys("Retry:Lead:*");
        List<LeadInfo> retryLeads = new ArrayList<>();

        if (retryLeadKeys.isEmpty()) {
            throw new RuntimeException("No retry-specific leads found in Redis.");
        }

        Iterator<String> iterator = retryLeadKeys.iterator();
        int fetched = 0;

        // Fetch the specified number of retry-specific leads
        while (iterator.hasNext() && fetched < count) {
            String leadId = iterator.next();

            LeadInfo leadInfo = new LeadInfo();
            leadInfo.setLeadId(leadId.replaceAll("Retry:Lead: ", ""));
            leadInfo.setCompanyName(jedis.hget(leadId, "companyName"));
            leadInfo.setFirstName(jedis.hget(leadId, "firstName"));
            leadInfo.setLastName(jedis.hget(leadId, "lastName"));
            leadInfo.setPhoneNumber(jedis.hget(leadId, "phoneNumber"));

            retryLeads.add(leadInfo);
            fetched++;
        }

        return retryLeads;
    }


    /**
     * Save cookies to Redis
     *
     * @param cookies Set of Selenium cookies to store
     */
    public static void saveCookiesToRedis(Set<BrowserCookie> cookies) {
        for (BrowserCookie cookie : cookies) {
            // Get the cookie name and other details
            String cookieKey = cookie.getName();

            // Get the expiry time and convert it to a string if it's valid, otherwise store null.
            String expiryTime = cookie.getExpiry() > 0 ? String.valueOf(cookie.getExpiry()) : "null";

            // Format the cookie value string
            String cookieValue = String.format("%s;%s;%s;%s;%b", cookie.getValue(), cookie.getDomain(), cookie.getPath(), expiryTime, // use the expiryTime string
                    cookie.isSecure());

            // Save the cookie details to Redis using the cookie name as the key
            jedis.hset("Browser:Cookies", cookieKey, cookieValue);
        }
        logger.info("Cookies saved to Redis.");
    }

    /**
     * Load cookies from Redis
     *
     * @return Set of Selenium cookies
     */
    public static Set<BrowserCookie> loadCookiesFromRedis() {
        Set<String> cookieKeys = jedis.hkeys("Browser:Cookies");
        Set<BrowserCookie> cookies = new HashSet<>();

        for (String key : cookieKeys) {
            String[] cookieDetails = jedis.hget("Browser:Cookies", key).split(";");

            // Reconstruct the cookie using the details from Redis
            long expiryTime = "null".equals(cookieDetails[3]) ? -1 : Long.parseLong(cookieDetails[3]);

            BrowserCookie cookie = new SeBrowserCookieImpl(key, // Cookie name
                    cookieDetails[0], // Cookie value
                    cookieDetails[1], // Domain
                    cookieDetails[2], // Path
                    Boolean.parseBoolean(cookieDetails[4]), // isSecure
                    false, // Assuming HttpOnly is not saved; you can adjust this if needed
                    expiryTime // Expiry time (null is represented as -1)
            );
            cookies.add(cookie);
        }

        logger.info("Cookies loaded from Redis.");
        return cookies;
    }


    /**
     * Clear cookies from Redis
     */
    public static void clearCookiesFromRedis() {
        jedis.del("Browser:Cookies");
        logger.info("Cookies cleared from Redis.");
    }
}
