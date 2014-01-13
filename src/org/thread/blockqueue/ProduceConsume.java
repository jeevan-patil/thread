package org.thread.blockqueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author jeevan
 * @date 11-Jan-2014 12:13:12 PM
 *
 */
public class ProduceConsume {
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(15);

	public static void main(String a[]) {
		final ProduceConsume pc = new ProduceConsume();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				pc.produce();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				pc.consume();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch(Exception e) {
			
		}
	}
	
	private void produce() {
		Random random = new Random();
		
		while(true) {
			try {
				queue.put(random.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void consume() {
		Random random = new Random();
		
		while(true) {
			
			try {
				Thread.sleep(100);
			} catch(Exception e) {}
			
			if(random.nextInt(20) == 11) {
				try {
					Integer value = queue.take();
					System.out.println("Value is: " + value + " and queue size is: " + queue.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
