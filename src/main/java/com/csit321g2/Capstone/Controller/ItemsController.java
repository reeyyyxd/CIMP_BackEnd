package com.csit321g2.Capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.ItemsEntity;
import com.csit321g2.Capstone.Service.ItemsService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/item")
public class ItemsController {
	
	@Autowired
	ItemsService iserv;
	
	@PostMapping("/insertItem")
	public ItemsEntity insertItem(@RequestBody ItemsEntity item) {
		return iserv.insertItem(item);
	}
	
	@GetMapping("/getAllItems")
	public List<ItemsEntity> getAllItems(){
		return iserv.getAllItems();
	}
	
	@PutMapping("/updateItem")
	public ItemsEntity updateItem(@RequestParam Long propertyTag, @RequestBody ItemsEntity newItemDetails) {
		return iserv.updateItem(propertyTag, newItemDetails);
	}
	
	@DeleteMapping("/deleteItem/{propertyTag}")
	public String deleteItem(@PathVariable Long propertyTag) {
		return iserv.deleteItem(propertyTag);
	}
	
	@GetMapping("/accPer")
	public List<String> fetchAccPer() {
		return iserv.fetchAccPer();
	}

	@GetMapping("/dep")
	public List<String> fetchDep() {
		return iserv.fetchDep();
	}
	
	@GetMapping("/des")
	public List<String> fetchDesig() {
		return iserv.fetchDesig();
	}

	@GetMapping("/status")
	public List<String> fetchStatus() {
		return iserv.fetchStatus();
	}

	@GetMapping("/uom")
	public List<String> fetchUom() {
		return iserv.fetchUom();
	}
	
	@GetMapping("/supplier")
	public List<String> fetchSupp() {
		return iserv.fetchSupp();
	}

	@GetMapping("/building")
	public List<String> fetchBuilding() {
		return iserv.fetchBuilding();
	}

	@GetMapping("/room")
	public List<String> fetchRoom() {
		return iserv.fetchRoom();
	}
	
	@GetMapping("/name")
	public List<String> fetchName() {
		return iserv.fetchName();
	}

	@GetMapping("/model")
	public List<String> fetchModel() {
		return iserv.fetchModel();
	}

	@GetMapping("/type")
	public List<String> fetchType() {
		return iserv.fetchType();
	}
	
	@GetMapping("/invoice")
	public List<String> fetchInvoiceDate() {
		return iserv.fetchInvoiceDate();
	}

	@GetMapping("/lifespan")
	public List<String> fetchLifespan() {
		return iserv.fetchLifespan();
	}
	

	@GetMapping("/filter")
	public List<ItemsEntity> fetchFilter(@RequestParam String acc_per, 
	@RequestParam String department,
    @RequestParam String designation,
    @RequestParam String status,
    @RequestParam String uom,
    @RequestParam String supplier,
    @RequestParam String building,
    @RequestParam String room,
    @RequestParam String name,
    @RequestParam String model,
    @RequestParam String type,
	@RequestParam String invoice_date,
	@RequestParam String lifespan) {
		return iserv.fetchFilter(acc_per,department,designation,status,uom,supplier,building,room,name,model,type,invoice_date,lifespan);
	}
	
}
