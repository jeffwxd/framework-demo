import com.alibaba.fastjson.JSON;
import com.wxd.RedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 10:43 2019/4/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final String SCORE_RANK = "score_rank";

    @Test
    public void test()  {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
    }

    /**
     * 批量新增
     */
    @Test
    public void batchAdd() {
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("张三" + i, 1D + i);
            tuples.add(tuple);
        }
        System.out.println("循环时间:" +( System.currentTimeMillis() - start));
        Long num = redisTemplate.opsForZSet().add(SCORE_RANK, tuples);
        System.out.println("批量新增时间:" +(System.currentTimeMillis() - start));
        System.out.println("受影响行数：" + num);
    }
    /**
     * 获取排行列表
     */
    @Test
    public void list() {

        Set<String> range = redisTemplate.opsForZSet().reverseRange(SCORE_RANK, 0, 10);
        System.out.println("获取到的排行列表:" + JSON.toJSONString(range));
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().reverseRangeWithScores(SCORE_RANK, 0, 10);
        System.out.println("获取到的排行和分数列表:" + JSON.toJSONString(rangeWithScores));
    }
    @Test
    public void test01(){
        Set<String> range = redisTemplate.opsForZSet().reverseRange(SCORE_RANK, 0, 10);
        System.out.println("获取到的排行列表:" + JSON.toJSONString(range));
    }

    public static void main(String[] args) {
        List<RedisTest> list = null;
        if(CollectionUtils.isEmpty(list)){
            System.out.println("1111111");
        }
        System.out.println(list);
    }
}
