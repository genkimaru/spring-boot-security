package com.accenture.coap.master.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.accenture.coap.master.dto.AppDto;
import com.accenture.coap.master.dto.AppListDto;
import com.accenture.coap.master.dto.ComponentDto;
import com.accenture.coap.master.dto.ServerDto;
import com.accenture.coap.master.metadata.domain.metadata.App;
import com.accenture.coap.master.metadata.domain.metadata.Component;
import com.accenture.coap.master.metadata.domain.metadata.Server;

public class AppAssembler {
	public static App assembleApp1(AppDto dto) {
		return null;
	}

	public static AppDto extractAppDTO(App app) {
		AppDto dto = new AppDto();
		dto.setAirId(app.getAirId());
		dto.setAppName(app.getName());
		dto.setServiceTier(app.getServiceTier());
		Set<Component> componentList = app.getComponents();

		if (componentList != null) {
			List<ComponentDto> componentDtoList = new ArrayList<ComponentDto>();
			for (Component component : componentList) {
				List<ServerDto> serverDtoList = new ArrayList<ServerDto>();
				Set<Server> servers = component.getServers();
				if (servers != null) {
					for (Server server : servers) {
						serverDtoList.add(new ServerDto(server.getId(), server.getName(), server.getAirId()));
					}
				}
				componentDtoList.add(
						new ComponentDto(component.getId(), component.getName(), component.getAirId(), serverDtoList));
			}
			dto.setComponents(componentDtoList);
		}

		return dto;
	}

	public static List<AppListDto> extractAppListDTO(List<App> apps) {
		List<AppListDto> list = new ArrayList<AppListDto>();
		for (App app : apps) {
			AppListDto dto = new AppListDto();
			dto.setAirId(app.getAirId());
			dto.setAppName(app.getName());
			// dto.setAlertingStrategies("");
			list.add(dto);
		}
		return list;
	}

	public static void excludeLoopLinding(List<App> apps) {
		if (apps != null) {
			for (App app : apps) {
				excludeLoopLinding(app);
			}
		}
	}

	public static void excludeLoopLinding(App app) {
		Set<Component> components = app.getComponents();
		for (Component c : components) {
			c.setApp(null);
			Set<Server> servers = c.getServers();
			for (Server s : servers) {
				s.setComponent(null);
			}
		}
	}
}
