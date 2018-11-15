package com.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.albany.foodOnWheels.constants.Constant;

public class ImageUpload {

private static String getFileName(final Part part) {
	   
	    
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	
	public static String imageUpload(HttpServletRequest request) throws ServletException, java.io.IOException {
		
		final String path = Constant.file_upload; // CHANGE this to your path
	    
		final Part filePart = request.getPart("file");
	    final String fileName = getFileName(filePart);
	    
	    OutputStream out = null;
	    InputStream filecontent = null;
	    
	    try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        System.out.println("New file " + fileName + " created at " + path);
	        
	    } catch (FileNotFoundException fne) {
	    	System.out.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        

	        
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	       
	    }
	    return fileName;
	}
	
}
