package com.home.project.media.mplayer.test.fileutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.home.project.media.mplayer.utils.FileUtils;

public class FileUtilsTest {
	private List<String> randomStrings;
	
	@Before
	public void warmUp(){
		randomStrings = new ArrayList<String>();
		randomStrings.add("raphael");
		randomStrings.add("natasha");
		randomStrings.add("adheli");
		randomStrings.add("kariny");
		randomStrings.add("andreza");
		randomStrings.add("thaina");
	}
	@Test
	public void verifyTempFileCreation() {
		FileUtils utils = new FileUtils();
		
		utils.createTemporaryFile(randomStrings);
		
		File tmp = utils.getTempFile();
		

		assertTrue(tmp.exists());

		utils.closeTempFile();

		assertFalse(tmp.exists());
	}
//	@Ignore
	@Test
	public void verifyDataOnFile() {
		FileUtils utils = new FileUtils();
		
		utils.createTemporaryFile(randomStrings);
		
		File tmp = utils.getTempFile();

		assertTrue(tmp.exists());

		if (tmp != null) {
			try {
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader(
						tmp.getAbsolutePath()));
				
				String line;
				int index = 0;
				while ((line = reader.readLine()) != null) {
					assertEquals(randomStrings.get(index), line);
					System.out.println(randomStrings.get(index));
					index++;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		utils.closeTempFile();

//		assertFalse(tmp.exists());
	}
}
