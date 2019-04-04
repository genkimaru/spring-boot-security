package com.ccsip.coap.master.dto;

import com.ccsip.coap.master.assembler.AlertSourceAssembler;
import com.ccsip.coap.master.metadata.domain.confdata.AlertSource;
import com.ccsip.coap.master.metadata.domain.confdata.DataType;

public class GetRootAlertSource {
	public GetRootAlertSource(long airId, DataType dt, AlertSource as) {
		this.airIdAndDataType = new AirIdAndDataType(airId, dt);
		AlertSourceAssembler.excludeLoopLindingForMetadata(as);
		this.alertSource = as;
	}

	class AirIdAndDataType {
		long airId;
		DataType dataType;

		AirIdAndDataType(long airId, DataType dt) {
			this.airId = airId;
			this.dataType = dt;
		}

		public long getAirId() {
			return airId;
		}

		public void setAirId(long airId) {
			this.airId = airId;
		}

		public DataType getDataType() {
			return dataType;
		}

		public void setDataType(DataType dataType) {
			this.dataType = dataType;
		}
	}

	AirIdAndDataType airIdAndDataType;
	AlertSource alertSource;

	public AirIdAndDataType getAirIdAndDataType() {
		return airIdAndDataType;
	}

	public void setAirIdAndDataType(AirIdAndDataType airIdAndDataType) {
		this.airIdAndDataType = airIdAndDataType;
	}

	public AlertSource getAlertSource() {
		return alertSource;
	}

	public void setAlertSource(AlertSource alertSource) {
		this.alertSource = alertSource;
	}

}