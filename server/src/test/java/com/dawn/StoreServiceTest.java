package com.dawn;

import com.dawn.dto.MenuDTO;
import com.dawn.dto.StoreDTO;
import com.dawn.model.Store;
import com.dawn.model.User;
import com.dawn.repository.store.StoreRepository;
import com.dawn.repository.user.UserRepository;
import com.dawn.service.StoreService;
import com.dawn.service.StoreServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @Mock
    public StoreRepository storeRepository;

    @Mock
    public UserRepository userRepository;

    public StoreService storeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        storeService = new StoreServiceImpl(userRepository, storeRepository);
    }

    @Test
    public void storeCreate() {
        given(userRepository.findUserByUserId(1))
                .willReturn(new User("loginId", "1234", "jun", true));
        when(storeRepository.save(any(Store.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        StoreDTO.Create newStore = new StoreDTO.Create(
            1, "새벽식당", "신공", "24시", "새벽에만 하는 식당"
        );
        Store store = storeService.createStore(newStore);
        assertThat(store, is(notNullValue()));
    }

    @Test
    public void storeAddMenu() {
        User user = new User("loginId", "1234", "jun", true);
        Store store = new Store("새벽식당", "신공", "24시", "새벽에만 하는 식당", user);
        List<MenuDTO.Create> menuDTOs =
                new ArrayList<>(Arrays.asList(new MenuDTO.Create(1, "햄버거", "맥도날드..", 6000),
                        new MenuDTO.Create(1, "샌드위치", "서브웨이..", 4000)));
    }
}

