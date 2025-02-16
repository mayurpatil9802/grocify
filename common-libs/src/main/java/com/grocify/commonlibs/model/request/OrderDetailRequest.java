package com.grocify.commonlibs.model.request;

import com.grocify.commonlibs.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Builder
@Data
public class OrderDetailRequest {

	private Map<String, String> metadata;

}
