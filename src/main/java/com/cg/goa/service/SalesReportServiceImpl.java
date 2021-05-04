package com.cg.goa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goa.dao.ISalesReportRepository;
import com.cg.goa.exception.SalesReportException;
import com.cg.goa.model.SalesReportModel;

@Service
public class SalesReportServiceImpl implements ISalesReportService {

	@Autowired
	private ISalesReportRepository salesrepo;

	@Autowired
	private EMParserSalesReport parser;

	/*
	 * A default Constructor with no implementation
	 */

	public SalesReportServiceImpl() {

	}
	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */
	public SalesReportServiceImpl(ISalesReportRepository salesrepo) {
		super();
		this.salesrepo = salesrepo;
		this.parser = new EMParserSalesReport();
	}
	/*
	 * service implementation for Finding All Sales Report
	 */
	@Transactional
	@Override
	public List<SalesReportModel> findAllSalesReport() {
		return salesrepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	/*
	 * service implementation for Finding All Sales Report By ProductId
	 */
	@Transactional
	@Override
	public SalesReportModel findAllSalesReportByProductId(String productId) {
		if (productId != null) {
			return parser.parse(salesrepo.findAllByproductId(productId));
		}
		return null;
	}
	/*
	 * service implementation for Updating Product Report
	 */
	@Transactional
	@Override
	public boolean updateProductReport(String productId, Integer quantity, BigDecimal totalSale) {
		
		if (salesrepo.existsByproductId(productId)) {
			
			SalesReportModel s = parser.parse(salesrepo.findByproductId(productId));
			s.setQuantitySold(quantity);
			s.setTotalSale(totalSale);
			parser.parse(salesrepo.save(parser.parse(s)));
			return true;
		}
		return false;

	}
	/*
	 * service implementation for Deleting All Sales Report
	 * @Throws SalesReportException
	 */
	@Transactional
	@Override
	public boolean deleteAllSalesReport() throws SalesReportException {
		if (salesrepo.findAll() != null) {
			salesrepo.deleteAll();
			return true;
		} else {
			throw new SalesReportException("Nothing to delete. Report table is empty");
		}

	}
	/*
	 * service implementation for Deleting Sales Report By Id
	 * @Throws SalesReportException
	 */
	@Transactional
	@Override
	public boolean deleteSalesReportById(Long salesReportId) throws SalesReportException {
		if (salesrepo.findById(salesReportId) != null) {
			salesrepo.deleteById(salesReportId);
			return true;
		} else {
			throw new SalesReportException("Nothing to delete. Report table is empty");
		}
	}
}
