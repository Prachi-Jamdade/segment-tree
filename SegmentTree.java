package com.binarytree;

public class SegmentTree {
	
	private static class Node {
		int data;
		int startInterval;
		int endInterval;
		Node left;
		Node right;
		
		public Node(int startInterval, int endInterval) {
			this.startInterval = startInterval;
			this.endInterval = endInterval;
		}
	}
	
	Node root;
	
	public SegmentTree(int[] arr) {
		this.root = constructTree(arr, 0, arr.length - 1);
	}
	
	private static Node constructTree(int[] arr, int start, int end) {
		// creation of Leaf Node
		if(start == end) {
			Node leafNode = new Node(start, end);
			leafNode.data = arr[start];
			return leafNode;
		}
		
		// creation of a new node with current index 
		Node node = new Node(start, end);
		int mid = (start + end) / 2;
		
		node.left = constructTree(arr, start, mid);
		node.right = constructTree(arr, mid+1, end);
		
		node.data = node.left.data + node.right.data;
		
		return node;
	}
	
	public void display() {
		display(this.root);
	}
	
	private void display(Node node) {
		String str = "";
		
		if(node.left != null) {
			str = str + "Interval = [" + node.left.startInterval + ", " + node.left.endInterval + "]" + 
		" & data: " + node.left.data + "-> ";
		} else {
			str = str + "null => ";
		}
		
		str = str + "Interval = [" + node.startInterval + ", " + node.endInterval + "]" + 
		" & data: " + node.data + " => ";
		
		if(node.right != null) {
			str = str + "Interval = [" + node.right.startInterval + ", " + node.right.endInterval + "]" + 
		" & data: " + node.right.data;
		} else {
			str = str + "null ";
		}
		
		System.out.println(str + "\n");
		
		if(node.left != null)	display(node.left);
		
		if(node.right != null)	display(node.right);
	}
	
	public int query(int queryStartIndex, int queryEndIndex) {
		return query(root, queryStartIndex, queryEndIndex);
	}
	
	private int query(Node node, int queryStartIndex, int queryEndIndex) {
		// node is completely lying inside query interval
		if(node.startInterval >= queryStartIndex && node.endInterval <= queryEndIndex) {
			return node.data;
		} 
		// node is completely lying outside query interval
		else if(node.startInterval > queryEndIndex || node.endInterval < queryStartIndex) {
			return 0;
		} 
		// overlapping of intervals, call to recursion
		else {
			return query(node.left, queryStartIndex, queryEndIndex) + query(node.right, queryStartIndex, queryEndIndex);
		}
	}
	
	public void update(int index, int value) {
		root.data = update(root, index, value);
	}
	
	private int update(Node node, int index, int value) {
		// index lying inside interval
		if(index >= node.startInterval && index <= node.endInterval) {
			
			// leaf node, then update the data with current value
			if(index == node.startInterval && index == node.endInterval) {
				node.data = value;
				return node.data;
			} 
			// call to recursion to update the tree
			else {
				int leftData = update(node.left, index, value);
				int rightData = update(node.right, index, value);
				
				node.data = leftData + rightData;
			}
		} 
		
		return node.data;
	}
	
	public static void main(String[] args) {
		int arr[] = {3, 8, 6, -10, 2, -5, 12, 87};
		
		SegmentTree tree = new SegmentTree(arr);
//		tree.display();
		System.out.println(tree.query(1, 6));
	}

}
