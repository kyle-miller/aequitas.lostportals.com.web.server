package com.lostportals.aequitas.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Icon;
import com.lostportals.aequitas.web.domain.MapIcon;

@RunWith(MockitoJUnitRunner.class)
public class MapIconServiceImpl_UT {

	@InjectMocks
	MapIconServiceImpl testObj;

	@Mock
	IconService iconService;

	@Test
	public void getAll() {
		List<Icon> objects = Arrays.asList(createIcon(), createIcon());
		when(iconService.getAll()).thenReturn(objects);

		List<MapIcon> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(objects.size(), actualList.size());
		for (int i = 0; i < objects.size(); i++) {
			assertEquals(objects.get(i).getId(), actualList.get(i).getId());
		}
	}

	Icon createIcon() {
		Icon obj = new Icon();
		obj.setId(UUID.randomUUID().toString());
		obj.setName(UUID.randomUUID().toString());
		obj.setUrl(UUID.randomUUID().toString());
		return obj;
	}
}
