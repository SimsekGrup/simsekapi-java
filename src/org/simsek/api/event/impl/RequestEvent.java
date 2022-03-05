package org.simsek.api.event.impl;

import org.simsek.api.event.Event;
import org.simsek.api.type.APIType;

import lombok.Getter;

@Getter
public class RequestEvent extends Event {
	
	private final APIType apiType;
	
	public RequestEvent(APIType type, String requestUrl) {
		this.apiType = type;
	}
}
