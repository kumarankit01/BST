package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CreateBTree {
	public Node root = null;
	public Node insertNode(Node node) {
		if (root == null) {
			root = node;
			System.out.println("root is "+root.data);
		} else {

			Node parent = root;
			Node child = root;

			while(child != null){
				parent = child;
				if(child.data > node.data){
					child = child.leftNode;
				}else{
					child = child.rightNode;
				}
			}
			if (parent.data > node.data) {
				System.out.println("adding "+node.data+" to left of "+parent.data);
				parent.leftNode = node;
			} else {
				parent.rightNode = node;
				System.out.println("adding "+node.data+" to right of "+parent.data);

			}
		}
		return root;
	}

	public static void main(String[] args) {
		CreateBTree bTree = createDefaultTree();
		CreateBTree.printInorder(bTree.root);
		boolean isPresent = searchNode(bTree, new Node(70));
		System.out.println("data available status : "+isPresent);
		CreateBTree bTree2 = createDefaultTree();
		boolean isSameTree = compareTree(bTree.root, bTree2.root);
		System.out.println("is tree same : "+isSameTree);
		isSameTree = compareTree(bTree.root, new CreateBTree().root);
		System.out.println("is tree same : "+isSameTree);
		int sizeOfTree = getSizeOfTree(bTree.root);
		System.out.println("size of tree : "+sizeOfTree);

		int maxHeight = getMaxHeightOfTree(bTree.root);
		System.out.println("max height of tree : "+maxHeight);
		List<Integer> nodeList = new ArrayList<>();
		boolean isSuchPathAvail = rootToLeafSum(61,bTree.root,nodeList);
		System.out.println("is root to leaf path available : "+isSuchPathAvail);
		for (Integer integer : nodeList) {
			System.out.println(integer);
		}

		CreateBTree.levelOrder(bTree.root, null);
		CreateBTree.levelOrder(bTree.root);


	}

	public static void levelOrder(Node node) {
		if(node == null){
			return;
		}
		Queue<Node> nodes = new LinkedList<>();
		nodes.add(node);
		while(!nodes.isEmpty()){
			Node curr = nodes.poll();
			System.out.println(curr.data);
			if(curr.leftNode != null)
				nodes.add(curr.leftNode);
			if(curr.rightNode != null)
				nodes.add(curr.rightNode);
		}
	}

	public static void levelOrder(Node root,List<Node> node) {
		if(root == null)
			return;
		if(node == null){
			node = new ArrayList<>();
			node.add(root);
		}
		List<Node> newList = new ArrayList<>();
		for (Node curr : node) {
			System.out.print(curr.data+"  ");
			if(curr.leftNode != null)
				newList.add(curr.leftNode);
			if(curr.rightNode != null)
				newList.add(curr.rightNode);
		}
		System.out.println();
		if(newList.size()!=0)
		levelOrder(root, newList);
	}

	public static boolean rootToLeafSum(int sum, Node node, List<Integer> nodeList) {
		if(node == null){
			return false;
		}
		//check if leaf
		if(node.leftNode == null && node.rightNode == null){
			if(node.data == sum){
				nodeList.add(node.data);
				return true;
			}else{
				return false;
			}
		}else{
			if(rootToLeafSum(sum-node.data, node.leftNode, nodeList)){
				nodeList.add(node.data);
				return true;
			}if(rootToLeafSum(sum-node.data, node.rightNode, nodeList)){
				nodeList.add(node.data);
				return true;
			}
		}
		return false;
	}

	public static int getMaxHeightOfTree(Node root) {
		if(root == null)
			return 0;
		return 1+ Math.max(getMaxHeightOfTree(root.leftNode), getMaxHeightOfTree(root.rightNode));
	}

	public static int getSizeOfTree(Node root) {
		if(root==null)
			return 0;
		return 1+getSizeOfTree(root.leftNode)+getSizeOfTree(root.rightNode);
	}

	public static boolean compareTree(Node root1, Node root2) {
		if(root1 == null && root2 == null){
			return true;
		}
		if(root1 == null || root2 == null){
			return false;
		}
		return root1.data == root2.data && compareTree(root1.leftNode, root2.leftNode)
				&& compareTree(root1.rightNode, root2.rightNode);

	}

	public static boolean searchNode(CreateBTree bTree, Node node) {

		Node temp = bTree.root;
		while(temp!=null){
			if(node.data == temp.data){
				return true;
			}
			else if(node.data < temp.data){
				temp = temp.leftNode;
			}else{
				temp = temp.rightNode;
			}
		}
		return false;
	}

	public static CreateBTree createDefaultTree() {
		CreateBTree bTree = new CreateBTree();
		Node node = new Node(10);
		bTree.insertNode(node);

		node = new Node(5);
		bTree.insertNode(node);

		node = new Node(7);
		bTree.insertNode(node);

		node = new Node(3);
		bTree.insertNode(node);

		node = new Node(15);
		bTree.insertNode(node);

		node = new Node(20);
		bTree.insertNode(node);

		node = new Node(16);
		bTree.insertNode(node);

		node = new Node(14);
		bTree.insertNode(node);

		node = new Node(21);
		bTree.insertNode(node);

		node = new Node(22);

		bTree.insertNode(node);
		return bTree;

	}
	public static  void printPreorder(Node root2) {
		System.out.println(root2.data);

		if(root2.leftNode != null)
			printPreorder(root2.leftNode);

		if(root2.rightNode != null)
			printPreorder(root2.rightNode);

	}
	public static  void printInorder(Node root2) {

		if(root2.leftNode != null)
			printInorder(root2.leftNode);

		System.out.println(root2.data);

		if(root2.rightNode != null)
			printInorder(root2.rightNode);

	}
	public static  void printPostorder(Node root2) {

		if(root2.leftNode != null)
			printPostorder(root2.leftNode);

		if(root2.rightNode != null)
			printPostorder(root2.rightNode);

		System.out.println(root2.data);
	}
	boolean checkBST(Node root) {
		if(root == null){
			return true;
		}else{
			return checkBST(root,-1,100000);
		}

	}
	private boolean checkBST(Node root, int lowerBound, int upperBound) {
		if(root == null){
			return true;
		}else{
			if(temp.containsKey(root.data)){
				return false;
			}
			temp.put(root.data, null);
			if((root.data > lowerBound && root.data < upperBound) && checkBST(root.left,lowerBound,root.data) && checkBST(root.right,root.data,upperBound) ){
				return true;
			}
		}
		return false;
	}
}
class Node{
	Node leftNode;
	Node rightNode;
	int data;
	public Node(int data) {
		this.data = data;
	}
}
