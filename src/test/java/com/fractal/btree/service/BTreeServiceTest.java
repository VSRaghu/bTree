package com.fractal.btree.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fractal.btree.BtreeApplication;
import com.fractal.btree.impl.BTree;

@SpringBootTest(classes = BtreeApplication.class)
@RunWith(SpringRunner.class)
public class BTreeServiceTest {
	
	@Autowired
	BTreeService bTreeService;

	@Autowired
	BTree bTree;
	
	@Test
	public void getBTreeTestIfTreeDoesNotExist() throws Exception {
		String result = bTreeService.getBTree();
		assertEquals(BTreeService.bTreeDoesntExist, result);
	}
	
	@Test
	public void getBTreeTest() throws Exception {
		bTreeService.addKeyInBTree(12);
		bTreeService.addKeyInBTree(13);
		bTreeService.addKeyInBTree(14);
		bTreeService.addKeyInBTree(15);
		bTreeService.addKeyInBTree(16);
		String result = bTreeService.getBTree();
		String[] numbers = result.split(" "); 
		assertEquals(numbers.length,5); // Correct number of elements in tree
		assertEquals(Integer.parseInt(numbers[0]), 12);// Checking root element
		bTreeService.addKeyInBTree(17);
		bTreeService.addKeyInBTree(18);
		String resultAfterAddingNumbers = bTreeService.getBTree();
		String[] numbersAfterAddition = resultAfterAddingNumbers.split(" ");
		assertEquals(numbersAfterAddition.length,7); // Correct number of elements in tree
		assertEquals(Integer.parseInt(numbersAfterAddition[0]), 14);// Checking root element
		bTreeService.deleteBTree();
	}
	
	@Test
	public void deleteBTreeTestWhenNoBTreeExist() throws Exception {
		String result = bTreeService.deleteBTree();
		assertEquals(BTreeService.bTreeDoesntExist, result);
	}
	
	@Test
	public void deleteBTreeTest() throws Exception {
		bTreeService.addKeyInBTree(10);
		String result = bTreeService.deleteBTree();
		assertEquals("BTree Deleted Successfully.", result);
	}
	
	@Test
	public void deleteKeyFromBTreeTestWhenTreeDoesntExist() throws Exception {
		String result = bTreeService.deleteFromBTree(10);
		assertEquals(BTreeService.bTreeDoesntExist, result);
	}
	
	@Test
	public void deleteKeyFromBTreeTest() throws Exception {
		bTreeService.addKeyInBTree(10);
		bTreeService.addKeyInBTree(20);
		bTreeService.addKeyInBTree(30);
		String result = bTreeService.getBTree();
		String[] nodes = result.split(" ");
		assertEquals(nodes.length, 3);
		String deleteResult = bTreeService.deleteFromBTree(10);
		assertEquals("Key deleted successfully", deleteResult);
		String resultAfterDelete = bTreeService.getBTree();
		String[] nodesAfterDelete = resultAfterDelete.split(" ");
		assertEquals(nodesAfterDelete.length, 2);
		String deleteResultForNonExistingKey = bTreeService.deleteFromBTree(10);
		assertEquals("Key does not exist in tree", deleteResultForNonExistingKey);
		bTreeService.deleteBTree();
	}
}
