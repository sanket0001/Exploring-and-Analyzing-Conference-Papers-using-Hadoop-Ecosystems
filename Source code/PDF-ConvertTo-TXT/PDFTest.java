package pdftest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author patel
 */
public class PDFTest {

    
     
    public static void main(String[] args) throws IOException {
     
        // TODO code application logic here
        File folder = new File("E:\\4th sem\\Big Data\\Project\\release-2016-02-29-02-26-09\\output\\pdfs");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String paperId = listOfFiles[i].getName().substring(0, 4);
                 PDFManager pdfManager = new PDFManager();
                pdfManager.setFilePath("E:\\4th sem\\Big Data\\Project\\release-2016-02-29-02-26-09\\output\\pdfs\\"+listOfFiles[i].getName());
                String paper = listOfFiles[i].getName().substring(5);
                String temp = paper.replaceAll("[-]"," ");
                String title = temp.replace(".pdf", " ");
                
                System.out.println(pdfManager.ToText());
                pdfManager.writeTexttoFile(pdfManager.ToText(), "E:\\4th sem\\Big Data\\Project\\text files\\"+paperId+".txt",paperId);
               
            } else if (listOfFiles[i].isDirectory()) {
         System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
      
    
    }
    
}
