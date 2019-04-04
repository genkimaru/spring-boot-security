package com.ccsip.coap.master.assembler;

import com.ccsip.coap.master.dto.DataTypeDto;
import com.ccsip.coap.master.metadata.domain.confdata.DataType;

public class DataTypeAssembler {

	public static DataType assemble(DataTypeDto dto) {
		DataType dt = new DataType();
		dt.setId(dto.getDataTypeId());
		dt.setName(dto.getDataTypeName());
		return dt;
	}

	public static DataTypeDto extract(DataType dt) {
		DataTypeDto dto = new DataTypeDto();
		dto.setDataTypeId(dt.getId());
		dto.setDataTypeName(dt.getName());
		return dto;
	}

}
