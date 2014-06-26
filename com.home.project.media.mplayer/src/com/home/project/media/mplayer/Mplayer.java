package com.home.project.media.mplayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;

import com.home.project.media.mplayer.constants.Commands;
import com.home.project.media.mplayer.constants.Paths;
import com.home.project.media.mplayer.utils.FileUtils;

public class Mplayer {
	// private FileUtils utils;
	private Logger logger = Logger.getLogger(Mplayer.class.getName());
	private FileUtils utils;
	private File tmpPlaylist;
	// path pro player
	// commando de slave

	private Process mplayerProcess;
	private PrintStream mplayerIn;

	private BufferedReader mplayerOutErr;

	public Mplayer() {
	}

	public void openFiles(List<String> files){
		
		StringBuilder command = new StringBuilder();
		utils = new FileUtils();
		command.append(Paths.getMplayerPath());
		command.append(Commands.SLAVE_MODE);
		if (files.size() > 1) {

			// start MPlayer as an external process
			// String command =Paths.getMplayerPath() +Commands.SLAVE_MODE +
			// path ;
			utils.createTemporaryFile(files);
			
			
			tmpPlaylist = utils.getTempFile();//utils.createTemporaryFile(files);
			
			command.append(Commands.PLAYLIST);
			
			command.append(tmpPlaylist.getAbsoluteFile());
		} else {
			command.append(files.get(0));
		}
//		System.out.println(command.substring(0));
		startMplayer(command.substring(0));
	}

	public void startMplayer(String command) {

		if (mplayerProcess == null) {

			logger.info("Starting MPlayer process: " + command);

			try {
				mplayerProcess = Runtime.getRuntime()
						.exec(command.substring(0));

				// create the piped streams where to redirect the standard
				// output
				// and error of MPlayer
				// specify a bigger pipesize

				PipedInputStream readFrom = new PipedInputStream(1024 * 1024);
				PipedOutputStream writeTo;

				writeTo = new PipedOutputStream(readFrom);

				mplayerOutErr = new BufferedReader(new InputStreamReader(
						readFrom));

				// create the threads to redirect the standard output and error
				// of
				// MPlayer
				new OutputRedirect(mplayerProcess.getInputStream(), writeTo).start();
				new OutputRedirect(mplayerProcess.getErrorStream(), writeTo).start();

				// the standard input of MPlayer
				mplayerIn = new PrintStream(mplayerProcess.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// wait to start playing
		// waitForAnswer("Starting playback...");
		// logger.info("Started playing file " + path);
	}

	public void close() {
		if (mplayerProcess != null) {
			execute("quit");
			if (tmpPlaylist != null) {
				utils.closeTempFile();
			}
			try {
				mplayerProcess.waitFor();
			} catch (InterruptedException e) {
			}
			mplayerProcess = null;
		}
	}

	public void togglePlay() {
		execute("pause");
	}

	public void nexTrack() {
		execute("pt_step 1");
	}

	public void previousTrack() {
		execute("pt_step -1");
	}

	/**
	 * Sends a command to MPlayer..
	 * 
	 * @param command
	 *            the command to be sent
	 */
	private void execute(String command) {
		execute(command, null);
	}

	/**
	 * Sends a command to MPlayer and waits for an answer.
	 * 
	 * @param command
	 *            the command to be sent
	 * @param expected
	 *            the string with which has to start the line; if null don't
	 *            wait for an answer
	 * @return the MPlayer answer
	 */
	private String execute(String command, String expected) {
		if (mplayerProcess != null) {
			logger.info("Send to MPlayer the command \"" + command
					+ "\" and expecting "
					+ (expected != null ? "\"" + expected + "\"" : "no answer"));

			mplayerIn.print(command);

			mplayerIn.print("\n");

			mplayerIn.flush();

			logger.info("Command sent");
			if (expected != null) {
				String response = waitForAnswer(expected);
				logger.info("MPlayer command response: " + response);
				return response;
			}
		}
		return null;
	}

	/**
	 * Read from the MPlayer standard output and error a line that starts with
	 * the given parameter and return it.
	 * 
	 * @param expected
	 *            the expected starting string for the line
	 * @return the entire line from the standard output or error of MPlayer
	 */
	private String waitForAnswer(String expected) {
		// todo add the possibility to specify more options to be specified
		// todo use regexp matching instead of the beginning of a string
		String line = null;
		if (expected != null) {
			try {
				while ((line = mplayerOutErr.readLine()) != null) {
					logger.info("Reading line: " + line);
					if (line.startsWith(expected)) {
						return line;
					}
				}
			} catch (IOException e) {
			}
		}
		return line;
	}
}
