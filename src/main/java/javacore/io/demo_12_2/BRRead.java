package javacore.io.demo_12_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRRead {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		char c;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter characters , 'q' to quit.");
		
		//read characters
		do{
			c = (char)br.read();
		}while(c != 'q');
	}
	
	//System.in默认情况下是以行来缓冲的，这意味着在按下Enter之前，是没有输入的。这不能充分体现交互式控制台输入read的价值。
}
