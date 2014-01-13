package org.thread.waitnotify;

import java.util.LinkedList;

/**
 * 
 * @author jeevan
 * @date 11-Jan-2014 12:49:08 PM
 * @purpose Demonstrate producer-consumer pattern using traditional wait notify methods, without using any concurrent packages.
 */
public class ProcNumbers {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	private int value = 0;

	public void produce() throws InterruptedException {
		while(true) {
			synchronized (lock) {
				while(list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
		}		
	}

	public void consume() throws InterruptedException {
		while(true) {
			synchronized (lock) {
				while(list.size() == 0) {
					lock.wait();
				}
				System.out.print("List size was: " + list.size());
				int val = list.removeFirst();
				System.out.println("; Removed value is : " + val);
				lock.notify();
			}

			Thread.sleep(2000);
		}
	}
}
