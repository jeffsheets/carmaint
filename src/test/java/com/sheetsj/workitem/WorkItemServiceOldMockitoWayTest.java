package com.sheetsj.workitem;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;

import com.sheetsj.car.Car;
import com.sheetsj.manufacturer.Manufacturer;
import com.sheetsj.provider.Provider;

public class WorkItemServiceOldMockitoWayTest {
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	//1) Did you know JUnit creates class variables new for each test case?
	private WorkItemRepository workItemRepository = mock(WorkItemRepository.class);
	
	//2) Now have to either overload a protected constructor to accept a workItemRepository...
	//private WorkItemService service = new WorkItemService(workItemRepository);
	private WorkItemService service = new WorkItemService();
	
	@Before
	public void setUp() {
		//2b) Or add a protected setter that takes the workItemRepository
		//service.setWorkItemRepository(workItemRepository)
	}

	//3) Test disabled because it won't work unless we modified line 2 or 2b above
	//@Test
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

	private WorkItem buildWorkItem(String date, Car focus, Provider tiresPlus) throws ParseException {
		return new WorkItem(focus, dateFormat.parse(date), "new tires", tiresPlus, 1000L, 200.00, "");
	}

}
