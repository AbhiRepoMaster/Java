package com.example.bookacar.datafetch;

public interface BaseDetails {
	//BaseUrl Link
	String BaseUrlWindowLnk = "https://leafground.com/window.xhtml";
	String BaseUrlFrameLnk = "https://leafground.com/frame.xhtml";
	String BaseUrlAlertLnk = "https://leafground.com/alert.xhtml";
	

	//Windows class xpaths
	String NewWindowButton = "j_idt88:new";
	String MultipleButton = "//div[@class = 'grid button-demo']//button[@id='j_idt88:j_idt91']";
	String MultipleButtonCloseWindow = "//div[@class = 'grid button-demo']//button[@id='j_idt88:j_idt93']";
	String clickTestTwoNewTabToOpen ="//div[@class = 'grid button-demo']//button[@id='j_idt88:j_idt95']";
	
	
	//Alert Xpath
	String SimpleAleretButton ="//button[@id='j_idt88:j_idt91']";
	String ConfirmDialogButton= "//button[@id='j_idt88:j_idt93']";
	String SweetAlertButton= "//button[@id='j_idt88:j_idt95']";
	String SweettButton= "ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top ui-draggable-handle";
	String TestAlert = "j_idt88:j_idt96_title";
	String SweetModalDialogBTN = "//button[@id='j_idt88:j_idt100']";
	String SweetModal = "//div[@aria-labelledby='j_idt88:j_idt101_title']";
	String ModalText = "//span[@id = 'j_idt88:j_idt101_title']";   
	String CloseButton = "//div[(@id = 'j_idt88:j_idt101')] //span[@class ='ui-icon ui-icon-closethick']";
	String DismissBTN = "//span[contains(text(), 'Dismiss')]";
	String SweetAlertDLg = "//div[@id='j_idt88:j_idt96']";
	String SweetAlertTxt = "//span[@id='j_idt88:j_idt96_title']";
	
	String PromplDialogBTN = "//button[@id='j_idt88:j_idt100']";
	String PromplDialogTxt = "//div[@aria-labelledby='j_idt88:j_idt101_title']";
	String PromplcloseButton = "//div[(@id = 'j_idt88:j_idt101')] //span[@class ='ui-icon ui-icon-closethick']";
	
	String SweetAlertConfirmationBTN = "//button[(@id = 'j_idt88:j_idt104')]"; 

	String MinimizeAndMaximizeBTN = "//button[@id='j_idt88:j_idt111']"; 
	String MinimizeBTN = "//span[@class = 'ui-icon ui-icon-minus']"; 
	String MaximizeBTN = "//span[@class = 'ui-icon ui-icon-extlink']"; 

	
}
