package com.dawn;

import com.dawn.controller.MenuController;
import com.dawn.dto.MenuDTO;
import com.dawn.repository.menu.MenuRepository;
import com.dawn.service.MenuServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MenuController.class)
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuServiceImpl menuService;

    @InjectMocks
    private MenuController menuController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(menuController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    //https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/
    //https://stackoverflow.com/questions/63311426/mockmvc-is-not-autowired-it-is-null
    @Test
    public void getMenus() throws Exception {
        given(menuService.getAllMenus()).willReturn(
                new ArrayList<>(Arrays.asList(
                    new MenuDTO.GetMenu(1, 2, "햄버거", "맥도날드", 6000, "")
                )));
        MockHttpServletResponse response =
                mockMvc.perform(get("/menus")
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();
        System.out.println("응답 = " + response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotNull();
    }

    @Test
    public void addMenus() {

    }
}
