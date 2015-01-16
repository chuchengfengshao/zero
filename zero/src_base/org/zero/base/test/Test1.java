package org.zero.base.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Test1 test = new Test1();
//		test.test2();
		final IdWorker w = new IdWorker(1, 1);
		System.out.println(w.nextId());
//		for(int i=0;i<100;i++){
//			UUID uuid = UUID.randomUUID();
//			String id = uuid.toString().replace("-","");
//			//System.out.println(id);
//			byte[] b = id.getBytes();
//			System.out.println(b.length);
//			System.out.println(ByteConverter.byteArrayToInteger(b, 0, 32));
//		}
		
	}

	public void test2() {
		final IdWorker w = new IdWorker(1, 2);
		final CyclicBarrier cdl = new CyclicBarrier(100);

		for (int i = 0; i < 100000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cdl.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println(w.nextId());
				}
			}).start();
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
