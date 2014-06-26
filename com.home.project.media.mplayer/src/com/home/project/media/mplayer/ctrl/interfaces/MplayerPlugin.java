package com.home.project.media.mplayer.ctrl.interfaces;

import java.util.List;

import net.xeoh.plugins.base.Plugin;

import com.home.project.media.mplayer.Mplayer;

public interface MplayerPlugin extends Plugin {
	public Mplayer getMplayerInstace();
	public void togglePlay();
	public void nextTrack();
	public void previousTrack();
	public void close();
	public void open(List<String> files);
	
}
