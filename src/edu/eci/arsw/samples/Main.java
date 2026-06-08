package edu.eci.arsw.samples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {
		int numHilos=20;
		CyclicBarrier barrier = new CyclicBarrier(numHilos+1);
		
		HiloProc[] hilos=new HiloProc[numHilos];
		
		for (int i=0;i<numHilos;i++){
			hilos[i]=new HiloProc(i,barrier);
		}
		for (int i=0;i<numHilos;i++){
			hilos[i].start();
		}
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        long tiempoPromedio=0;
		
		for (int i=0;i<numHilos;i++){
			tiempoPromedio+=hilos[i].getResultado();
		}

		System.out.println("El tiempo promedio de la ejecución fue de:"+tiempoPromedio/numHilos);
	}
	
}
