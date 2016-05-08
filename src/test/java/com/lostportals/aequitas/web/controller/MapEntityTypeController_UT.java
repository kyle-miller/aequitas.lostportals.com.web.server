package com.lostportals.aequitas.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.service.MapEntityTypeService;
import com.lostportals.aequitas.web.domain.MapEntityType;

@RunWith(MockitoJUnitRunner.class)
public class MapEntityTypeController_UT {

	@InjectMocks
	MapEntityTypeController testObj;

	@Mock
	MapEntityTypeService mapEntityService;

	@Test
	public void getAll() {
		List<MapEntityType> expectedList = Arrays.asList(new MapEntityType());
		when(mapEntityService.getAll()).thenReturn(expectedList);

		List<MapEntityType> actualList = testObj.getAll();

		assertEquals(expectedList, actualList);
	}
}
