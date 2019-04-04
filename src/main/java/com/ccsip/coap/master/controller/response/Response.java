package com.ccsip.coap.master.controller.response;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Response", description = "Please according to the modelType field to find the data model")
public class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2388874285590094550L;
	private String errCode = "0000";
	private String errDesc = "";
	private Object model = null;
	private String modelType = "None";

	public Response() {
		super();
	}

	public Response(String errCode) {
		super();
		this.errCode = errCode;
		this.errDesc = getDescByCode(errCode);
	}

	public Response(String errCode, Object model, String modelType) {
		super();
		this.errCode = errCode;
		this.errDesc = getDescByCode(errCode);
		this.setModel(model);
		this.modelType = modelType;
	}

	public Response(ResponseCode code, Object model, String modelType) {
		super();
		this.errCode = code.getCode();
		this.errDesc = code.getDesc();
		this.setModel(model);
		this.modelType = modelType;
	}

	private String getDescByCode(String errCode) {
		for (ResponseCode code : ResponseCode.values()) {
			if (code.getCode().equals(errCode)) {
				return code.getDesc();
			}
		}
		return "";
	}

	public Response(String errCode, Exception e) {
		this.errCode = errCode;
		if (e != null)
			this.errDesc = e.toString();
	}

	public Response(String errCode, String errDesc) {
		this.errCode = errCode;
		this.errDesc = errDesc;
	}

	public Response(ResponseCode code) {
		this.errCode = code.getCode();
		this.errDesc = code.getDesc();
	}

	public Response(ResponseCode unknown, Exception e) {
		this.errCode = unknown.getCode();
		this.errCode = e.toString();
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	@Override
	public String toString() {
		return "Response [errCode=" + errCode + ", errDesc=" + errDesc + "]";
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

}
