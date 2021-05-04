package com.cg.goa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cg.goa.dao.ISalesReportRepository;
import com.cg.goa.entity.SalesReportEntity;
import com.cg.goa.exception.SalesReportException;
import com.cg.goa.model.SalesReportModel;

@ExtendWith(MockitoExtension.class)
public class SalesReportServiceTest {

	@Mock
	private ISalesReportRepository salesrepo;

	@InjectMocks /*
					 * injecting Sales Report repository marked as @Mock into Sales Report service
					 * implementation
					 */
	private SalesReportServiceImpl srsImpl;
	/*
	 * test case 1- to Find All Sales Report
	 */
	@Test
	@DisplayName("SalesReportServiceImpl::find All Sales Report should find all reports in SalesReportModel List")
	void testfindAllSalesReport() {
		BigDecimal cost=new BigDecimal("100.12");
		List<SalesReportEntity> testdata =  Arrays.asList(new SalesReportEntity[] {
				new SalesReportEntity(1L, "P01", "cookies", 45,cost),
				new SalesReportEntity(1L, "P01", "cookies", 45,cost)
		});
		List<SalesReportModel> expected =  Arrays.asList(new SalesReportModel[] {
				new SalesReportModel(1L, "P01", "cookies", 45,cost),
				new SalesReportModel(1L, "P01", "cookies", 45,cost)
		});
		Mockito.when(salesrepo.findAll()).thenReturn(testdata);
		List<SalesReportModel> actual=srsImpl.findAllSalesReport();
		assertEquals(expected, actual);
	}
	/*
	 * test case 2- to Find  Sales Report By Product Id
	 */
	@Test
	@DisplayName("SalesReportServiceImpl::Find Orders By Product Id should find orders by product Id in SalesReport List")
	void testFindByProductId() {
		BigDecimal cost=new BigDecimal("100.12");
		SalesReportEntity testdata = new SalesReportEntity(1L, "P01", "cookies", 45,cost);
		SalesReportModel expected = new SalesReportModel(1L, "P01", "cookies", 45, cost);
		Mockito.when(salesrepo.findAllByproductId(testdata.getProductId())).thenReturn(testdata);
		SalesReportModel actual = srsImpl.findAllSalesReportByProductId(expected.getProductId());
		assertEquals(expected, actual);
	}
	/*
	 * test case 3- to Delete Sales Report By Id
	 * @Sales Report Exception
	 */
	//Delete Report By Id Test Case
	@Test
	@DisplayName("SalesReportServiceImpl::delete Sales Report By Id  should delete reports in SalesReportModel ")
	void testdeleteSalesReportByIdException() throws SalesReportException {
		BigDecimal cost=new BigDecimal("100.12");
		SalesReportEntity testdata = new SalesReportEntity(1L, "P01", "cookies", 45,cost);
		SalesReportModel expected = new SalesReportModel(1L, "P01", "cookies", 45, cost);
		Mockito.when(salesrepo.findById(testdata.getSalesReportId())).thenReturn(Optional.of(testdata));
		Mockito.doNothing().when(salesrepo).deleteById(1L);
		boolean actual = srsImpl.deleteSalesReportById(expected.getSalesReportId());
		assertTrue(actual);
	}
	
	/*
	 * test case 4- to Delete All Sales Report
	 * @Sales Report Exception
	 */
	@Test
	@DisplayName("SalesReportServiceImpl::delete All SalesReport should delete all reports in SalesReportModel List")
	void testdeleteAllSalesReport() throws SalesReportException {
		BigDecimal cost=new BigDecimal("100.12");
		List<SalesReportEntity> testdata =  Arrays.asList(new SalesReportEntity[] {
				new SalesReportEntity(1L, "P01", "cookies", 45,cost),
				new SalesReportEntity(1L, "P01", "cookies", 45,cost)
		});
		List<SalesReportModel> expected =  Arrays.asList(new SalesReportModel[] {
				new SalesReportModel(1L, "P01", "cookies", 45,cost),
				new SalesReportModel(1L, "P01", "cookies", 45,cost)
		});
	    Mockito.when(salesrepo.findAll()).thenReturn(testdata);
		assertTrue(srsImpl.deleteAllSalesReport());
		
	}

}