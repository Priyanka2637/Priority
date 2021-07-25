package com.project.ordermgt.db.mapper;

import java.util.List;

import com.project.ordermgt.priorityProject.data.SqlAdapter;

public interface SourceDbMapper {

	public List<String> getIdList(SqlAdapter sqlAdapter);
}
