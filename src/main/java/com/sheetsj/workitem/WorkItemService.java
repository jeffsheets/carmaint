package com.sheetsj.workitem;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;

@Service
public class WorkItemService {

	private WorkItemRepository workItemRepository;
	
	/**
	 * Yes, this could be done in SQL, but sometimes you just need an example for presentations!
	 */
	public Collection<WorkItem> findAllByMakeAndYear(final String carMake, final String year) {
		List<WorkItem> allWorkItems = workItemRepository.findAll();
		
		return selectWorkItemsByMakeAndYear(carMake, year, allWorkItems);
	}

	protected Collection<WorkItem> selectWorkItemsByMakeAndYear(final String carMake, final String year, List<WorkItem> allWorkItems) {
		@SuppressWarnings("unchecked")
		Collection<WorkItem> results = CollectionUtils.select(allWorkItems, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				WorkItem item = (WorkItem) object;
				
				return carMake.equalsIgnoreCase(item.getCar().getMake().getName()) && year.equalsIgnoreCase(getYearAsString(item.getWorkDate()));
			}
		});
		return results;
	}

	protected String getYearAsString(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.YEAR));
	}
}
