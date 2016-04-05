package com.example.myproject;

import java.io.*;

import javax.print.*;
import javax.print.attribute.*; 
import javax.print.attribute.standard.*; 
  
public class PrintTest{
  
   public static void main(String args[]){
	   final String LOC="C:/Users/rs14861/Desktop/java8_tutorial.pdf";
        FileInputStream psStream = null;
        try {
            psStream = new FileInputStream(LOC);
            } catch (FileNotFoundException ffne) {
              ffne.printStackTrace();
            }
            if (psStream == null) {
                return;
            }
            String defaultPrinter =
            	      PrintServiceLookup.lookupDefaultPrintService().getName();
            	    System.out.println("Default printer: " + defaultPrinter);

        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);  
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new Copies(1));
        PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
         
        // this step is necessary because I have several printers configured
        PrintService myPrinter = null;
        for (int i = 0; i < services.length; i++){
        	 String svcName = services[i].toString();   
			System.out.println("service found: "+svcName);
                   
            if (svcName.contains("printer closest to me")){
                myPrinter = services[i];
                System.out.println("my printer found: "+svcName);
                break;
            }
        }
         
        if (myPrinter != null) {            
            DocPrintJob job = myPrinter.createPrintJob();
            try {
            job.print(myDoc, aset);
             
            } catch (Exception pe) {pe.printStackTrace();}
        } else {
            System.out.println("no printer services found");
        }
   }
}