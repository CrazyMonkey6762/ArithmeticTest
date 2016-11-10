package org.stone.entity;

/**
 * @ClassName_PackThing
 * @author_Stone6762
 * @CreationTime_2016年11月10日 下午8:55:25
 * @Description_可以装入背包的物品
 */
public class PackThing implements Comparable<PackThing> {
	/**
	 * @weight重量
	 */
	public double weight;
	/**
	 * @value价值
	 */
	public double value;
	/**
	 * @weVaeRatio单位重量的价值
	 */
	public double ValueWRatio;

	public PackThing(double weight, double value) {
		super();
		this.weight = weight;
		this.value = value;
		this.ValueWRatio = value / weight;
	}

	@Override
	public int compareTo(PackThing p2) {
		return (int) (p2.ValueWRatio - this.ValueWRatio);
	}

}
