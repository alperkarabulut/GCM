package com.mbis.gcm.service;

import org.apache.log4j.BasicConfigurator;

public class SendMessageToApple {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		Device dev = new Device();
		dev.SendMessageToApple("e9aa728d226d96e40d363cccaeabbdbba4d90d1240b119b35f53090a091a5449", "test", false);
	}

}