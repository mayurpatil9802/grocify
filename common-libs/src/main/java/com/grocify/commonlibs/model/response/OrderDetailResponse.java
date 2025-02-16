package com.grocify.commonlibs.model.response;

import com.grocify.commonlibs.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Builder
@Data
public class OrderDetailResponse {

	private Long orderId;

	private Long userId;

	private Map<String, String> metadata;

	private Long deliveryBy;

	private OrderStatus status;

	private LocalDate orderTakeTime;

	private LocalDate orderDeliveredTime;

}
