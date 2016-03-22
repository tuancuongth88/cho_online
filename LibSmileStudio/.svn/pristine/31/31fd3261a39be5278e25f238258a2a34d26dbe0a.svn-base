package com.lib.java.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReadWriteFile {
	final String tag = "TAG";

	public String readFileStringAssest(InputStream input, String whereline[]) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(input);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			if (Arrays.binarySearch(whereline, line) >= 0)
				continue;
			buffer.append(line);
		}
		return buffer.toString();
	}

	public String readFileStringAssest(InputStream input) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(input);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}
