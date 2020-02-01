package com.vntofu.fastfood.test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class Test {
	public static void main(String[] args) {
		String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXNzYWdlIjoiSldUIFJ1bGVzISIsImlhdCI6MTQ1OTQ0ODExOSwiZXhwIjoxNDU5NDU0NTE5fQ.-yIVBD5b73C75osbmwwshQNRC7frWUYrqaTjTpza2y4";
		System.out.println(token.length());
		String customerName = "Zoey";
		StringBuilder sb = new StringBuilder();
		for(char c: customerName.toCharArray()) {
			sb.append(c + "");
			sb.append(token.charAt((int) (Math.random()*155) + 1));
		}
		System.out.println("Session Number: " + sb);
		List<Integer> list = new ArrayList<Integer>();
		
		
	}
}
