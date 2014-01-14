package org.thread.lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author jeevan
 * @date 13-Jan-2014 11:06:35 PM
 * 
 */
public class LockRunner {
	private Lock lock = new ReentrantLock();
	final Condition cond = lock.newCondition();
	private int cnt = 0;

	public void increment() {
		for (int i = 0; i < 20000; i++)
			++cnt;
	}

	public void firstThread() throws InterruptedException {
		lock.lock();

		System.out.println("First is waiting ... ");
		cond.await();
		System.out.println("First is awaken!");

		try {
			increment();
		} finally {
			lock.unlock();
		}
	}

	public void secondThread() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();

		System.out.println("Press the return key!");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");
		cond.signal();

		try {
			increment();
		} finally {
			lock.unlock();			
		}
	}

	public void done() {
		System.out.println("Count is: " + cnt);
	}
}
