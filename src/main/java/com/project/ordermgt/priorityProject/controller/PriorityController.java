package com.project.ordermgt.priorityProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import com.project.ordermgt.priorityProject.data.PriorityAreas;
import com.project.ordermgt.priorityProject.util.BeanFactory;
import com.project.ordermgt.db.mapper.PriorityMapper;
import org.apache.log4j.Logger;

@RestController
@PropertySource("file:${configPath}")
public class PriorityController {

	PriorityMapper priorityMapper;
	@PostConstruct
	public void init() {
		
	}

	public PriorityController() {
		
		priorityMapper = (PriorityMapper) BeanFactory.getContext().getBean("priorityMapper");	
	}
	
	private static Logger log = Logger.getLogger(PriorityController.class);
	
	@GetMapping("/getAllPriorityArea")
    public @ResponseBody  List<PriorityAreas> getAllPriorityArea() {
		String nameofCurrentMethod = new Throwable().getStackTrace()[0].getMethodName();
		long startTime = System.currentTimeMillis();
		List<PriorityAreas> priorityAreas = new ArrayList<PriorityAreas>();
		try {
			priorityAreas = priorityMapper.getAllPriorityArea();
		} catch (Exception e) {
			e.printStackTrace();
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			log.info(nameofCurrentMethod + ", Failure, "  + timeTaken + "milliseconds");
		}
		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		log.info(nameofCurrentMethod + ", Success, " + timeTaken + "milliseconds");
		return priorityAreas;
	}
	
//	RequestBody  for API setRating
//	{
//	    "userId" : "1111",
//		"areas":  ["Relationships","Connection", "Career", "Wealth"],
//		"priority":  ["1","2", "3", "4"],
//	    "satisfaction" :  ["1","2", "3", "5"]
//	}
	
	
	@RequestMapping(value = "/setRating", method = RequestMethod.POST)
	public String setRating(@RequestBody String jsonRequest) throws Exception {
		String namOfCurrentMethod = new Throwable().getStackTrace()[0].getMethodName();
		long startTime = System.currentTimeMillis();
		Map<String, Object> requestData = new HashMap<String, Object>();
		String userId, areas,priority,satisfaction;
		int row;
		try {
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(jsonRequest);
			userId = json.getAsString("userId");
			areas  = json.getAsString("areas");
			priority = json.getAsString("priority");
			satisfaction = json.getAsString("satisfaction");
			String[] areasList = areas.replace("[", "").replace("]", "").replace("\"", "").split(",");
			String[] priorityList = priority.replace("[", "").replace("]", "").replace("\"", "").split(",");
			String[] satisfactionList = satisfaction.replace("[", "").replace("]", "").replace("\"", "").split(",");
			for (int i=0; i<areasList.length; i++)  {  
				Map<String,Object> filterInput = new HashMap<String,Object>();
				filterInput.put("userId", userId);
				filterInput.put("area", areasList[i]);
				filterInput.put("priorityValue", priorityList[i]);
				filterInput.put("satisfactionRating", satisfactionList[i]);
				if(Integer.parseInt(satisfactionList[i]) > 0 && Integer.parseInt(satisfactionList[i]) <= 5) { // scale of 1 to 5.
					row = priorityMapper.insertInRatingDB(filterInput);
					System.out.println(row);
				}
			}  
		} catch (Exception e) {
			e.printStackTrace();
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			log.info(namOfCurrentMethod + ", Failure, "  + timeTaken + "milliseconds");
			throw e;
		}
		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		log.info(namOfCurrentMethod + ", Success, " + timeTaken + "milliseconds");
		return "Success";
	}

	
}
