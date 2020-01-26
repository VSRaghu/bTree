package com.fractal.btree.impl;

import org.springframework.stereotype.Component;

/**
 * @author Vivek
 * BTree class Implementation.
 */
@Component
public class BTree {
	private static BTree bTree = null;
	int order = 3; // Order of the tree. Default kept 3.
	public Node root; // Root node of the tree.

	/**
	 * @author Vivek
	 * This class Represents nodes of BTree.
	 */
	private static class Node {
		int key[]; // Number of keys that can be accommodated in a node.
		int count; // Count maintains number of keys already inserted in the node.
		Node children[];
		boolean leaf;

		public Node(int order, Node parent) {
			key = new int[2 * order - 1];
			children = new Node[2 * order];
			leaf = true;
			count = 0;
		}

		public int getValueAtGivenIndex(int i) {
			return key[i];
		}

		public Node getchildren(int i) {
			return children[i];
		}
	}

	private BTree() {
		root = new Node(order, null);
	}
	
	public static BTree getBTree() {
		return bTree;
	}
	
	public static BTree createBTree() {
		if(bTree ==  null) {
			bTree = new BTree();
		}
		return bTree;
	}

	/**
	 * This method inserts a key into the BTree.
	 * @param key
	 */
	public void insert(int key) {
		Node rootNode = bTree.root;
		// Checking if root node is all occupied. No place for another key. Then we need to split this node.
		if (rootNode.count == 2 * order - 1) {
			Node newNode = new Node(order, null);
			bTree.root = newNode;
			newNode.count = 0;
			newNode.children[0] = rootNode;
			newNode.leaf = false;
			splitNode(newNode, rootNode, 0);
			insertKeyAtGivenNode(newNode, key);
		} else {
			// If root node is not full then we can insert in root node itself.
			insertKeyAtGivenNode(rootNode, key);
		}
	}

	/**
	 * This method splits a node whenever required to insert a new key.
	 * @param node
	 * @param nodeToSplit
	 * @param indexToSplit
	 */
	public void splitNode(Node node, Node nodeToSplit, int indexToSplit) {
		Node newNode = new Node(order, null);
		newNode.leaf = nodeToSplit.leaf;
		newNode.count = order - 1;
		for (int j = 0; j < order - 1; j++) {
			// Will gather second half keys in newNode from nodeToSplit
			newNode.key[j] = nodeToSplit.key[j + order];
		}
		if (!nodeToSplit.leaf) {
			for (int k = 0; k < order; k++) {
				// Copy half of the children from nodeToSpllit to newNode if NodeToSplit is not
				// a leaf node
				newNode.children[k] = nodeToSplit.children[k + order];
			}
		}
		// As we have copied keys from nodeToSplit to newNode so now we have place for
		// new keys in nodeToSplit. So changing the keyCount in nodeToSplit
		nodeToSplit.count = order - 1;
		// Re-arranging node children.
		for (int j = node.count; j > indexToSplit; j--) {
			node.children[j + 1] = node.children[j];
		}
		node.children[indexToSplit + 1] = newNode;
		// Re-arranging node keys.
		for (int j = node.count; j > indexToSplit; j--) {
			node.key[j + 1] = node.key[j];
		}
		node.key[indexToSplit] = nodeToSplit.key[order - 1];
		nodeToSplit.key[order - 1] = 0;
		// Setting up other half after split as Zero.
		for (int j = 0; j < order - 1; j++) {
			nodeToSplit.key[j + order] = 0;
		}
		node.count++;
	}
	
	/**
	 * This method searches a key in the BTree.
	 * @param root
	 * @param key
	 * @return
	 */
	public Node search(Node root, int key) {
		int i = 0;
		while (i < root.count && key > root.key[i]) {
			i++;
		}
		if (i <= root.count && key == root.key[i]) {
			return root;
		}
		if (root.leaf) {
			return null;
		} else {
			return search(root.getchildren(i), key);
		}
	}

	
	/**
	 * This methods inserts a key at a given node.
	 * @param node
	 * @param key
	 */
	public void insertKeyAtGivenNode(Node node, int key) {
		int KeyCount = node.count;
		if (node.leaf) {
			// If leaf node then move then make space for new key. Shift lesser values to
			// left and then higher to the right.
			while (KeyCount >= 1 && key < node.key[KeyCount - 1]) {
				node.key[KeyCount] = node.key[KeyCount - 1];
				KeyCount--;
			}
			node.key[KeyCount] = key;
			node.count++;
		} else {
			// If not leaf node then find the place either in non-leaf node by splitting them or find the leaf node where to put this key.
			int index = 0;
			while (index < node.count && key > node.key[index]) {
				index++;
			}
			if (node.children[index].count == order * 2 - 1) {
				splitNode(node, node.children[index], index);
				if (key > node.key[index]) {
					index++;
				}
			}
			insertKeyAtGivenNode(node.children[index], key);
		}
	}
	
	/**
	 * Method for pre-order traversal of tree.
	 * @param nodesInPreOrder
	 * @return
	 */
	public String getNodesInPreOrder(StringBuilder nodesInPreOrder) {
		StringBuilder nodes = getNodes(bTree.root, nodesInPreOrder);
		return nodes.toString();
	}

	/**
	 * Method for pre-order traversal of tree. Left nodes to right node.
	 * Traversing from left most nodes to right most nodes.
	 * @param node
	 * @param nodesInPreOrder
	 * @return
	 */
	public StringBuilder getNodes(Node node, StringBuilder nodesInPreOrder) {
		//Add root node in the output
		for (int i = 0; i < node.count; i++) {
			nodesInPreOrder.append(node.getValueAtGivenIndex(i) + " ");
		}
		//Add node in the output in case of non leaf nodes
		if (!node.leaf) {
			for (int j = 0; j <= node.count; j++) {
				if (node.getchildren(j) != null) {
					getNodes(node.getchildren(j), nodesInPreOrder);
				}
			}
		}
		return nodesInPreOrder;
	}
	
	/**
	 * Method to delete a key from the BTree.
	 * @param bTree
	 * @param key
	 * @return
	 */
	public String deleteKey(int key) {
		Node temp = new Node(order, null);
		temp = search(bTree.root, key);
		if(temp == null) {
			return "Key does not exist in tree";
		}
		if (temp.leaf && temp.count > order - 1) {
			//Remove the key and re arrange the other keys.
			int i = 0;
			while (key > temp.getValueAtGivenIndex(i)) {
				i++;
			}
			for (int j = i; j < 2 * order - 2; j++) {
				temp.key[j] = temp.getValueAtGivenIndex(j + 1);
			}
			temp.count--;
			return "Key deleted successfully";
		} else {
			return "This node is either not a leaf or has less than order - 1 keys";
		}
	}
	
	/**
	 * This method nullify the existing BTree.
	 * @return
	 */
	public void deleteBTree() {
		bTree = null;
	}
}
