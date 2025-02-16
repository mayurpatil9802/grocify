package com.grocify.commonlibs.entity;


import com.grocify.commonlibs.enums.OrderStatus;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Data
@Table(name = "order_detail")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	private Long userId;

	@Type(JsonType.class)
	@Column(columnDefinition = "json")
	private Map<String, String> orderDetails;

	private Long deliveryBy;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	private LocalDate orderTakeTime;

	private LocalDate orderDeliveredTime;
}
