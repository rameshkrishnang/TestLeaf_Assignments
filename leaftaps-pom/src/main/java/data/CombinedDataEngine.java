package data;

import java.util.List;

public class CombinedDataEngine {

    public static LeadInfo fetchData() {
        LeadInfo fakerLeadInfo = FakerDataEngine.getLeadInfo();
        LeadInfo redisLeadInfo = RedisDataEngine.getRandomLeadRedis();
        redisLeadInfo.setCompanyName(fakerLeadInfo.getCompanyName());
        //System.out.println(leadInfo.getLeadId());
        //leadInfo.setLeadId(JdbcDataRecord.getRandomLeadId());
        return redisLeadInfo;

    }

    public static LeadInfo fetchRetryData() {
        // Fetch retry-specific data logic
        LeadInfo fakerLeadInfo = FakerDataEngine.getLeadInfo();
        LeadInfo retryRedisLeadInfo = RedisDataEngine.getRetryLeadRedis(); // Use a retry-specific Redis query if available
        retryRedisLeadInfo.setCompanyName(fakerLeadInfo.getCompanyName() + " Retry");
        return retryRedisLeadInfo;
    }

    public static List<LeadInfo> fetchMultipleData() {
        return RedisDataEngine.getMultipleLeadsRedis(2);
    }

    public static List<LeadInfo> fetchMultipleRetryData() {
        // Fetch a different set of leads for retries
        return RedisDataEngine.getMultipleRetryLeadsRedis(3); // Fetch 3 leads as retry-specific logic
    }

}
