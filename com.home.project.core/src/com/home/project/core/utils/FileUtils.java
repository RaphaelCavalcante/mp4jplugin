package com.home.project.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.home.project.core.constants.Directories;

public class FileUtils {
	private String dir;
	
	
	public FileUtils(){}
	/*
	 * Retorna os arquivos de video no diretório
	 */
	public List<String> listVideoFiles() {

		dir = Directories.getVideoDir();
		return listFiles(dir,false);

	}
	/*
	 * Retorna os arquivos de audio no diretório
	 */
	public List<String> listMusicFiles(){

		dir= Directories.getMusicDir();
		return listFiles(dir,false);
	}
	/*
	 * Retorna apenas os diretorios contidos em Videos
	 */
	public List<String> listMusicDirectories(){
		dir= Directories.getMusicDir();
		return listFiles(dir, true);
	}
	/*
	 * Retorna apenas os diretorios contidos em Music
	 */
	public List<String> listVideoDirectories(){
		dir = Directories.getVideoDir();
		return listFiles(dir, true);
	}
	
	public List<String> listAllVideo(){
		return listRespectiveDir(Directories.getVideoDir());
	}
	
	public List<String> listAllMusic(){
		return listRespectiveDir(Directories.getMusicDir());
	}
	
	public List<String> listRespectiveDir(String dir){
		List<String>aux;
		aux=listFiles(dir, true);
		aux.addAll(listFiles(dir,false));
		return aux;
	}
	/**
	 * Metodo para listar os arquivos ou diretorios
	 * 
	 * @param dir - diretorio onde que será verificado
	 * @param onlyDirFlag - flag que indica se apenas diretorios serao listados
	 * @return - uma lista com os arquivos ou diretorios contidos em Dir
	 */
	private List<String> listFiles(String dir, boolean onlyDirFlag){
		File file;
		file = new File(dir);
		
		List<String> list = new ArrayList<String>();

		if (file != null) {
			for (File fp : file.listFiles()) {
				if(onlyDirFlag && fp.isDirectory()){
					list.add(fp.getName());
				}else if(!onlyDirFlag && !fp.isDirectory()){
					list.add(fp.getName());
				}
			}
		}
		return list;
	}
	
	public File createTemporaryFile(List<String>files){
		File temp=null;
		try {
			temp = File.createTempFile("tempFile", ".txt");
			BufferedWriter writer=new BufferedWriter(new FileWriter(temp));
			
			for(String tmp:files){
				writer.write(tmp+"\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
}
