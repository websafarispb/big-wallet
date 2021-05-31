package ru.stepev.bigwallet.model;


class Test extends Thread {
	Test(){
		
	}
	
	Test(Runnable r){
		super(r);
	}
	
//	public void run() {
//		System.out.print("Inside Thread");
//	}
}

class RunnableDemo implements Runnable {

	@Override
	public void run() {
		System.out.print("Inside runnable");
	}

}

public class Main {

	public static void main(String[] args) {
		new Test().start();
		new Test(new RunnableDemo()).start();
		Thread t = new Thread(new RunnableDemo());
		t.start();

	}
}
