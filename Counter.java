public class Counter extends Thread {

	@Override
	public void run() {
		for (int i = 0; i <= 1000 && Boldness_1.getInstance().shouldCurrentThreadBeRunning(); i++) {
			System.out.println(i);
			try{
			Thread.sleep(10);
		} catch (Exception ex) {
			System.out.println("ERROR: I'm a train, can't stop me");
		};
		}

		Boldness_1.getInstance().done();
	}
}