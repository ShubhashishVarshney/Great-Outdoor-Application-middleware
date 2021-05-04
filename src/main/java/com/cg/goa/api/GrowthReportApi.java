package com.cg.goa.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.goa.exception.GrowthReportException;
import com.cg.goa.exception.SalesReportException;
import com.cg.goa.model.GrowthReportModel;
import com.cg.goa.service.IGrowthReportService;

@RestController
@RequestMapping("/GrowthReportEntity")
public class GrowthReportApi {

	@Autowired
	private IGrowthReportService growthservice;

	/*
	 * to retrieve all GrowthReport
	 * return : List<customer>
	 * params : NIL
	 */
	@GetMapping
	public ResponseEntity<List<GrowthReportModel>> findAllGrowthReport() {
		return ResponseEntity.ok(growthservice.findAllGrowthReport());
	}

	
	/*
	 * to Add GrowthReport 
	 * return : Boolean
	 * params : NIL
	 */
	@PostMapping
	public ResponseEntity<Boolean> addGrowthReport(@RequestBody GrowthReportModel g) throws SalesReportException {
		ResponseEntity<Boolean> response = null;
		boolean p = growthservice.addGrowthReport(g);
		if (p == false) {
			response = ResponseEntity.notFound().build();
		}
		response = ResponseEntity.ok(p);
		return response;
	}

	
	/*
	 * to delete all GrowthReport
	 * return : Boolean
	 * params : NIL
	 */
	@DeleteMapping
	public ResponseEntity<Boolean> deleteAllGrowthReport() throws GrowthReportException {
		ResponseEntity<Boolean> response = null;
		boolean p = growthservice.deleteAllGrowthReport();
		if (p == false) {
			response = ResponseEntity.notFound().build();
		}
		response = ResponseEntity.ok(p);
		return response;
	}
	/*
	 * to delete growthreport by Id
	 * return : Boolean
	 * params : id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteGrowthReportById(@PathVariable("id") Long growthReportId)
			throws GrowthReportException {
		ResponseEntity<Boolean> response = null;
		boolean p = growthservice.deleteGrowthReportById(growthReportId);
		if (p == false) {
			response = ResponseEntity.notFound().build();
		}
		response = ResponseEntity.ok(p);
		return response;
	}
}
