package com.jdc.proto;

public class PrototypeApp {

	public static void main(String[] args) {
		
		ProtoObject obj1 = new ProtoObject();
		ProtoObject obj2 = obj1.copy();
		
		System.out.println(obj1);
		System.out.println(obj2);
		
	}

}
