package com.grocify.storemgmt.controller;

import com.grocify.commonlibs.model.request.OrderDetailRequest;
import com.grocify.commonlibs.model.response.OrderDetailResponse;
import com.grocify.storemgmt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/{userId}")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping
	private List<OrderDetailResponse> getOrder(){
		return orderService.getOrderDetails();
	}

	@PostMapping
	private OrderDetailResponse insertOrder(@PathVariable Long userId, @RequestBody OrderDetailRequest orderDetailRequest){
		return orderService.insertOrderDetail(userId,orderDetailRequest);
	}

	@GetMapping("orderId/{orderId}/accept/{deliveryId}")
	private OrderDetailResponse acceptOrderByDeliveryMan(@PathVariable Long deliveryId,@PathVariable Long orderId){
		return orderService.UpdateDeliveryIdDetail(deliveryId,orderId);
	}
	@GetMapping("orderId/{orderId}/done")
	private OrderDetailResponse updateDeliveryStatus(@PathVariable Long orderId){
		return orderService.updateDeliveryStatusDetail(orderId);
	}
	@GetMapping("/orderdetail")
	private List<OrderDetailResponse> orderDetailsOfUser(@PathVariable Long userId){
		return orderService.userOrderDetailStatus(userId);
	}
	@GetMapping("/{deliveryBy}")
	private List<OrderDetailResponse> acceptedOrder(@PathVariable Long deliveryBy){
		return orderService.getOrderDetailsByDeliveryId(deliveryBy);
	}

	@GetMapping("orderId/{orderId}/cancel")
	private OrderDetailResponse cancelDelivery(@PathVariable Long orderId){
		return orderService.cancelDeliveryStatusDetail(orderId);
	}




}
