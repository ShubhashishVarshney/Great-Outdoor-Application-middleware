package com.cg.goa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.goa.dao.IGrowthReportRepository;
import com.cg.goa.entity.GrowthReportEntity;
import com.cg.goa.exception.GrowthReportException;
import com.cg.goa.exception.SalesReportException;
import com.cg.goa.model.GrowthReportModel;

@ExtendWith(MockitoExtension.class)
public class GrowthReportServiceImplTest {
	@Mock
	private IGrowthReportRepository growthrepo;

	@InjectMocks /*
					 * injecting growth report repository marked as @Mock into growth report service
					 * implementation
					 */
	private GrowthReportServiceImpl gsImpl;
	/*
	 * Test Case 1 - to Find All
	 */
	@Test
	@DisplayName("GrowthReportServiceImpl::findAll should return list of existing packages as GrowthReport Model List")
	void testFindAll() {
		BigDecimal cost=new BigDecimal("100.12");
		List<GrowthReportEntity> testdata = Arrays.asList(new GrowthReportEntity[] {
				
				new GrowthReportEntity(1L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red"),
				new GrowthReportEntity(2L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red") });

		Mockito.when(growthrepo.findAll()).thenReturn(testdata); /* when repo.findAll() is called, then test data */

		List<GrowthReportModel> expected = Arrays.asList(new GrowthReportModel[] {
				new GrowthReportModel(1L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red"),
				new GrowthReportModel(2L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red") });

		List<GrowthReportModel> actual = gsImpl.findAllGrowthReport();

		assertEquals(expected, actual);
	}
	/*
	 * Test Case 2 - to add Growth Report
	 * @throws   SalesReportException
	 */
	@Test
	@DisplayName("GrowthReportServiceImpl::add should add growthreport to list of existing packages as GrowthReport Model List")
	void testaddGrowthReport() throws SalesReportException {
		BigDecimal cost=new BigDecimal("100.12");
		GrowthReportEntity testdata = new GrowthReportEntity(1L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red");
		GrowthReportModel expected = new GrowthReportModel(1L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red");
		Mockito.when(growthrepo.existsById(testdata.getGrowthReportId())).thenReturn(false);
     // Mockito.when(growthrepo.save(testdata)).thenReturn(testdata);
		//boolean actual =gsImpl.addGrowthReport();
		assertTrue(gsImpl.addGrowthReport(expected));
	}
	/*
	 * Test Case 3 - to Delete All Growth Report
	 * @throws   GrowthReportException
	 */
	@Test
	@DisplayName("GrowthReportServiceImpl::deleteallgrowthreport should delete all growth report of existing packages as GrowthReport Model List")
	void testDeleteAllGrowthReport() throws GrowthReportException {
		BigDecimal cost=new BigDecimal("100.12");
		List<GrowthReportEntity> testdata = Arrays.asList(new GrowthReportEntity[] {
				new GrowthReportEntity(1L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red"),
				new GrowthReportEntity(2L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red")
				});
			Mockito.when(growthrepo.findAll()).thenReturn(testdata); /* when repo.findAll() is called, then test data */
			
		Mockito.doNothing().when(growthrepo).deleteAll();
		boolean actual = gsImpl.deleteAllGrowthReport();
		assertTrue(actual);

	}
	/*
	 * Test Case 3 - to Delete Growth Report By Id
	 * @throws   GrowthReportException
	 */
	@Test
	@DisplayName("GrowthReportServiceImpl::deletegrowtreportbyid should delete growth report id of existing packages as GrowthReport Model List")
	void testDeleteGrowthReportById() throws GrowthReportException {
		BigDecimal cost=new BigDecimal("100.12");
		GrowthReportEntity testdata = new GrowthReportEntity(1L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red");
		GrowthReportModel expected = new GrowthReportModel(1L,LocalDateTime.of(LocalDate.of(2020, 03, 02), LocalTime.of(16, 30)),cost,cost,cost,"red");
		Mockito.when(growthrepo.existsById(testdata.getGrowthReportId())).thenReturn(true);
		Mockito.doNothing().when(growthrepo).deleteById(1L);
		boolean actual =gsImpl.deleteGrowthReportById(expected.getGrowthReportId());
		assertTrue(actual);

	}
}