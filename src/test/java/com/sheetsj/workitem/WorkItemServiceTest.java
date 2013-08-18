package com.sheetsj.workitem;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.sheetsj.car.Car;
import com.sheetsj.manufacturer.Manufacturer;
import com.sheetsj.provider.Provider;

@RunWith(MockitoJUnitRunner.class)
public class WorkItemServiceTest {
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	@Mock
	private WorkItemRepository workItemRepository;
	
	@InjectMocks
	@Spy
	private WorkItemService service = new WorkItemService();

	@Test
	public void testGetYearAsString_1999() throws ParseException {
		String result = service.getYearAsString(dateFormat.parse("03/01/1999"));
		
		assertThat(result, is("1999"));
		assertThat(result, is(equalTo("1999")));
	}

	@Test
	public void testGetYearAsString_2000() throws ParseException {
		String result = service.getYearAsString(dateFormat.parse("03/01/2000"));
		
		assertThat(result, is("2000"));
	}

	@Test
	public void testGetYearAsString_199() throws ParseException {
		String result = service.getYearAsString(dateFormat.parse("03/01/199"));
		
		assertThat(result, is("199"));
	}

	@Test
	public void testGetYearAsString_13() throws ParseException {
		String result = service.getYearAsString(dateFormat.parse("03/01/13"));
		
		assertThat(result, is("13"));
	}

	@Test
	public void testGetYearAsString_2013() throws ParseException {
		String result = service.getYearAsString(dateFormat.parse("03/01/2013"));
		
		assertThat(result, is("2013"));
	}

	@Test
	public void testSelectWorkItemsByMakeAndYear() throws ParseException {
		Manufacturer ford = new Manufacturer("ford");
		Manufacturer toyota = new Manufacturer("toyota");
		Car focus = new Car(2008, ford, "Focus", "4 cylinder");
		Car f150 = new Car(2010, ford, "F-150", "High HP");
		Car camry = new Car(2011, toyota, "Camry", "Sedan");
		Provider tiresPlus = new Provider("Tires Plus", "Bellevue");
		
		WorkItem workItem1 = buildWorkItem("01/01/2012", focus, tiresPlus);
		WorkItem workItem2 = buildWorkItem("12/31/2012", f150, tiresPlus);
		WorkItem workItem3 = buildWorkItem("01/01/2013", camry, tiresPlus);
		WorkItem workItem4 = buildWorkItem("12/31/2013", f150, tiresPlus);
		WorkItem workItem5 = buildWorkItem("12/01/2013", focus, tiresPlus);
		List<WorkItem> allWorkItems = Arrays.asList(workItem1, workItem2, workItem3, workItem4, workItem5);
		
		Collection<WorkItem> results = service.selectWorkItemsByMakeAndYear("ford", "2013", allWorkItems);
		assertThat(results, containsInAnyOrder(workItem3, workItem5));
		
		results = service.selectWorkItemsByMakeAndYear("ford", "2012", allWorkItems);
		assertThat(results, containsInAnyOrder(workItem1, workItem2));
		
		results = service.selectWorkItemsByMakeAndYear("chevy", "2013", allWorkItems);
		assertThat(results, empty());
		
		results = service.selectWorkItemsByMakeAndYear("ford", "2014", allWorkItems);
		assertThat(results, empty());
	}

	@Test
	public void testFindAllByMakeAndYear() throws ParseException {
		Manufacturer ford = new Manufacturer("ford");
		Manufacturer toyota = new Manufacturer("toyota");
		Car focus = new Car(2008, ford, "Focus", "4 cylinder");
		Car f150 = new Car(2010, ford, "F-150", "High HP");
		Car camry = new Car(2011, toyota, "Camry", "Sedan");
		Provider tiresPlus = new Provider("Tires Plus", "Bellevue");
		
		WorkItem workItem1 = buildWorkItem("01/01/2012", focus, tiresPlus);
		WorkItem workItem2 = buildWorkItem("12/31/2012", f150, tiresPlus);
		WorkItem workItem3 = buildWorkItem("01/01/2013", camry, tiresPlus);
		WorkItem workItem4 = buildWorkItem("12/31/2013", f150, tiresPlus);
		WorkItem workItem5 = buildWorkItem("12/01/2013", focus, tiresPlus);
		List<WorkItem> allWorkItems = Arrays.asList(workItem1, workItem2, workItem3, workItem4, workItem5);
		
		when(workItemRepository.findAll()).thenReturn(allWorkItems);
		
		Collection<WorkItem> results = service.findAllByMakeAndYear("ford", "2013");
		assertThat(results, containsInAnyOrder(workItem3, workItem5));
		
		results = service.findAllByMakeAndYear("ford", "2012");
		assertThat(results, containsInAnyOrder(workItem1, workItem2));
		
		results = service.findAllByMakeAndYear("chevy", "2013");
		assertThat(results, empty());
		
		results = service.findAllByMakeAndYear("ford", "2014");
		assertThat(results, empty());
	}

	@Test
	public void testFindAllByMakeAndYear_spy() throws ParseException {
		List<WorkItem> allWorkItems = Arrays.asList(new WorkItem(), new WorkItem());
		when(workItemRepository.findAll()).thenReturn(allWorkItems);
		
		//Uses a spy on the service. Usually recommend refactoring service, but sometimes this is necessary or just easier
		WorkItem expected = new WorkItem();
		doReturn(Arrays.asList(expected)).when(service).selectWorkItemsByMakeAndYear("bmw", "2008", allWorkItems);
		
		Collection<WorkItem> results = service.findAllByMakeAndYear("bmw", "2008");
		
		//Showing nested matchers in contains and sameInstance
		assertThat(results, contains(sameInstance(expected)));
	}

	private WorkItem buildWorkItem(String date, Car focus, Provider tiresPlus) throws ParseException {
		return new WorkItem(focus, dateFormat.parse(date), "new tires", tiresPlus, 1000L, 200.00, "");
	}

}
