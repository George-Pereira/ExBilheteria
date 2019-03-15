package view;

import java.util.concurrent.Semaphore;

import controller.ThreadsBilheteria;

public class Main 
{
	public static void main(String[] args) 
	{
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		for(int CTA = 1; CTA < 301; CTA++) 
		{
			Thread thread = new ThreadsBilheteria(CTA, false, false, semaforo);
			thread.start();
		}
	}
}
