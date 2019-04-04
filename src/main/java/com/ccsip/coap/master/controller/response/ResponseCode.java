package com.ccsip.coap.master.controller.response;

public enum ResponseCode {
	/** SUCCESS */
	SUCCESS("0000", "SUCCESS"),
	/** No accessToken */
	SYS_0001("0001", "No accessToken"),
	/** Not login */
	SYS_0002("0002", "Not login"),
	/** Please Check the required parameters */
	SYS_0003("0003", "Check the required parameters"),
	/** LIST EMPTY */
	LIST_EMPTY("0004", "LIST EMPTY"),
	/** Can't find the resources */
	UNFOUND("0005", "Can't find the resources"),
	/** UNKNOWN */
	UNKNOWN("9999", "UNKNOWN"),
	/** */
	USER_1001("1001", ""),
	/** The username or email already registered */
	USER_1002("1002", "The username or email already registered"),
	/** ERROR Incorrect username or password */
	USER_1003("1003", "ERROR Incorrect username or password"),

	ROLE_2001("2001", ""),

	STRATETY_3001("3001", ""),
	/** NO SUCH ALERTGROUP FOUND */
	ALERT_4001("4001", "NO SUCH ALERTGROUP FOUND"),
	
	ALERT_4002("4002", " ALERTGROUP NAME DUPLICATE"), 
	
	IN_USE("4003", " ALERTGROUP IN USE");

	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private ResponseCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
