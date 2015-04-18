package com.tripoin.core.rest.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tripoin.core.domain.OrderDetailDTO;
import com.tripoin.core.domain.OrderDetails;
import com.tripoin.core.pojo.OrderDetail;
import com.tripoin.core.service.IGenericManagerJpa;

@Service("orderDetailManager")
public class OrderDetailManager {
	private static transient final Logger LOGGER = LoggerFactory.getLogger(CarriageManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderDetails> getOrderDetails(Message<?> inMessage){
	
		OrderDetails orderDetails = new OrderDetails();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<OrderDetail> orderDetailList = iGenericManagerJpa.loadObjects(OrderDetail.class);
			boolean isFound;
			if (orderDetailList!=null){
				List<OrderDetailDTO> orderDetailDTOList = new ArrayList<OrderDetailDTO>();
				for (OrderDetail o : orderDetailList) {
					LOGGER.debug("data :"+o.toString());
					OrderDetailDTO data = new OrderDetailDTO(o.getOrderHeader().getOrderNo(), o.getTotalOrder(), o.getTotalAmount(), o.getStatus(), o.getMenu().getId(), o.getMenu().getName(), o.getOrderHeader().getSeat().getId(), o.getOrderHeader().getCarriage().getId(), o.getOrderHeader().getTrain().getId());
					orderDetailDTOList.add(data);
				} 
				orderDetails.setTrx_order_detail(orderDetailDTOList);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", orderDetails, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Seat Not Found", "EMPTY", orderDetails, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", orderDetails, responseHeaderMap);
		}
		Message<OrderDetails> message = new GenericMessage<OrderDetails>(orderDetails, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(String responseCode, String responseMsg, String result, OrderDetails orderDetails, Map<String, Object> responseHeaderMap){
		
		orderDetails.setResponse_code(responseCode);
		orderDetails.setResponse_msg(responseMsg);
		orderDetails.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}
