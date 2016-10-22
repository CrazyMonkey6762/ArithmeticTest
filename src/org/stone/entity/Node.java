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
		
		
		
		public Node(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
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
