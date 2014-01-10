package org.thread.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author jeevan
 *
 */
class Processor implements Runnable {
	private int id;

	public Processor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("starting  ... " + id);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("completed ... " + id);
	}
}

public class ProcPool {

	public static void main(String a[]) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i = 1; i <= 5; i++) {
			executor.submit(new Processor(i));
		}
		
		executor.shutdown();
		
		System.out.println("All the tasks submitted ... ");
		
		try {
			executor.awaitTermination(3, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed ... ");
	}
}
