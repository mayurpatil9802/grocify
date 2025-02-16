package com.grocify.commonlibs.dao;


import com.grocify.commonlibs.dto.OrderDTO;
import com.grocify.commonlibs.entity.OrderEntity;
import com.grocify.commonlibs.enums.OrderStatus;
import com.grocify.commonlibs.mapper.OrderMapper;
import com.grocify.commonlibs.repository.OrderRepository;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDao {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper orderMapper;

	public List<OrderDTO> getOrderDetails() {
		return orderRepository.findAll().stream()
				.map(product -> orderMapper.orderEntityToOrderDTO(product))
				.toList();
	}

	public OrderDTO insertOrderData(OrderDTO userDTO) {
		OrderEntity orderEntity = orderMapper.orderDTOToOrderEntity(userDTO);
		orderEntity.setOrderTakeTime(userDTO.getOrderTakeTime());
		orderEntity.setStatus(userDTO.getStatus());
		orderEntity.setUserId(userDTO.getUserId());
		orderEntity = orderRepository.save(orderEntity);
		return orderMapper.orderEntityToOrderDTO(orderEntity);

	}

	public OrderDTO getOrderDetail(Long orderId) {
		Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
		if(orderEntity.isEmpty()){
			throw new RuntimeException("order is not valid");
		}
		return orderMapper.orderEntityToOrderDTO(orderEntity.get());
	}

	public OrderDTO updateDeliveryDetail(OrderDTO orderDTO) {
		OrderEntity orderEntity = orderMapper.orderDTOToOrderEntity(orderDTO);
    	orderEntity.setOrderId(orderDTO.getOrderId());
		orderEntity.setUserId(orderDTO.getUserId());
		orderEntity.setDeliveryBy(orderDTO.getDeliveryBy());
		orderEntity.setOrderDeliveredTime(orderDTO.getOrderDeliveredTime());
		orderEntity.setOrderTakeTime(orderDTO.getOrderTakeTime());
		orderEntity.setStatus(orderDTO.getStatus());
		orderEntity= orderRepository.save(orderEntity);
    	System.out.println(orderEntity);
		return orderMapper.orderEntityToOrderDTO(orderEntity);
	}
}
