package com.evan.blog.service.impls;

import com.evan.blog.pojo.IPLocation;
import com.evan.blog.service.IPQueryService;
import com.evan.blog.util.IPUtil;
import com.evan.blog.util.JsonUtil;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service(value = "IPQueryService")
public class IPQueryServiceImpl implements IPQueryService {
    @Autowired
    RedisOperator redisOperator;

    @Override
    public IPLocation query(String ip) {
        double ipDouble = new Long(IPUtil.IPv4ToLong(ip)).doubleValue();
        Set<ZSetOperations.TypedTuple<String>> tupleSet =
                redisOperator.zrevrangebyscore("ip2cityid:",0, ipDouble,0, 1);
        String cityId = null;
        if (!tupleSet.isEmpty()) {
            for (ZSetOperations.TypedTuple<String> tuple: tupleSet) {
                try {
                    cityId = tuple.getValue();
                } catch (NumberFormatException e) {
                    // nothing to do
                }

            }
        }

        if (cityId != null) {
            cityId = cityId.split("_")[0];
        } else {
            return new IPLocation("unkown", "unkown", "unkown");
        }
        // location is a json string
        String location = redisOperator.hget("cityid2city:", cityId);
        //could catch NullPointerException?
        List<String> locationList = JsonUtil.jsonToList(location, String.class);
        IPLocation ipLocation = new IPLocation(locationList.get(0), locationList.get(1), locationList.get(2));

        return ipLocation;

    }

}
