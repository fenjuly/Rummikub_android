package org.xhome_liurongchan.rummikub.bean;

/**
 * @author [FeN]July E-mail: newfenjuly@gmail.com
 * @version 创建时间：2013-6-12 下午2:17:20 
 * 类说明:实例类
 */
public class Rummikub  implements Comparable<Rummikub>{

	private int color;   //0-black, 1-red, 2-orange, 3-blue

	private int value; //0-103  | 104 and 105 ...  
	
	private int type;  //0-general, 1-special

	
	public Rummikub(int color, int value, int type) {
		this.color = color;
		this.value = value;
		this.type = type;
	}
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int compareTo(Rummikub another) {
		return this.value - another.getValue();
	}

}
