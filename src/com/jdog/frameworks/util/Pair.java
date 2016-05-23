package com.jdog.frameworks.util;

import java.io.Serializable;

public class Pair<T1,T2> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private T1 _0;
	private T2 _1;
	
	
	
	public Pair(T1 _0 , T2 _1){
		this.set_0(_0);
		this.set_1(_1);
	}



	public void set_0(T1 _0) {
		this._0 = _0;
	}



	public T1 get_0() {
		return _0;
	}



	public void set_1(T2 _1) {
		this._1 = _1;
	}



	public T2 get_1() {
		return _1;
	}
	
	/**
	 * _0的别名
	 * 
	 */
	public T1 getKey(){
		return _0;
	}
	/**
	 * _1的别名
	 * 
	 */
	public T2 getValue(){
		return _1;
	}
}
