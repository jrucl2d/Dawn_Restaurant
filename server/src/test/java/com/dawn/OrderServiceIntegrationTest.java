package com.dawn;

import com.dawn.dto.OrderDTO;
import com.dawn.model.Order;
import com.dawn.model.OrderStatus;
import com.dawn.model.Store;
import com.dawn.model.User;
import com.dawn.repository.order.OrderRepository;
import com.dawn.repository.store.StoreRepository;
import com.dawn.repository.user.UserRepository;
import com.dawn.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class OrderServiceIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Test
    public void PASS_OrderStatus업데이트() {
        User user = new User("loginId", "1234", "jun", true);
        user = userRepository.save(user);
        Store store = new Store("새벽식당", "신공", "24시", "새벽에만 하는 식당", user);
        store = storeRepository.save(store);
        Order order = new Order(10000, store);
        order = orderRepository.save(order);
        orderService.updateStateOfOrder(
                new OrderDTO.OrderStateUpdate(order.getOrderId(), OrderStatus.COOKING));
        order = orderRepository.save(order);
        assertThat(order.getOrderStatus().getStatusCode(), is(OrderStatus.COOKING.getStatusCode()));
    }
}
