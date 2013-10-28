package com.idocs.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;

public class FileIntersectionProducerDefault implements
		FileIntersectionProducer {

	private WriterProvider writerProvider;

	public FileIntersectionProducerDefault() {
		writerProvider = new WriterProvider();
	}

	public void createIntersection(String inputFileName1,
			String inputFileName2, String outputFileName) {
		Set<String> data1 = getSortedData(inputFileName1);
		Set<String> data2 = getSortedData(inputFileName2);
		Set<String> intersection;
		// slightly better performance if smaller set is first
		if (data1.size() > data2.size()) {
			intersection = Sets.intersection(data2, data1);
		} else {
			intersection = Sets.intersection(data1, data2);
		}
		flushIntersection(outputFileName, intersection);
	}

	private void flushIntersection(String outputFile, Set<String> data) {
		try {
			if(data.isEmpty()){
				File f = new File(outputFile);
				f.createNewFile();
				return;
			}
			BufferedWriter out = writerProvider
					.getBufferedFileWriter(outputFile);
			Iterator<String> it = data.iterator();
			while (it.hasNext()) {
				out.write(it.next() + "\n");
			}
			out.close();
		} catch (Exception e) {
			throw new FileIntersectionProducerException(
					"There was an error while writting output " + outputFile);
		}
	}

	private Set<String> getSortedData(String filename) {
		try {
			Set<String> data = Sets.newTreeSet();
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
			br.close();
			return data;
		} catch (FileNotFoundException e) {
			throw new FileIntersectionProducerException("File not found "
					+ filename);
		} catch (IOException e) {
			throw new FileIntersectionProducerException("Error reading file "
					+ filename);

		}
	}

	protected class WriterProvider {
		public BufferedWriter getBufferedFileWriter(String filename)
				throws IOException {
			return new BufferedWriter(new FileWriter(filename));
		}
	}
	
	@VisibleForTesting
	protected void setWriterProvider(WriterProvider writerProvider){
		this.writerProvider = writerProvider;
	}
}
