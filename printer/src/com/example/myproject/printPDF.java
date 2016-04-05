package com.example.myproject;


import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class printPDF {
  static public void main(String args[]) throws Exception {
	  final String LOC="C:/Users/rs14861/Desktop/java8_tutorial.pdf";
	 // String mimeType="application/pdf";
	  
    try {
      PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
      pras.add(new Copies(1));
     

      PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.PDF, pras);

      if (pss.length == 0)
        throw new RuntimeException("No printer services available.");

      PrintService ps = pss[0];
      System.out.println("Printing to " + ps);

      DocPrintJob job = ps.createPrintJob();

      FileInputStream fin = new FileInputStream(LOC);
      Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PDF, null);
 
      job.print(doc, pras);

      fin.close();
    } catch (IOException ie) {
      ie.printStackTrace();
    } catch (PrintException pe) {
      pe.printStackTrace();
    }
  }
}

