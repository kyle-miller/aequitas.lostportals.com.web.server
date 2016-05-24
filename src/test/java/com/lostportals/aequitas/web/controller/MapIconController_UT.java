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

import com.lostportals.aequitas.service.MapIconService;
import com.lostportals.aequitas.web.domain.MapIcon;

@RunWith(MockitoJUnitRunner.class)
public class MapIconController_UT {

	@InjectMocks
	MapIconController testObj;

	@Mock
	MapIconService mapEntityService;

	@Test
	public void getAll() {
		List<MapIcon> expectedList = Arrays.asList(new MapIcon());
		when(mapEntityService.getAll()).thenReturn(expectedList);

		List<MapIcon> actualList = testObj.getAll();

		assertEquals(expectedList, actualList);
	}
}
