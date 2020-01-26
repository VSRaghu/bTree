package com.fractal.btree.service;

import org.springframework.stereotype.Service;

import com.fractal.btree.impl.BTree;

@Service
public class BTreeService {
	
	public static final String bTreeDoesntExist = "BTree doesn't exist";
	
	/**
	 * This method returns BTree if BTree exists.
	 * @return BTree nodes in pre-order 
	 */
	public String getBTree() {
		BTree bTree = BTree.getBTree();
		if(bTree != null) {
			StringBuilder nodesInPreOrder = new StringBuilder();
			return bTree.getNodesInPreOrder(nodesInPreOrder);
		}else {
			return bTreeDoesntExist;
		}	
	}
	
	/**
	 * This method creates BTree for given order, if order is given.
	 * This method also adds the key in the tree. If order is not given the by default tree is created with order 3. 
	 * @param order
	 * @param key
	 */
	public void addKeyInBTree(int key) {
		BTree bTree = BTree.getBTree();
		if(bTree == null) {
			bTree = BTree.createBTree();
		}
		bTree.insert(key);
	}
	
	/**
	 * This method deletes a key from BTree.
	 * @param key
	 * @return
	 */
	public String deleteFromBTree(int key) {
		BTree bTree = BTree.getBTree();
		if(bTree != null) {
			return(bTree.deleteKey(key));
		}else {
			return bTreeDoesntExist;
		}
	}
	
	/**
	 * This method deleted the existing BTree. 
	 * @return
	 */
	public String deleteBTree() {
		BTree bTree = BTree.getBTree();
		if(bTree != null) {
			bTree.deleteBTree();
			return "BTree Deleted Successfully.";
		}else {
			return bTreeDoesntExist;
		}
	}
	
}
