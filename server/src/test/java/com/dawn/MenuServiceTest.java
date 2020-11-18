package com.dawn;

import com.dawn.model.Store;
import com.dawn.model.User;
import com.dawn.repository.menu.MenuRepository;
import com.dawn.repository.store.StoreRepository;
import com.dawn.service.MenuService;
import com.dawn.service.MenuServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    public MenuRepository menuRepository;

    @Mock
    public StoreRepository storeRepository;

    public MenuService menuService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        menuService = new MenuServiceImpl(menuRepository);
    }

    @Test
    public void getAllMenuOfStore() {
        int storeId = 1;
        User user = new User("loginId", "1234", "jun", true);
        Store targetStore = new Store("새벽식당", "신공", "새벽에 영업해요", "영업 중..", user);
    }
}
