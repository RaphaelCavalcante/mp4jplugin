package com.home.project.core.constants;

public class Directories {
	private static String VIDEO_DIR="~/videos";
	private static String MUSIC_DIR="~/musicas";
	
	public static String getVideoDir(){
		return VIDEO_DIR;
	}
	public static String getMusicDir(){
		return MUSIC_DIR;
	}
	public static void setVideoDir(String directory){
		VIDEO_DIR=directory;
	}
	public static void setMusicDir(String directory){
		MUSIC_DIR=directory;
	}
}
