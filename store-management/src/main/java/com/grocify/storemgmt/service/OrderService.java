package com.grocify.storemgmt.service;

import com.grocify.commonlibs.dao.OrderDao;
import com.grocify.commonlibs.dto.OrderDTO;
import com.grocify.commonlibs.enums.OrderStatus;
import com.grocify.commonlibs.mapper.OrderMapper;
import com.grocify.commonlibs.model.request.OrderDetailRequest;
import com.grocify.commonlibs.model.response.OrderDetailResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderMapper orderMapper;

	public List<OrderDetailResponse> getOrderDetails() {

		return orderDao.getOrderDetails().stream().filter(product ->product.getStatus()==OrderStatus.PENDING )
				.map(product -> orderMapper.orderDTOToOrderResponse(product))
				.toList();
	}

	public List<OrderDetailResponse> getOrderDetailsByDeliveryId(@PathVariable Long deliveryId ) {

		return orderDao.getOrderDetails().stream().filter(product ->product.getStatus()==OrderStatus.PROGRESS ).filter(product -> Objects.equals(product.getDeliveryBy(), deliveryId))
				.map(product -> orderMapper.orderDTOToOrderResponse(product))
				.toList();
	}

	public OrderDetailResponse insertOrderDetail(Long userId, OrderDetailRequest orderDetailRequest) {
		OrderDTO userDTO = orderMapper.orderRequestToOrderDTO(orderDetailRequest);
		userDTO.setStatus(OrderStatus.PENDING);
		userDTO.setUserId(userId);
		userDTO = orderDao.insertOrderData(userDTO);
		return orderMapper.orderDTOToOrderResponse(userDTO);
	}

	public OrderDetailResponse UpdateDeliveryIdDetail(Long deliveryId,Long orderId) {

		OrderDTO orderDTO= orderDao.getOrderDetail(orderId);
		orderDTO.setDeliveryBy(deliveryId);
		orderDTO.setStatus(OrderStatus.PROGRESS);
		orderDTO.setOrderTakeTime(LocalDate.now());
		orderDTO = orderDao.updateDeliveryDetail(orderDTO);
		return orderMapper.orderDTOToOrderResponse(orderDTO);



	}

	public OrderDetailResponse updateDeliveryStatusDetail(Long orderId) {
		OrderDTO orderDTO= orderDao.getOrderDetail(orderId);

		orderDTO.setStatus(OrderStatus.DELIVERED);
		orderDTO.setOrderDeliveredTime(LocalDate.now());
		orderDTO = orderDao.updateDeliveryDetail(orderDTO);
		return orderMapper.orderDTOToOrderResponse(orderDTO);
	}

	public OrderDetailResponse cancelDeliveryStatusDetail(Long orderId) {
		OrderDTO orderDTO= orderDao.getOrderDetail(orderId);
		orderDTO.setStatus(OrderStatus.CANCLED);
		orderDTO = orderDao.updateDeliveryDetail(orderDTO);
		return orderMapper.orderDTOToOrderResponse(orderDTO);
	}

	public List<OrderDetailResponse> userOrderDetailStatus(Long userId) {
		return orderDao.getOrderDetails().stream().filter(product -> Objects.equals(product.getUserId(), userId))
				.map(product -> orderMapper.orderDTOToOrderResponse(product))
				.toList();
	}
}
