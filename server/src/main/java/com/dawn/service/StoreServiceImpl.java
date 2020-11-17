package com.dawn.service;

import com.dawn.dto.MenuDTO;
import com.dawn.dto.MenuOrderDTO;
import com.dawn.dto.OrderDTO;
import com.dawn.dto.StoreDTO;
import com.dawn.model.*;
import com.dawn.repository.menu.MenuRepository;
import com.dawn.repository.menuorder.MenuOrderRepository;
import com.dawn.repository.order.OrderRepository;
import com.dawn.repository.store.StoreRepository;
import com.dawn.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MenuOrderRepository menuOrderRepository;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    @Override
    public Store createStore(StoreDTO.Create newStore) {
        User user = userRepository.findUserByUserId(newStore.getOwnerUserId());
        Store store =
                new Store(newStore.getStoreTitle(), newStore.getLocation(),
                          newStore.getBusinessHour(), newStore.getDescription(), user);
        return storeRepository.save(store);
    }

    @Override
    public OrderDTO.Get submitNewOrder(OrderDTO.Create newOrder) {
        List<MenuOrder> menuOrders = new ArrayList<>();
        List<MenuOrderDTO.Get> menuOrderResult = new ArrayList<>();
        List<MenuOrderDTO.Create> newMenuOrders = newOrder.getMenusOrders();
        Order order = new Order(0, new Store(newOrder.getStoreId()));
        order = orderRepository.save(order);
        int totalPrice = 0;
        for (MenuOrderDTO.Create menuOrder : newMenuOrders) {
            Menu menu = menuRepository.findByMenuId(menuOrder.getMenuId());
            totalPrice += menu.getPrice();
            menuOrders.add(new MenuOrder(menuOrder.getQuantity(), order, menu));
        }
        menuOrders = menuOrderRepository.saveAll(menuOrders);
        for (MenuOrder menuOrder : menuOrders) {
            Menu currMenu = menuOrder.getMenu();
            menuOrderResult.add(
                    new MenuOrderDTO.Get(
                            menuOrder.getMenuOrderId(),
                            menuOrder.getQuantity(),
                            new MenuDTO.OrderListItem(
                                    currMenu.getMenuId(),
                                    currMenu.getMenuTitle(),
                                    currMenu.getPrice(),
                                    currMenu.getImageURL())));
        }
        order.setMenuOrders(menuOrders);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        return new OrderDTO.Get(order.getOrderId(), totalPrice, menuOrderResult);
    }
}

