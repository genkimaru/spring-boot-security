package com.accenture.coap.master.assembler;

import java.util.List;
import java.util.Set;

import com.accenture.coap.master.dto.AlertSourceDto;
import com.accenture.coap.master.metadata.domain.confdata.AlertSource;
import com.accenture.coap.master.metadata.domain.confdata.CriticalAlertList;

public class AlertSourceAssembler {

	public static AlertSource assembleAlertSource(AlertSourceDto dto) {
		AlertSource as = new AlertSource();
		as.setId(dto.getAlertSourceId());
		as.setName(dto.getAlertSourceName());
		return as;
	}

	public static AlertSourceDto extractAlertSourceDto(AlertSource as) {
		AlertSourceDto dto = new AlertSourceDto();
		dto.setAlertSourceId(as.getId());
		dto.setAlertSourceName(as.getName());
		return dto;
	}

	public static void excludeLoopLinding(List<AlertSource> asList) {
		if (asList != null)
			for (AlertSource as : asList) {
				excludeLoopLinding(as);
			}
	}

	public static void excludeLoopLinding(AlertSource as) {
		try {
			Set<AlertSource> children = as.getChildren();
			if (children != null && !children.isEmpty()) {
				for (AlertSource child : children) {
					AlertSource parent = child.getParent();
					if (parent != null) {
						AlertSource p = new AlertSource();
						p.setId(parent.getId());
						p.setName(parent.getName());
						p.setAirId(parent.getAirId());
						p.setRefId(parent.getRefId());
						p.setStatisticalThreshold(parent.getStatisticalThreshold());
						p.setCriticalAlertList(parent.getCriticalAlertList());
						child.setParent(p);
					}
					excludeLoopLinding(child);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void excludeLoopLindingForMetadata(List<AlertSource> asList) {
		if (asList != null)
			for (AlertSource as : asList) {
				excludeLoopLindingForMetadata(as);
			}
	}

	public static void excludeLoopLindingForMetadata(AlertSource as) {
		try {
			as.setDataType(null);
			// as.setInfrastructureUnit(null);
			CriticalAlertList cal = as.getCriticalAlertList();
			if (cal != null) {
				cal.setAdditionalAlerts(null);
				cal.setAlertGroup(null);
			}
			Set<AlertSource> children = as.getChildren();
			if (children != null && !children.isEmpty()) {
				for (AlertSource child : children) {
					AlertSource parent = child.getParent();
					if (parent != null) {
						AlertSource p = new AlertSource();
						p.setId(parent.getId());
						p.setName(parent.getName());
						p.setAirId(parent.getAirId());
						p.setRefId(parent.getRefId());
						p.setStatisticalThreshold(parent.getStatisticalThreshold());
						p.setCriticalAlertList(parent.getCriticalAlertList());
						p.setLevel(parent.getLevel());

						child.setParent(p);
					}
					excludeLoopLindingForMetadata(child);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
