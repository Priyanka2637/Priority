package com.project.ordermgt.db.mapper;

import java.util.List;
import java.util.Map;

import com.project.ordermgt.priorityProject.data.PriorityAreas;


public interface PriorityMapper {

	List<PriorityAreas> getAllPriorityArea();

	int insertInRatingDB(Map<String, Object> filterInput);

	
}
