package com.fractal.btree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fractal.btree.response.Response;
import com.fractal.btree.response.ResponseBuilder;
import com.fractal.btree.service.BTreeService;

@SuppressWarnings("rawtypes")
@RestController
public class BTreeController {
	
	@Autowired BTreeService  bTreeService;
	
	/**
	 * This method is to create a new BTree if one doesn't exists already. If Tree already exists then we add the key value in the BTree.
	 * @param key
	 * @return Response
	 */
	@PutMapping("addKeyInBTree/{key}")
	public Response addKeyInBTree(@PathVariable int key) {
		bTreeService.addKeyInBTree(key);
		return ResponseBuilder.successResponse("Successfully Added "+ key +" in BTree");
	}
	
	/**
	 * This method is to get the already created BTree.
	 * @return Response
	 */
	@GetMapping("getBTree")
	public Response getBTree() {
		return ResponseBuilder.successResponse(bTreeService.getBTree());
	}
	
	/**
	 * This method is to delete a key from the BTree.
	 * @return Response
	 */
	@DeleteMapping("deleteKeyFromBTree/{key}")
	public Response deleteKeyFromBTree(@PathVariable int key) {
		return ResponseBuilder.successResponse(bTreeService.deleteFromBTree(key));
	}
	
	/**
	 * This method is to delete the BTree.
	 * @return Response
	 */
	@DeleteMapping("deleteBTree")
	public Response deleteBTree() {
		return ResponseBuilder.successResponse(bTreeService.deleteBTree());
	}
}
