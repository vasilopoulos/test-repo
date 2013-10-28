package com.idocs.test;

import java.io.File;

import com.google.common.base.Throwables;

public class FileIntersectionApp {

	private final FileIntersectionProducerFactory producerFactory;

	public FileIntersectionApp(FileIntersectionProducerFactory producerFactory) {
		this.producerFactory = producerFactory;
	}

	private static String usage() {
		return "Please provide 2 input and 1 output filenames";
	}

	private static boolean fileExists(String file) {
		File f = new File(file);
		return f.exists();
	}

	private static void printMessage(String msg) {
		System.err.println(msg);
	}

	public static void main(String[] args) {
		if (args == null || args.length != 3) {
			printMessage(usage());
		}

		String filename1 = args[0];
		String filename2 = args[1];
		String output = args[2];
		if (!fileExists(filename1) || !fileExists(filename2)) {
			printMessage("Cannot find both input files.");
			System.exit(1);

		}

		FileIntersectionApp app = new FileIntersectionApp(
				new FileIntersectionProducerFactory());
		FileIntersectionProducer defaultProducer = app.producerFactory
				.getDefaultProducer();

		try {
			defaultProducer.createIntersection(filename1, filename2, output);
		} catch (Exception e) {
			printMessage("There was an error while processing "
					+ Throwables.getStackTraceAsString(e));
		}
	}
}
