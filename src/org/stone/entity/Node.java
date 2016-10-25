package org.stone.entity;

/**  
 * @ClassName_Node
 * @author_Stone6762  
 * @CreationTime_2016年10月22日 下午8:07:41
 * @Description_平面上存储点信息的实体
 */
public class Node implements Comparable<Node> {
		public double x;
		public double y;
		
		public Node() {
			super();
		}
		public Node(double x) {
			super();
			this.x = x;
		}
		public Node(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		//默认按照x坐标进行比较，如果x相等，再按照y进行比较
		@Override
		public int compareTo(Node node) {
			//按照x排序，x相等时按照y排序
			double t=this.x-node.x;
			if (t==0) {
				return (int)(this.y-node.y);
			}else {
				return (int)(t);
			}
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
	
}
