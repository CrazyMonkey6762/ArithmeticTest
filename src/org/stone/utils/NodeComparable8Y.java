package org.stone.utils;

import java.util.Comparator;

import org.stone.entity.Node;

/**
 * @ClassName_NodeComparable8Y
 * @author_Stone6762
 * @CreationTime_2016年10月25日 上午11:53:19
 * @Description_
 */
public class NodeComparable8Y implements Comparator<Node> {

	@Override
	public int compare(Node n1, Node n2) {
		if (n1.y-n2.y>0) {
			return 1;
		}else {
			return -1;
		}
	}

}
