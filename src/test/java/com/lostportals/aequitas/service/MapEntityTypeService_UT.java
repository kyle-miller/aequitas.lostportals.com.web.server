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

import com.lostportals.aequitas.web.admin.domain.EntityType;
import com.lostportals.aequitas.web.domain.MapEntityType;

@RunWith(MockitoJUnitRunner.class)
public class MapEntityTypeService_UT {

	@InjectMocks
	MapEntityTypeService testObj;

	@Mock
	EntityTypeService iconService;

	@Test
	public void getAll() {
		List<EntityType> objects = Arrays.asList(createEntityType(), createEntityType());
		when(iconService.getAll()).thenReturn(objects);

		List<MapEntityType> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(objects.size(), actualList.size());
		for (int i = 0; i < objects.size(); i++) {
			assertEquals(objects.get(i).getId(), actualList.get(i).getId());
		}
	}

	EntityType createEntityType() {
		EntityType obj = new EntityType();
		obj.setId(UUID.randomUUID().toString());
		obj.setName(UUID.randomUUID().toString());
		return obj;
	}
}
