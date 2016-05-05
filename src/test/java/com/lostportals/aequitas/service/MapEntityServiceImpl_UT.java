package com.lostportals.aequitas.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Circle;
import com.lostportals.aequitas.web.admin.domain.Entity;
import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;
import com.lostportals.aequitas.web.admin.domain.EntityType;
import com.lostportals.aequitas.web.admin.domain.Marker;
import com.lostportals.aequitas.web.admin.domain.Note;
import com.lostportals.aequitas.web.admin.domain.Polygon;
import com.lostportals.aequitas.web.domain.MapCircle;
import com.lostportals.aequitas.web.domain.MapEntity;
import com.lostportals.aequitas.web.domain.MapEntityType;
import com.lostportals.aequitas.web.domain.MapMarker;
import com.lostportals.aequitas.web.domain.MapNote;
import com.lostportals.aequitas.web.domain.MapPolygon;

@RunWith(MockitoJUnitRunner.class)
public class MapEntityServiceImpl_UT {
	static final String entityId = UUID.randomUUID().toString();

	@InjectMocks
	MapEntityServiceImpl testObj;

	@Mock
	EntityService entityService;

	@Mock
	EntityTypeService entityTypeService;

	@Mock
	EntityEntityTypeXrefService entityEntityTypeXrefService;

	@Mock
	CircleService circleService;

	@Mock
	MarkerService markerService;

	@Mock
	PolygonService polygonService;

	@Mock
	NoteService noteService;

	@Mock
	IconService iconService;

	@Test
	public void getAll_noEntities() {
		when(entityService.getAll()).thenReturn(Collections.emptyList());

		List<MapEntity> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(0, actualList.size());
		verify(entityService).getAll();
		verify(entityTypeService).getAll();
		verify(entityEntityTypeXrefService).getAll();
		verify(circleService).getAll();
		verify(markerService).getAll();
		verify(polygonService).getAll();
		verify(noteService).getAll();
		verify(iconService).getAll();
		verifyNoMoreInteractions(entityService);
	}

	@Test
	public void getAll_entityTypeToEntity() {
		Entity entity = createEntity();
		when(entityService.getAll()).thenReturn(Collections.singletonList(entity));
		EntityEntityTypeXref matchingXref = new EntityEntityTypeXref();
		matchingXref.setEntityId(entityId);
		String matchingId = UUID.randomUUID().toString();
		matchingXref.setEntityTypeId(matchingId);
		EntityEntityTypeXref nonMatchingXref = new EntityEntityTypeXref();
		String nonMatchingEntityId = entityId + "nonMatching";
		nonMatchingXref.setEntityId(nonMatchingEntityId);
		String nonMatchingId = matchingId + "nonMatching";
		nonMatchingXref.setEntityTypeId(nonMatchingId);
		when(entityEntityTypeXrefService.getAll()).thenReturn(Arrays.asList(matchingXref, nonMatchingXref));
		EntityType matchingEntityType = new EntityType();
		matchingEntityType.setId(matchingId);
		EntityType nonMatchingEntityType = new EntityType();
		nonMatchingEntityType.setId(UUID.randomUUID().toString());
		when(entityTypeService.getAll()).thenReturn(Arrays.asList(matchingEntityType, nonMatchingEntityType));

		List<MapEntity> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(1, actualList.size());
		MapEntity actualMapEntity = actualList.get(0);
		assertNotNull(actualMapEntity);
		assertEquals(entity.getId(), actualMapEntity.getId());
		List<MapEntityType> mapTypesList = actualMapEntity.getTypes();
		assertNotNull(mapTypesList);
		assertEquals(1, mapTypesList.size());
		MapEntityType actualMapEntityType = mapTypesList.get(0);
		assertNotNull(actualMapEntityType);
		assertEquals(matchingEntityType.getId(), actualMapEntityType.getId());
	}

	@Test
	public void getAll_circleToEntity() {
		Entity entity = createEntity();
		when(entityService.getAll()).thenReturn(Collections.singletonList(entity));
		Circle matching = new Circle();
		matching.setId(UUID.randomUUID().toString());
		matching.setEntityId(entityId);
		Circle nonMatching = new Circle();
		nonMatching.setId(UUID.randomUUID().toString());
		String nonMatchingEntityId = entityId + "nonMatching";
		nonMatching.setEntityId(nonMatchingEntityId);
		when(circleService.getAll()).thenReturn(Arrays.asList(matching, nonMatching));

		List<MapEntity> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(1, actualList.size());
		MapEntity actualMapEntity = actualList.get(0);
		assertNotNull(actualMapEntity);
		assertEquals(entity.getId(), actualMapEntity.getId());
		List<MapCircle> objList = actualMapEntity.getCircles();
		assertNotNull(objList);
		assertEquals(1, objList.size());
		MapCircle actualObj = objList.get(0);
		assertNotNull(actualObj);
		assertEquals(matching.getId(), actualObj.getId());
	}

	@Test
	public void getAll_markerToEntity() {
		Entity entity = createEntity();
		when(entityService.getAll()).thenReturn(Collections.singletonList(entity));
		Marker matching = new Marker();
		matching.setId(UUID.randomUUID().toString());
		matching.setEntityId(entityId);
		Marker nonMatching = new Marker();
		nonMatching.setId(UUID.randomUUID().toString());
		String nonMatchingEntityId = entityId + "nonMatching";
		nonMatching.setEntityId(nonMatchingEntityId);
		when(markerService.getAll()).thenReturn(Arrays.asList(matching, nonMatching));

		List<MapEntity> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(1, actualList.size());
		MapEntity actualMapEntity = actualList.get(0);
		assertNotNull(actualMapEntity);
		assertEquals(entity.getId(), actualMapEntity.getId());
		List<MapMarker> objList = actualMapEntity.getMarkers();
		assertNotNull(objList);
		assertEquals(1, objList.size());
		MapMarker actualObj = objList.get(0);
		assertNotNull(actualObj);
		assertEquals(matching.getId(), actualObj.getId());
	}

	@Test
	public void getAll_noteToEntity() {
		Entity entity = createEntity();
		when(entityService.getAll()).thenReturn(Collections.singletonList(entity));
		Note matching = new Note();
		matching.setId(UUID.randomUUID().toString());
		matching.setEntityId(entityId);
		Note nonMatching = new Note();
		nonMatching.setId(UUID.randomUUID().toString());
		String nonMatchingEntityId = entityId + "nonMatching";
		nonMatching.setEntityId(nonMatchingEntityId);
		when(noteService.getAll()).thenReturn(Arrays.asList(matching, nonMatching));

		List<MapEntity> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(1, actualList.size());
		MapEntity actualMapEntity = actualList.get(0);
		assertNotNull(actualMapEntity);
		assertEquals(entity.getId(), actualMapEntity.getId());
		List<MapNote> objList = actualMapEntity.getNotes();
		assertNotNull(objList);
		assertEquals(1, objList.size());
		MapNote actualObj = objList.get(0);
		assertNotNull(actualObj);
		assertEquals(matching.getId(), actualObj.getId());
	}

	@Test
	public void getAll_polygonToEntity() {
		Entity entity = createEntity();
		when(entityService.getAll()).thenReturn(Collections.singletonList(entity));
		Polygon matching = new Polygon();
		matching.setId(UUID.randomUUID().toString());
		matching.setEntityId(entityId);
		Polygon nonMatching = new Polygon();
		nonMatching.setId(UUID.randomUUID().toString());
		String nonMatchingEntityId = entityId + "nonMatching";
		nonMatching.setEntityId(nonMatchingEntityId);
		when(polygonService.getAll()).thenReturn(Arrays.asList(matching, nonMatching));

		List<MapEntity> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(1, actualList.size());
		MapEntity actualMapEntity = actualList.get(0);
		assertNotNull(actualMapEntity);
		assertEquals(entity.getId(), actualMapEntity.getId());
		List<MapPolygon> objList = actualMapEntity.getPolygons();
		assertNotNull(objList);
		assertEquals(1, objList.size());
		MapPolygon actualObj = objList.get(0);
		assertNotNull(actualObj);
		assertEquals(matching.getId(), actualObj.getId());
	}

	private Entity createEntity() {
		Entity entity = new Entity();
		entity.setId(entityId);
		entity.setTitle("title");
		return entity;
	}
}
