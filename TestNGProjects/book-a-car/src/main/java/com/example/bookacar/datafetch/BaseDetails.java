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
}
