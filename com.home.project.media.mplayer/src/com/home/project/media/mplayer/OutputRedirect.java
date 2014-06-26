package com.home.project.media.mplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutputRedirect extends Thread {
	private InputStream in;
	private OutputStream out;
//	private String prefix;
	private Logger logger=Logger.getLogger(OutputRedirect.class.getName());

	public OutputRedirect(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
//		this.prefix=prefix;
	}

	public void run() {

		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			PrintStream printStream = new PrintStream(out);

			String line;

			while ((line = reader.readLine()) != null) {
				logger.info(line+"\n");
				printStream.println(line);
			}
		} catch (IOException e) {

			logger.log(Level.WARNING, "An error has occured while grabbing lines", e);
		}
	}
}
