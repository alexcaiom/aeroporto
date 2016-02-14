package com.mb;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ColumnResizeEvent;

public class AbstractMB {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractMB() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
	/*	JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);*/
		addMensagem(message, FacesMessage.SEVERITY_ERROR);
	}
	
	private void addMensagem(String message, Severity severidade) {
		if (severidade == null) {
			severidade = FacesMessage.SEVERITY_INFO;
		}
		FacesMessage mensagem = new FacesMessage(severidade, message, null);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	protected void displayInfoMessageToUser(String message) {
		/*JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);*/
		addMensagem(message, FacesMessage.SEVERITY_INFO);
	}
	
	protected void closeDialog(){
		FacesContext.getCurrentInstance().getExternalContext().addResponseHeader(KEEP_DIALOG_OPENED, "false");
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
	
	protected void addToSessao (String chave, Object o){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		request.getSession().setAttribute(chave, o);
	}
	
	protected Object getFromSessao (String chave){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		return request.getSession().getAttribute(chave);
	}
	
	protected void removeDaSessao (String chave) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		request.getSession().removeAttribute(chave);
	}
}