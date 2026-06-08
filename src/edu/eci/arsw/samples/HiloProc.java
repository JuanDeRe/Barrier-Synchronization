package edu.eci.arsw.samples;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class HiloProc extends Thread{

	int waitPeriod;
	int idHilo;
	long resultado=0;
	CyclicBarrier barrier;
	
	public HiloProc(int id, CyclicBarrier barrier){
		this.barrier = barrier;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitPeriod=Math.abs(new Random(System.currentTimeMillis()).nextInt()%5000);
		idHilo=id;
	}
	
	public void run(){
		int numit=10;
		long startTime=System.currentTimeMillis();
		for (int i=0;i<numit;i++){
			System.out.println("Soy el hilo "+idHilo+" y voy en el "+(((float)(i+1)/(float)numit)*100)+"% de mi tarea. P:"+waitPeriod);
			try {
				Thread.sleep(waitPeriod);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
        resultado=System.currentTimeMillis()-startTime;
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
	}
	public long getResultado() {
		return resultado;
	}
}
