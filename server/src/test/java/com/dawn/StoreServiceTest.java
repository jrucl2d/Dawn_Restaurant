package com.dawn;

import com.dawn.dto.*;
import com.dawn.exception.DawnException;
import com.dawn.model.*;
import com.dawn.repository.menu.MenuRepository;
import com.dawn.repository.menuorder.MenuOrderRepository;
import com.dawn.repository.order.DawnOrderRepository;
import com.dawn.repository.store.StoreRepository;
import com.dawn.repository.user.UserRepository;
import com.dawn.service.StoreService;
import com.dawn.service.StoreServiceImpl;
import com.google.cloud.storage.Storage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class StoreServiceTest {

    @Mock
    public StoreRepository storeRepository;

    @Mock
    public UserRepository userRepository;

    @Mock
    private MenuOrderRepository menuOrderRepository;

    @Mock
    private DawnOrderRepository orderRepository;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private Storage storage;

    public StoreService storeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        storeService = new StoreServiceImpl(
                userRepository, storeRepository, menuOrderRepository, orderRepository, menuRepository);
    }

    @Test
    public void PASS_storeCreate() throws IOException {
        given(userRepository.findUserByUserId(1))
                .willReturn(new User("loginId", "1234", "jun", true));
        when(storeRepository.save(any(Store.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        StoreDTO.CreateStore newStore = new StoreDTO.CreateStore(
                1, "새벽식당", "신공", "24시", "새벽에만 하는 식당", null);
        Store store = storeService.createStore(newStore, null);
        assertThat(store, is(notNullValue()));
    }

    @Test
    public void PASS_storeAddMenu() {
        User user = new User("loginId", "1234", "jun", true);
        Store store = new Store("새벽식당", "신공", "24시", "새벽에만 하는 식당", "", user);
        List<MenuDTO.CreateMenu> menuDTOs =
                new ArrayList<>(Arrays.asList(new MenuDTO.CreateMenu(1, "햄버거", "맥도날드..", 6000),
                        new MenuDTO.CreateMenu(1, "샌드위치", "서브웨이..", 4000)));
    }

    @Test
    public void PASS_submitNewOrder() throws DawnException {
        List<MenuOrderDTO.Create> menuOrders = new ArrayList<>();
        int orderId = 100;
        int storeId = 101;
        Store mockStore = new Store(storeId);
        User mockUser = new User("4whomtbts", "1234", "손님", false);
        mockUser.setUserId(1);
        DawnOrder order = new DawnOrder(orderId, 0, mockStore, mockUser);
        given(menuRepository.findByMenuId(1)).willReturn(new Menu("햄버거", "맛있는", 6000, "", new Store(1)));
        given(menuRepository.findByMenuId(2)).willReturn(new Menu("서브웨이", "샌드위치", 5000, "", new Store(1)));
        given(userRepository.findUserByUserId(mockUser.getUserId())).willReturn(mockUser);
        when(menuOrderRepository.saveAll(any())).thenAnswer((Answer<List<MenuOrder>>) invocation -> {
            Object[] args = invocation.getArguments();
            return (List<MenuOrder>) args[0];
        });
        when(orderRepository.save(any(DawnOrder.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        MenuOrderDTO.Create menuOrder1 = new MenuOrderDTO.Create(1, 2);
        MenuOrderDTO.Create menuOrder2 = new MenuOrderDTO.Create(2, 5);
        menuOrders.addAll(Arrays.asList(menuOrder1, menuOrder2));
        OrderDTO.CreateOrder newOrder = new OrderDTO.CreateOrder(1, mockUser.getUserId(), menuOrders);
        OrderDTO.GetOrder result = storeService.submitNewOrder(newOrder);
        assertThat(result.getTotalPrice(), is(2 * 6000 + 5 * 5000));
    }

    @Test
    public void SUCESS_submitNewOrder_WHEN_잔액과결제금액이_같을때() throws DawnException {
        List<MenuOrderDTO.Create> menuOrders = new ArrayList<>();
        int orderId = 100;
        int storeId = 101;
        Store mockStore = new Store(storeId);
        User mockUser = new User("4whomtbts", "1234", "손님", false, 100000);
        mockUser.setUserId(1);
        DawnOrder order = new DawnOrder(orderId, 0, mockStore, mockUser);
        given(menuRepository.findByMenuId(1)).willReturn(new Menu("햄버거", "맛있는", 95000, "", new Store(1)));
        given(menuRepository.findByMenuId(2)).willReturn(new Menu("서브웨이", "샌드위치", 5000, "", new Store(1)));
        given(userRepository.findUserByUserId(mockUser.getUserId())).willReturn(mockUser);
        when(orderRepository.save(any(DawnOrder.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        MenuOrderDTO.Create menuOrder1 = new MenuOrderDTO.Create(1, 1);
        MenuOrderDTO.Create menuOrder2 = new MenuOrderDTO.Create(2, 1);
        menuOrders.addAll(Arrays.asList(menuOrder1, menuOrder2));
        OrderDTO.CreateOrder newOrder = new OrderDTO.CreateOrder(1, mockUser.getUserId(), menuOrders);
        storeService.submitNewOrder(newOrder);
    }

    @Test(expected = DawnException.class)
    public void FAIL_submitNewOrder_WHEN_존재하지않는_메뉴ID로_주문을_넣었을때() throws DawnException {
        List<MenuOrderDTO.Create> menuOrders = new ArrayList<>();
        int orderId = 100;
        int storeId = 101;
        Store mockStore = new Store(storeId);
        User mockUser = new User("4whomtbts", "1234", "손님", false);
        mockUser.setUserId(1);
        given(userRepository.findUserByUserId(mockUser.getUserId())).willReturn(mockUser);
        DawnOrder order = new DawnOrder(orderId, 0, mockStore, mockUser);
        final int INVALID_MENUID = -1;
        MenuOrderDTO.Create menuOrder1 = new MenuOrderDTO.Create(INVALID_MENUID, 2);
        menuOrders.addAll(Arrays.asList(menuOrder1));
        OrderDTO.CreateOrder newOrder = new OrderDTO.CreateOrder(1, mockUser.getUserId(), menuOrders);
        OrderDTO.GetOrder result = storeService.submitNewOrder(newOrder);
    }

    @Test(expected = DawnException.class)
    public void FAIL_submitNewOrder_WHEN_단품개수가_0개일때() throws DawnException {
        List<MenuOrderDTO.Create> menuOrders = new ArrayList<>();
        final int orderId = 100;
        final int storeId = 101;
        Store mockStore = new Store(storeId);
        User mockUser = new User("4whomtbts", "1234", "손님", false);
        mockUser.setUserId(1);
        given(userRepository.findUserByUserId(mockUser.getUserId())).willReturn(mockUser);
        DawnOrder order = new DawnOrder(orderId, 0, mockStore, mockUser);
        final int MENU_ID = 1;
        MenuOrderDTO.Create menuOrder1 = new MenuOrderDTO.Create(MENU_ID, 0);
        menuOrders.addAll(Arrays.asList(menuOrder1));
        OrderDTO.CreateOrder newOrder = new OrderDTO.CreateOrder(storeId, mockUser.getUserId(), menuOrders);
        OrderDTO.GetOrder result = storeService.submitNewOrder(newOrder);
    }

    @Test
    public void SUCCESS_getSalesOfStore() {
        int storeId = 1;
        List<DawnOrder> orders = orderRepository.findByStoreId(storeId);
        Store mockStore = new Store(storeId);
        User mockUser = new User("4whomtbts", "1234", "손님", false);
        DawnOrder order1 = new DawnOrder(1, 10000, mockStore, mockUser);
        order1.setMenuOrders(Collections.singletonList(new MenuOrder(1, order1, new Menu("서브웨이", "샌드위치", 5000, "", mockStore))));
        DawnOrder order2 = new DawnOrder(2, 10000, mockStore, mockUser);
        order2.setMenuOrders(Collections.singletonList(new MenuOrder(2, order2, new Menu("맥도날드", "빅맥", 5000, "", mockStore))));
        given(orderRepository.findByStoreId(storeId)).willReturn(Arrays.asList(order1, order2));
        SalesDTO.GetSales saleResult = storeService.getSalesOfStore(storeId);
        List<OrderDTO.GetOrder> orderList = saleResult.getOrders();
        assertThat(orderList.size(), is(2));
        for (OrderDTO.GetOrder order : orderList) {
            assertThat(order.getOrderStatus(), is(OrderStatus.NEW.getStatusTitle()));
            System.out.println(order.getCreatedAt());
        }
    }

}
