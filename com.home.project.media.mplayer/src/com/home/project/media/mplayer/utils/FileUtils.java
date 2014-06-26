package com.home.project.media.mplayer.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtils {
	private BufferedWriter writer;
	private File temp;
	
	
	
	

	public void createTemporaryFile(List<String> files) {
		try {
			
			temp = File.createTempFile("tempFile", ".txt", new File("tmp/"));
			
			writer = new BufferedWriter(new FileWriter(temp));

			for (String tmp : files) {
				writer.write(tmp + "\n");
				writer.flush();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	public void closeTempFile(){
		try {
			writer.close();
			temp.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public File getTempFile(){
		return temp;
	}
}
