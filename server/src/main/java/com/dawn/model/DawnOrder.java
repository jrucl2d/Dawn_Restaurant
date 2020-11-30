package com.dawn.model;

import com.dawn.dto.MenuOrderDTO;
import com.dawn.dto.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DawnOrder extends BaseAuditorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dawnOrderId;

    @Column
    private int totalPrice;

    @Column
    private boolean isPayed = false;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus = OrderStatus.NEW;

    @OneToMany
    private List<MenuOrder> menuOrders = new ArrayList<>();

    @ManyToOne
    private Store store;

    @ManyToOne
    private User user;

    public DawnOrder(int orderId, int totalPrice, Store store, User user) {
        this.dawnOrderId = orderId;
        this.totalPrice = totalPrice;
        this.store = store;
        this.user = user;
    }

    public DawnOrder(int totalPrice, Store store, User user) {
        this.totalPrice = totalPrice;
        this.store = store;
        this.user = user;
    }

    public static OrderDTO.GetOrder toOrderDTOGet(DawnOrder order) {
        return new OrderDTO.GetOrder(
                order.getDawnOrderId(),
                order.getTotalPrice(),
                order.getUser().getUserId(),
                order.getCreatedAt(),
                order.getOrderStatus().getStatusTitle(),
                order.getMenuOrders().stream()
                        .map(x -> new MenuOrderDTO.Get(
                                x.getMenuOrderId(), x.getQuantity(),
                                Menu.toMenuDTOOrderListItem(x.getMenu())))
                        .collect(Collectors.toList()));
    }
}
