package ro.utcn.pt.QueueSimulator.utilities;

import java.util.Random;

public class RandomGenerator {

	public static int getRandomIntInRange(int min, int max){
		return min + (new Random()).nextInt(max- min + 1);
	}
	
	public static long getRandomLongInRange(long min, long max){
		return  min + (new Random()).nextInt((int) (max- min + 1));
	}
}
