package com.grocify.commonlibs.dto;

import com.grocify.commonlibs.enums.OrderStatus;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Map;


@Builder
@Data
public class OrderDTO {
	private Long orderId;

	private Long userId;

	private Map<String, String> metadata;

	private Long deliveryBy;

	private OrderStatus status;

	private LocalDate orderTakeTime;

	private LocalDate orderDeliveredTime;
}
