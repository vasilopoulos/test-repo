package com.idocs.test;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedWriter;
import java.io.IOException;

import org.junit.Test;

import com.idocs.test.FileIntersectionProducerDefault.WriterProvider;

public class FileIntersectionProducerDefaultTest {

	private static String path = "\\src\\test\\java\\com\\idocs\\test";

	@Test
	public void shouldWriteIntersection() throws IOException {
		FileIntersectionProducerDefault producer = new FileIntersectionProducerDefault();
		WriterProvider writer = mock(WriterProvider.class);
		BufferedWriter br = mock(BufferedWriter.class);
		when(writer.getBufferedFileWriter(isA(String.class))).thenReturn(br);
		producer.setWriterProvider(writer);
		producer.createIntersection(path + "file1.txt", path + "file2.txt",
				"out");
		verify(br).write("beta\n");
		verify(br).write("gamma\n");
	}

	@Test
	public void shouldHandleEmpty() throws IOException {
		FileIntersectionProducerDefault producer = new FileIntersectionProducerDefault();
		WriterProvider writer = mock(WriterProvider.class);
		BufferedWriter br = mock(BufferedWriter.class);
		when(writer.getBufferedFileWriter(isA(String.class))).thenReturn(br);
		producer.setWriterProvider(writer);
		producer.createIntersection(path + "file1.txt", path + "emptyFile.txt",
				"out");
		verifyZeroInteractions(br);

	}

}
