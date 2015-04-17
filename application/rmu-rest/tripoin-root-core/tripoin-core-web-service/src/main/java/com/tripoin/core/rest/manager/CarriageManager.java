package com.tripoin.core.rest.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tripoin.core.domain.CarriageDTO;
import com.tripoin.core.domain.Carriages;
import com.tripoin.core.pojo.Carriage;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("carriageManager")
public class CarriageManager {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(CarriageManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<Carriages> getCarriages(Message<?> inMessage){
	
		Carriages carriages = new Carriages();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			MessageHeaders headers = inMessage.getHeaders();
			String id = (String)headers.get("carriageId");
			boolean isFound;
			if (id.equals("0")){
				List<Carriage> carriageList = iGenericManagerJpa.loadObjects(Carriage.class);
				List<CarriageDTO> carriageDTOList = new ArrayList<CarriageDTO>();
				for (Carriage c : carriageList) {
					LOGGER.debug("data :"+c.toString());
					CarriageDTO data = new CarriageDTO(c.getId(), c.getCode(), c.getNo(), c.getStatus(), c.getRemarks());
					carriageDTOList.add(data);
				} 
				carriages.setMaster_cariage(carriageDTOList);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", carriages, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Carriage Not Found", "EMPTY", carriages, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", carriages, responseHeaderMap);
		}
		Message<Carriages> message = new GenericMessage<Carriages>(carriages, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(String responseCode, 
						String responseMsg,
						String result,
						Carriages carriages, 
						Map<String, Object> responseHeaderMap){
		
		carriages.setResponse_code(responseCode);
		carriages.setResponse_msg(responseMsg);
		carriages.setRESULT(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}