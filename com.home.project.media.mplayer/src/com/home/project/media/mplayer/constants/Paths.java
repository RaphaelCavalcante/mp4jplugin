package com.home.project.media.mplayer.constants;

/**
 * 
 * @author raphael
 *
 * Classe que contem paths para o mplayer, considerando
 * paths padrao.
 * 
 */


public class Paths {
	private static String MPLAYER_PATH="/usr/bin/mplayer";
	
	
	
	public static void setPath(String newPath){
		MPLAYER_PATH=newPath;
	}
	public static String getMplayerPath(){
		return MPLAYER_PATH;
	}
}
