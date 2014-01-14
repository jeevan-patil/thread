package org.thread.lock;


/**
 * 
 * @author jeevan
 * @date 13-Jan-2014 11:06:19 PM
 *
 */
public class AppLock {
	public static void main(String a[]) {
		final LockRunner lockRunner = new LockRunner();

		Thread first = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					lockRunner.firstThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread second = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					lockRunner.secondThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		first.start();
		second.start();
		
		try {
			first.join();
			second.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lockRunner.done();
	}
}
