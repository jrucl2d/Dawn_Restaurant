package com.dawn.service;

import com.dawn.dto.MenuDTO;
import com.dawn.dto.MenuOrderDTO;
import com.dawn.dto.OrderDTO;
import com.dawn.dto.StoreDTO;
import com.dawn.exception.DawnException;
import com.dawn.model.*;
import com.dawn.repository.menu.MenuRepository;
import com.dawn.repository.menuorder.MenuOrderRepository;
import com.dawn.repository.order.OrderRepository;
import com.dawn.repository.store.StoreRepository;
import com.dawn.repository.user.UserRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
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
    //private final Storage storage;

    @Override
    public List<StoreDTO.GetStore> getAllStoreOfUserByUserId(int userId) {
        List<Store> fetchResult = storeRepository.getAllStoreOfUserByUserId(userId);
        List<StoreDTO.GetStore> result = new ArrayList<>();
        fetchResult.stream().map(Store::toGetStore).forEach(result::add);
        return result;
    }

    @Override
    public Store createStore(StoreDTO.CreateStore newStore, MultipartFile profileImage) throws IOException {
        User user = userRepository.findUserByUserId(newStore.getOwnerUserId());
        String jsonPath = "C:\\Users\\ysk78\\src\\sogong\\server\\sasuke.json";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        Store store =
                new Store(newStore.getStoreTitle(), newStore.getLocation(),
                          newStore.getBusinessHour(), newStore.getDescription(), "", user);
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder("sogong", "store/"+store.getStoreId()+"-profile.jpg")
                        .build(), profileImage.getBytes());
        System.out.println("generated blob= " + blobInfo.getGeneratedId());
        return storeRepository.save(store);
    }

    @Override
    @Transactional
    public OrderDTO.Get submitNewOrder(OrderDTO.Create newOrder) throws DawnException {
        List<MenuOrder> menuOrders = new ArrayList<>();
        List<MenuOrderDTO.Get> menuOrderResult = new ArrayList<>();
        List<MenuOrderDTO.Create> newMenuOrders = newOrder.getMenusOrders();
        Order order = new Order(0, new Store(newOrder.getStoreId()));
        order = orderRepository.save(order);
        int totalPrice = 0;
        for (MenuOrderDTO.Create menuOrder : newMenuOrders) {
            Menu menu = menuRepository.findByMenuId(menuOrder.getMenuId());
            if (menu == null) {
                throw new DawnException("주문하려는 메뉴가 존재하지 않습니다", "causation: menuId = ["+menuOrder.getMenuId()+"]");
            }
            totalPrice += (menu.getPrice() * menuOrder.getQuantity());
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

    @Override
    public void removeAllOrderOfStore(int storeId) {
        orderRepository.deleteAllOrderOfStore(storeId);
    }

    @Transactional
    public void removeAllStore(List<StoreDTO.DeleteStore> targetStores) {
        List<Store> targetList = new ArrayList<>();
        targetStores.forEach(x -> targetList.add(new Store(x.getStoreId())));
        storeRepository.deleteAll(targetList);
    }

    @Transactional
    public void removeStoreByStoreId(int storeId) {
        storeRepository.deleteById(storeId);
    }

    @Transactional
    public void removeAllStoreOfUserByUserId(int userId) {
        storeRepository.deleteAllStoreOfUserByUserId(userId);
    }
}

