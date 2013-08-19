package com.sheetsj.workitem;

import spock.lang.Specification
import spock.lang.Unroll

import com.sheetsj.car.Car
import com.sheetsj.manufacturer.Manufacturer
import com.sheetsj.provider.Provider

import static spock.util.matcher.HamcrestSupport.*
import static org.hamcrest.Matchers.*

class WorkItemServiceSpecTest extends Specification {
	def service = new WorkItemService();
	def workItemRepository = Mock(WorkItemRepository)
	
	def setup() {
		service.workItemRepository = workItemRepository
	}

	def "getYearAsString works for simple case"() {
		given: 'a normal date'
		def date = Date.parse("MM/dd/yyyy", "08/01/2013")
		
		when: 'getYearAsString is called'
		def result = service.getYearAsString(date)
		
		then: 'year is correct on result'
		result == "2013"
	}

	def "getYearAsString works for simple case using expect block"() {
		given: 'a normal date'
		def date = Date.parse("MM/dd/yyyy", "08/01/2013")
		
		expect: 'getYearAsString is correct when called'
		service.getYearAsString(date) == "2013"
	}
	
	@Unroll
	def "getYearAsString with #inputDate returns #result"(String inputDate, String result) {
		expect: 'getYearAsString is correct when called'
		service.getYearAsString(Date.parse("MM/dd/yyyy", inputDate)) == result
		
		where:
		inputDate	 | result
		"01/01/1999" | "1999"
		"02/01/2000" | "2000"
		"03/01/199"  | "199"
		'03/01/2013' | '2013'	
	}
	
	/** With Hamcrest matchers */
	def "selectWorkItemsByMakeAndYear tests correctly"() {
		given: 'a bunch of work items'
		List<WorkItem> allWorkItems = buildAllWorkItems()
		
		expect: 'findAllByMakeAndYear returns correct results'
		that service.selectWorkItemsByMakeAndYear('ford', '2013', allWorkItems), containsInAnyOrder(allWorkItems[2], allWorkItems[4])
		that service.selectWorkItemsByMakeAndYear('ford', '2012', allWorkItems), containsInAnyOrder(allWorkItems[0], allWorkItems[1])
		that service.selectWorkItemsByMakeAndYear('chevy', '2013', allWorkItems), empty()
		that service.selectWorkItemsByMakeAndYear('ford', '2014', allWorkItems), empty()
	}
	
	/** Almost simpler without Hamcrest because of groovy though! */
	def "selectWorkItemsByMakeAndYear tests correctly but without hamcrest"() {
		given: 'a bunch of work items'
		List<WorkItem> allWorkItems = buildAllWorkItems()
		
		expect: 'findAllByMakeAndYear returns correct results'
		service.selectWorkItemsByMakeAndYear('ford', '2013', allWorkItems).containsAll allWorkItems[2,4]
		service.selectWorkItemsByMakeAndYear('ford', '2012', allWorkItems).containsAll allWorkItems[0..1]
		service.selectWorkItemsByMakeAndYear('chevy', '2013', allWorkItems).empty
		!service.selectWorkItemsByMakeAndYear('ford', '2014', allWorkItems)
	}
	
	def "findAllByMakeAndYear works with mocks"() {
		given: 'a bunch of work items'
		List<WorkItem> allWorkItems = buildAllWorkItems()
		
		when: 'findAllByMakeAndYear is called'
		def results = service.findAllByMakeAndYear('ford', '2013')
		
		then: 'results are correct'
		1 * workItemRepository.findAll() >> allWorkItems
		results.containsAll allWorkItems[2,4]
	}
	
	/** not a good pattern. just showing it is possible */
	def "findAllByMakeAndYear with many tests at once"() {
		given: 'a bunch of work items'
		List<WorkItem> allWorkItems = buildAllWorkItems()
		_ * workItemRepository.findAll() >> allWorkItems
		
		expect: 'findAllByMakeAndYear is called and has correct results'
		service.findAllByMakeAndYear('ford', '2013').containsAll allWorkItems[2,4]
		service.findAllByMakeAndYear('ford', '2012').containsAll allWorkItems[0..1]
		service.findAllByMakeAndYear('chevy', '2013').empty
		!service.findAllByMakeAndYear('ford', '2014')
	}
	
	/** a better pattern */
	def "findAllByMakeAndYear with many tests at once but multiple blocks"() {
		given: 'a bunch of work items'
		List<WorkItem> allWorkItems = buildAllWorkItems()
		
		when: 'findAllByMakeAndYear is called for Ford and 2013'
		def results = service.findAllByMakeAndYear('ford', '2013')
		
		then: 'results are correct'
		1 * workItemRepository.findAll() >> allWorkItems
		results.containsAll allWorkItems[2,4]
		
		when: 'findAllByMakeAndYear is called for Ford and 2012'
		results = service.findAllByMakeAndYear('ford', '2012')
		
		then: 'results are correct'
		1 * workItemRepository.findAll() >> allWorkItems
		results.containsAll allWorkItems[0..1]
		
		when: 'findAllByMakeAndYear is called for Chevy and 2013'
		results = service.findAllByMakeAndYear('chevy', '2013')
		
		then: 'results are correct'
		1 * workItemRepository.findAll() >> allWorkItems
		results.empty
		
		when: 'findAllByMakeAndYear is called for Ford and 2014'
		results = service.findAllByMakeAndYear('ford', '2014')
		
		then: 'results are correct'
		1 * workItemRepository.findAll() >> allWorkItems
		!results
	}
	
	List<WorkItem> buildAllWorkItems() {
		Manufacturer ford = new Manufacturer("ford")
		Manufacturer toyota = new Manufacturer("toyota")
		
		Car focus = new Car(2008, ford, "Focus", "4 cylinder")
		Car f150 = new Car(2010, ford, "F-150", "High HP")
		Car camry = new Car(2011, toyota, "Camry", "Sedan")
		Provider tiresPlus = new Provider("Tires Plus", "Bellevue")
		
		WorkItem workItem1 = buildWorkItem("01/01/2012", focus, tiresPlus)
		WorkItem workItem2 = buildWorkItem("12/31/2012", f150, tiresPlus)
		WorkItem workItem3 = buildWorkItem("01/01/2013", camry, tiresPlus)
		WorkItem workItem4 = buildWorkItem("12/31/2013", f150, tiresPlus)
		WorkItem workItem5 = buildWorkItem("12/01/2013", focus, tiresPlus)
		
		Arrays.asList(workItem1, workItem2, workItem3, workItem4, workItem5)
	}

	WorkItem buildWorkItem(String date, Car focus, Provider tiresPlus) {
		return new WorkItem(focus, Date.parse("MM/dd/yyyy", date), "new tires", tiresPlus, 1000L, 200.00, "");
	}

}
