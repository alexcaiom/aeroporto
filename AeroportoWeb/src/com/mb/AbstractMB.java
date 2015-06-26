package com.mb;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ColumnResizeEvent;

import com.util.JSFMessageUtil;

public class AbstractMB {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractMB() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}
	
	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}
	
	protected void closeDialog(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}
	
	protected void keepDialogOpen(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}
	
	public void onResize(ColumnResizeEvent event) {  
        FacesMessage msg = new FacesMessage("Column " + event.getColumn().getClientId() + " resized", "W:" + event.getWidth() + ", H:" + event.getHeight());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    } 
	
	protected RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}
}