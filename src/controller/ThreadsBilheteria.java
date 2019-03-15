package controller;

import java.util.concurrent.Semaphore;

import java.util.Random;

public class ThreadsBilheteria extends Thread 
{
	private int idCliente;
	private boolean login;
	private boolean compra;
	private static int ingressos = 100;
	private Semaphore semaforo;
	public ThreadsBilheteria(int idCliente, boolean login, boolean compra, Semaphore semaforo) 
	{
		this.idCliente = idCliente;
		this.login = login;
		this.compra = compra;
		this.semaforo = semaforo;
	}
	@Override
	public void run() 
	{
		efetuarLogin();
		if(login == true) 
		{
			efetuarCompra();
			if(compra == true) 
			{
				try 
				{
					semaforo.acquire();
					validarCompra();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				finally 
				{
					semaforo.release();
				}
			}
		}
	}
	private void validarCompra() 
	{
		Random R = new Random();
		int ingressosSelecionados = (R.nextInt(4)+1);
		try 
		{
			Thread.sleep(100);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		if(ingressosSelecionados <= ingressos) 
		{
			ingressos -= ingressosSelecionados;
			System.out.println("O cliente " + idCliente + " conseguiu comprar " + ingressosSelecionados + " ingresso(s) para o show!");
		}
		else 
		{
			System.out.println("O cliente " + idCliente + " não conseguiu comprar seus ingressos e foi desconectado");
		}
		System.out.println("Ingressos restantes = " + ingressos);
	}
	private void efetuarCompra() 
	{
		int tempoCompra = (int)((Math.random() * 2001) + 1000);
		try 
		{
			Thread.sleep(100);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		if(tempoCompra > 2500) 
		{
			System.out.println("O cliente " + idCliente + " não conseguiu efetuar sua compra a tempo e foi desconectado");
		}
		else 
		{
			System.out.println("O cliente " + idCliente + " efetuou o processo de compra");
			compra = true;
		}
	}
	private void efetuarLogin() 
	{
		int tempoLogin = (int)((Math.random() * 1951) + 50);
		try 
		{
			Thread.sleep(100);
		}
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
		if(tempoLogin > 1000) 
		{
			System.out.println("O cliente " + idCliente + " não conseguiu fazer login a tempo e foi desconectado");
		}
		else 
		{
			System.out.println("O cliente " + idCliente + " efetuou o login");
			login = true;
		}
	}
}
