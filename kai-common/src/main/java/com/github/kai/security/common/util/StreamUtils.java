package com.github.kai.security.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TODO: 数据流操作工具类
 *
 * Author: kai
 * CreateDate: 2017/9/17
 * CreateTime: 12:29
 */
public class StreamUtils {

	public static void write(InputStream input, OutputStream output) throws IOException {
		try {
			int ch = 0;
			while ((ch = input.read()) != -1) {
				output.write(ch);
			}

		} catch (IOException e) {
			throw e;
		}
	}

	public static void writeAndClose(InputStream input, OutputStream output) throws IOException {
		try {
			int ch = 0;
			while ((ch = input.read()) != -1) {
				output.write(ch);
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (output != null) {
				output.close();
			}

			if (input != null) {
				input.close();
			}
		}
	}

	public static void close(InputStream input, OutputStream output) throws IOException {
		if (output != null) {
			output.close();
		}

		if (input != null) {
			input.close();
		}
	}

	public static void close(InputStream input) throws IOException {

		if (input != null) {
			input.close();
		}
	}

}
