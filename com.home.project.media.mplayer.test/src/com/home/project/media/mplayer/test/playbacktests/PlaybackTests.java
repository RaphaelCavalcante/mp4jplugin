package com.home.project.media.mplayer.test.playbacktests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.home.project.media.mplayer.Mplayer;
import com.home.project.media.mplayer.constants.Paths;

public class PlaybackTests {
	private final String AUDIO_SAMPLE = "audioResource/audio1.mp3";
	private final String VIDEO_SAMPLE = "videoResource/video1.mp4";
	private Mplayer player;

	@Before
	public void warmUp() {
		player = new Mplayer();

	}

	@Test
	public void verifyAudioResources() {
		File audioResource = new File(AUDIO_SAMPLE);

		assertTrue(audioResource.exists());

	}

	@Test
	public void verifyVideoResources() {

		File videoResource = new File(VIDEO_SAMPLE);

		assertTrue(videoResource.exists());
	}

	@Test
	public void verifyInstalation() {
		File mplayerBin = new File("/usr/bin/mplayer");

		assertTrue(mplayerBin.exists());

	}

	@Test
	public void verifyConstants() {
		assertTrue(Paths.getMplayerPath().equals("/usr/bin/mplayer"));
	}

	@Test
	public void startMplayerOneFileList() {
		List<String> files = new ArrayList<String>();
		files.add(AUDIO_SAMPLE);

		// System.out.println(files.size());
		try {

			player.openFiles(files);

			Thread.sleep(3000);

			player.close();

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void startMplayerMultipleFiles() {
		List<String> files = new ArrayList<String>();
		files.add("../" + AUDIO_SAMPLE);
		files.add("../" + VIDEO_SAMPLE);

		System.out.println();

		try {

			player.openFiles(files);

			Thread.sleep(3000);

			player.close();

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void mplayerBehaviorNext() {
		List<String> files = new ArrayList<String>();
		files.add("../" + AUDIO_SAMPLE);
		files.add("../" + VIDEO_SAMPLE);

		try {

			player.openFiles(files);

			Thread.sleep(3000);

			player.nexTrack();

			Thread.sleep(3000);

			player.close();

			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
