package com.grocify.commonlibs.mapper;

import com.grocify.commonlibs.dto.OrderDTO;
import com.grocify.commonlibs.entity.OrderEntity;
import com.grocify.commonlibs.model.request.OrderDetailRequest;
import com.grocify.commonlibs.model.response.OrderDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
	public OrderDTO orderEntityToOrderDTO(OrderEntity product) {
		return OrderDTO.builder()
				.orderId(product.getOrderId())
				.userId(product.getUserId())
				.metadata(product.getOrderDetails())
				.orderDeliveredTime(product.getOrderDeliveredTime())
				.orderTakeTime(product.getOrderTakeTime())
				.status(product.getStatus())
				.deliveryBy(product.getDeliveryBy())
				.build();
	}

	public OrderDetailResponse orderDTOToOrderResponse(OrderDTO product) {
    return OrderDetailResponse.builder()
        .orderId(product.getOrderId())
        .userId(product.getUserId())
        .metadata(product.getMetadata())
        .orderDeliveredTime(product.getOrderDeliveredTime())
        .orderTakeTime(product.getOrderTakeTime())
        .status(product.getStatus())
        .deliveryBy(product.getDeliveryBy())
        .build();
	}

	public OrderDTO orderRequestToOrderDTO(OrderDetailRequest orderDetailRequest) {
		return OrderDTO.builder()
				.metadata(orderDetailRequest.getMetadata())
				.build();
	}

	public OrderEntity orderDTOToOrderEntity(OrderDTO userDTO) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderDetails(userDTO.getMetadata());
		return orderEntity;
	}
}
