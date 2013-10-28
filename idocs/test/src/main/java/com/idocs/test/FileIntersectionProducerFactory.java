package com.idocs.test;

public class FileIntersectionProducerFactory {

	public FileIntersectionProducer getDefaultProducer(){
		return new FileIntersectionProducerDefault();
	}
}
