package com.home.project.media.mplayer.ctrl.impl;

import java.util.List;

import net.xeoh.plugins.base.annotations.PluginImplementation;

import com.home.project.media.mplayer.Mplayer;
import com.home.project.media.mplayer.ctrl.interfaces.MplayerPlugin;

@PluginImplementation
public class MplayerPluginImpl implements MplayerPlugin {
	private Mplayer player;
	public MplayerPluginImpl(){
		player=new Mplayer();
	}
	@Override
	public Mplayer getMplayerInstace() {
		return player;
	}

	@Override
	public void togglePlay() {
		player.togglePlay();
	}

	@Override
	public void nextTrack() {
		player.nexTrack();
	}

	@Override
	public void previousTrack() {
		player.previousTrack();
	}

	@Override
	public void close() {
		player.close();
	}

	@Override
	public void open(List<String> files) {
		player.openFiles(files);
	}

}
