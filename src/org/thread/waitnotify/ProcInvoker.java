package org.thread.waitnotify;

/**
 * 
 * @author jeevan
 * @date 11-Jan-2014 12:35:31 PM
 *
 */
public class ProcInvoker {

	public static void main(String a[]) {
		final ProcNumbers proc = new ProcNumbers();

		Thread producerThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					proc.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread consumerThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					proc.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		producerThread.start();
		consumerThread.start();
		
		try {
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
