package com.jdc.single;

public class SingleObject {
	
	private static SingleObject obj;
	
	private SingleObject() {}
	
	public static SingleObject getInstance() {
		if(null == obj)
			obj = new SingleObject();
		
		return obj;
	}

}
