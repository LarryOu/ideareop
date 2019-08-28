package cn.itcast.test;

import cn.itcast.springboot.MySpringBootApplication;
import cn.itcast.springboot.dao.UserDao;
import cn.itcast.springboot.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringBootApplication.class)
public class SpringBootRedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserDao userDao;

    @Test
    public void test() throws Exception {
        String user = redisTemplate.boundValueOps("user").get();
        if (user == null || "".equals(user)) {
            List<User> list = userDao.findAll();
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.writeValueAsString(list);
            redisTemplate.boundValueOps("user").set(user);
            System.out.println("数据库获取");
        } else {
            System.out.println("缓存中获取");
        }
        System.out.println(user);
    }

}
