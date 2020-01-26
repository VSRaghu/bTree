package com.fractal.btree.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fractal.btree.BtreeApplication;
import com.fractal.btree.service.BTreeService;

@SpringBootTest(classes = BtreeApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BTreeControllerTest {

	@Autowired
	BTreeController bTreeController;

	@Autowired
	MockMvc mockMvc;

	private static final String expectedDescription = "\"description\":\"OK\"";
	
	@Test
	public void getBTreeTest() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/getBTree")).andDo(print()).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(true, response.getContentAsString().contains(expectedDescription));
		assertEquals(true, response.getContentAsString().contains(BTreeService.bTreeDoesntExist));
	}

	@Test
	public void addKeyInBTreeTest() throws Exception {
		MvcResult result = this.mockMvc.perform(put("/addKeyInBTree/10")).andDo(print()).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(true, response.getContentAsString().contains(expectedDescription));
		assertEquals(true, response.getContentAsString().contains("Successfully Added 10 in BTree"));
		bTreeController.deleteBTree();
	}

	@Test
	public void deleteKeyFromBTreeTest() throws Exception {
		bTreeController.addKeyInBTree(10);
		MvcResult result = this.mockMvc.perform(delete("/deleteKeyFromBTree/20")).andDo(print()).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(true, response.getContentAsString().contains(expectedDescription));
		assertEquals(true, response.getContentAsString().contains("Key does not exist in tree"));
		bTreeController.deleteBTree();
	}

	@Test
	public void deleteBTreeTest() throws Exception {
		bTreeController.addKeyInBTree(10);
		MvcResult result = this.mockMvc.perform(delete("/deleteBTree")).andDo(print()).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(true, response.getContentAsString().contains(expectedDescription));
		assertEquals(true, response.getContentAsString().contains("BTree Deleted Successfully."));
	}

}
