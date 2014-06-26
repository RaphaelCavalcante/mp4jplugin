package com.home.project.core.unit.test;

import org.junit.Before;
import org.junit.Test;

import com.home.project.core.utils.FileUtils;

public class ListFilesTest {
	private FileUtils util;
	@Before
	public void warmUp(){
		util=new FileUtils();
	}
	@Test
	public void ViewListedVideoFiles() {
		System.out.println("Listando arquivos de video");	
		System.out.println(util.listVideoFiles());
	}
	@Test
	public void ViewListedMusicFiles() {
		System.out.println("Listando arquivos de musicas");
		
		System.out.println(util.listMusicFiles());
	}
	@Test
	public void ViewListedMusicDirs(){
		System.out.println("Listando diretorios de musica");
		System.out.println(util.listMusicDirectories());
	}
	@Test
	public void ViewListedVideoDirs(){
		System.out.println("Listando diretorios de video");
		System.out.println(util.listVideoDirectories());
	}

}
