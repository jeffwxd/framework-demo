import com.wxd.SpringbootApplication;
import com.wxd.bo.OrderInfo;
import com.wxd.dao.OrderDao;
import com.wxd.page.PageRequestParam;
import com.wxd.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/***
 *
 * @author xiongchuang
 * @date 2018-01-15
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class TestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderDao orderDao;


    @Autowired
    private OrderService orderService;
    @Test
    public void testFindOne(){
        logger.info("开始测试.........");
        OrderInfo info = orderService.findById(1L);
        System.out.println(info);
    }
    @Test
    public void testPageOrder(){
        List<OrderInfo> orderInfos = orderService.pageOrder();
        if(CollectionUtils.isEmpty(orderInfos)){
            logger.info("内容为空");
        }
        for (OrderInfo orderInfo : orderInfos) {
            System.out.println(orderInfo);
        }
    }
    @Test
    public void testPageParams(){
        PageRequestParam pageRequestParam = PageRequestParam.of(0, 2);
        pageRequestParam.put("shopId",pageRequestParam);
        List<OrderInfo> orderInfos = orderDao.pageOrderParam(pageRequestParam);
        for (OrderInfo orderInfo : orderInfos) {
            System.out.println(orderInfo);
        }
    }
}
