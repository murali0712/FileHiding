package service;

import java.util.Random;

public class GenerateOTP {
	public static String getOTP() {
		Random ran= new Random();
		return String.format("%04d",ran.nextInt(10000));
	}
}
