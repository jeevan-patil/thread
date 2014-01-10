package org.thread.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author jeevan
 *
 */
public class Processor {
	private Random random = new Random();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private List<Integer> serverA = new ArrayList<Integer>();
	private List<Integer> serverB = new ArrayList<Integer>();

	private void pingServerA() {
		synchronized (lock1) {
			// simulate pinging a server by sleeping for some time
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			serverA.add(random.nextInt(500));
		}		
	}

	private void pingServerB() {
		synchronized (lock2) {
			// simulate pinging a server by sleeping for some time
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			serverB.add(random.nextInt(500));
		}		
	}

	private void processServers() {
		for(int i = 0; i < 1000; i++) {
			pingServerA();
			pingServerB();			
		}		
	}

	public void invodeProcess() {
		System.out.println("Starting server check ... ");
		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {			
				processServers();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {			
				processServers();
			}
		});
		
		t1.start();
		t2.start();

		try {		
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("Time taken : " + (end - start));
		System.out.println("server A : " + serverA.size());
		System.out.println("server B : " + serverB.size());
		
	}

}
