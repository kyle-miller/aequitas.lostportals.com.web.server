package com.lostportals.aequitas.service;

import static java.math.RoundingMode.DOWN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.lostportals.aequitas.web.admin.domain.Circle;
import com.lostportals.aequitas.web.admin.domain.Entity;
import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;
import com.lostportals.aequitas.web.admin.domain.EntityType;
import com.lostportals.aequitas.web.admin.domain.Image;
import com.lostportals.aequitas.web.admin.domain.Marker;
import com.lostportals.aequitas.web.admin.domain.Note;
import com.lostportals.aequitas.web.admin.domain.Polygon;
import com.lostportals.aequitas.web.domain.MapCircle;
import com.lostportals.aequitas.web.domain.MapEntity;
import com.lostportals.aequitas.web.domain.MapEntityType;
import com.lostportals.aequitas.web.domain.MapImage;
import com.lostportals.aequitas.web.domain.MapMarker;
import com.lostportals.aequitas.web.domain.MapNote;
import com.lostportals.aequitas.web.domain.MapPolygon;

@RunWith(MockitoJUnitRunner.class)
public class MapEntityService_UT {
	static final String entityId = UUID.randomUUID().toString();

	@InjectMocks
	@Spy
	MapEntityService testObj;

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

	@Mock
	ImageService imageService;

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
		verify(imageService).getAll();
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
		matching.setPosition(0);
		Note nonMatching = new Note();
		nonMatching.setId(UUID.randomUUID().toString());
		String nonMatchingEntityId = entityId + "nonMatching";
		nonMatching.setEntityId(nonMatchingEntityId);
		nonMatching.setPosition(1);
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
	public void getAll_imageToEntity() {
		Entity entity = createEntity();
		when(entityService.getAll()).thenReturn(Collections.singletonList(entity));
		Image matching = new Image();
		matching.setId(UUID.randomUUID().toString());
		matching.setEntityId(entityId);
		Image nonMatching = new Image();
		nonMatching.setId(UUID.randomUUID().toString());
		String nonMatchingEntityId = entityId + "nonMatching";
		nonMatching.setEntityId(nonMatchingEntityId);
		when(imageService.getAll()).thenReturn(Arrays.asList(matching, nonMatching));

		List<MapEntity> actualList = testObj.getAll();

		assertNotNull(actualList);
		assertEquals(1, actualList.size());
		MapEntity actualMapEntity = actualList.get(0);
		assertNotNull(actualMapEntity);
		assertEquals(entity.getId(), actualMapEntity.getId());
		List<MapImage> objList = actualMapEntity.getImages();
		assertNotNull(objList);
		assertEquals(1, objList.size());
		MapImage actualObj = objList.get(0);
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

	@Test
	public void save_entityAndTypeXrefsSaved() {
		MapEntity mapEntityToSave = new MapEntity(createEntity());
		doNothing().when(testObj).validate(mapEntityToSave);
		mapEntityToSave.addType(new MapEntityType());
		mapEntityToSave.addType(new MapEntityType());
		Entity savedEntity = new Entity(mapEntityToSave);
		when(entityService.save(any(Entity.class))).thenReturn(savedEntity);

		MapEntity returnedMapEntity = testObj.save(mapEntityToSave);

		assertNotNull(returnedMapEntity);
		assertEquals(savedEntity.getId(), returnedMapEntity.getId());
		assertNotNull(returnedMapEntity.getTypes());
		assertEquals(2, returnedMapEntity.getTypes().size());
		verify(entityService).save(any(Entity.class));
		verify(entityEntityTypeXrefService, times(2)).save(any(EntityEntityTypeXref.class));
	}

	@Test
	public void save_markersSaved() {
		MapEntity mapEntityToSave = new MapEntity(createEntity());
		doNothing().when(testObj).validate(mapEntityToSave);
		MapMarker mapMarker1 = createMapMarker();
		mapEntityToSave.addMarker(mapMarker1);
		MapMarker mapMarker2 = createMapMarker();
		mapEntityToSave.addMarker(mapMarker2);
		Entity savedEntity = new Entity(mapEntityToSave);
		when(entityService.save(any(Entity.class))).thenReturn(savedEntity);
		Marker savedMarker1 = new Marker(mapMarker1);
		Marker savedMarker2 = new Marker(mapMarker2);
		when(markerService.save(any(Marker.class))).thenReturn(savedMarker1).thenReturn(savedMarker2);

		MapEntity returnedMapEntity = testObj.save(mapEntityToSave);

		assertNotNull(returnedMapEntity);
		assertNotNull(returnedMapEntity.getMarkers());
		assertEquals(2, returnedMapEntity.getMarkers().size());
		verify(markerService, times(2)).save(any(Marker.class));
	}

	@Test
	public void save_circlesSaved() {
		MapEntity mapEntityToSave = new MapEntity(createEntity());
		doNothing().when(testObj).validate(mapEntityToSave);
		MapCircle mapCircle1 = createMapCircle();
		mapEntityToSave.addCircle(mapCircle1);
		MapCircle mapCircle2 = createMapCircle();
		mapEntityToSave.addCircle(mapCircle2);
		Entity savedEntity = new Entity(mapEntityToSave);
		when(entityService.save(any(Entity.class))).thenReturn(savedEntity);
		Circle savedCircle1 = new Circle(mapCircle1);
		Circle savedCircle2 = new Circle(mapCircle2);
		when(circleService.save(any(Circle.class))).thenReturn(savedCircle1).thenReturn(savedCircle2);

		MapEntity returnedMapEntity = testObj.save(mapEntityToSave);

		assertNotNull(returnedMapEntity);
		assertNotNull(returnedMapEntity.getCircles());
		assertEquals(2, returnedMapEntity.getCircles().size());
		verify(circleService, times(2)).save(any(Circle.class));
	}

	@Test
	public void save_polygonsSaved() {
		MapEntity mapEntityToSave = new MapEntity(createEntity());
		doNothing().when(testObj).validate(mapEntityToSave);
		MapPolygon mapPolygon1 = createMapPolygon();
		mapEntityToSave.addPolygon(mapPolygon1);
		MapPolygon mapPolygon2 = createMapPolygon();
		mapEntityToSave.addPolygon(mapPolygon2);
		Entity savedEntity = new Entity(mapEntityToSave);
		when(entityService.save(any(Entity.class))).thenReturn(savedEntity);
		Polygon savedPolygon1 = new Polygon(mapPolygon1);
		Polygon savedPolygon2 = new Polygon(mapPolygon2);
		when(polygonService.save(any(Polygon.class))).thenReturn(savedPolygon1).thenReturn(savedPolygon2);

		MapEntity returnedMapEntity = testObj.save(mapEntityToSave);

		assertNotNull(returnedMapEntity);
		assertNotNull(returnedMapEntity.getPolygons());
		assertEquals(2, returnedMapEntity.getPolygons().size());
		verify(polygonService, times(2)).save(any(Polygon.class));
	}

	@Test
	public void save_notesSaved() {
		MapEntity mapEntityToSave = new MapEntity(createEntity());
		doNothing().when(testObj).validate(mapEntityToSave);
		MapNote mapNote1 = createMapNote();
		mapEntityToSave.addNote(mapNote1);
		MapNote mapNote2 = createMapNote();
		mapEntityToSave.addNote(mapNote2);
		Entity savedEntity = new Entity(mapEntityToSave);
		when(entityService.save(any(Entity.class))).thenReturn(savedEntity);
		Note savedNote1 = new Note(mapNote1);
		Note savedNote2 = new Note(mapNote2);
		when(noteService.save(any(Note.class))).thenReturn(savedNote1).thenReturn(savedNote2);

		MapEntity returnedMapEntity = testObj.save(mapEntityToSave);

		assertNotNull(returnedMapEntity);
		assertNotNull(returnedMapEntity.getNotes());
		assertEquals(2, returnedMapEntity.getNotes().size());
		verify(noteService, times(2)).save(any(Note.class));
	}

	@Test
	public void save_imagesSaved() {
		MapEntity mapEntityToSave = new MapEntity(createEntity());
		doNothing().when(testObj).validate(mapEntityToSave);
		MapImage mapImage1 = createMapImage();
		mapEntityToSave.addImage(mapImage1);
		MapImage mapImage2 = createMapImage();
		mapEntityToSave.addImage(mapImage2);
		Entity savedEntity = new Entity(mapEntityToSave);
		when(entityService.save(any(Entity.class))).thenReturn(savedEntity);
		Image savedImage1 = new Image(mapImage1);
		Image savedImage2 = new Image(mapImage2);
		when(imageService.save(any(Image.class))).thenReturn(savedImage1).thenReturn(savedImage2);

		MapEntity returnedMapEntity = testObj.save(mapEntityToSave);

		assertNotNull(returnedMapEntity);
		assertNotNull(returnedMapEntity.getImages());
		assertEquals(2, returnedMapEntity.getImages().size());
		verify(imageService, times(2)).save(any(Image.class));
	}

	private MapImage createMapImage() {
		MapImage mapImage = new MapImage();
		mapImage.setId(UUID.randomUUID().toString());
		mapImage.setUrl(UUID.randomUUID().toString());
		return mapImage;
	}

	private MapNote createMapNote() {
		MapNote mapNote = new MapNote();
		mapNote.setId(UUID.randomUUID().toString());
		mapNote.setNote(UUID.randomUUID().toString());
		return mapNote;
	}

	private MapPolygon createMapPolygon() {
		MapPolygon mapPolygon = new MapPolygon();
		mapPolygon.setId(UUID.randomUUID().toString());
		mapPolygon.setFillColor(UUID.randomUUID().toString());
		mapPolygon.setOutlineColor(UUID.randomUUID().toString());
		mapPolygon.setVertices(UUID.randomUUID().toString());
		return mapPolygon;
	}

	private MapCircle createMapCircle() {
		MapCircle mapCircle = new MapCircle();
		mapCircle.setId(UUID.randomUUID().toString());
		mapCircle.setFillColor(UUID.randomUUID().toString());
		mapCircle.setOutlineColor(UUID.randomUUID().toString());
		mapCircle.setRadius(Double.valueOf(Math.random() * 100).intValue());
		mapCircle.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		mapCircle.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		return mapCircle;
	}

	private MapMarker createMapMarker() {
		MapMarker mapMarker = new MapMarker();
		mapMarker.setId(UUID.randomUUID().toString());
		mapMarker.setIconId(UUID.randomUUID().toString());
		mapMarker.setLatitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		mapMarker.setLongitude(new BigDecimal(Double.toString(Math.random() * 100)).setScale(8, DOWN));
		return mapMarker;
	}

	private Entity createEntity() {
		Entity entity = new Entity();
		entity.setId(entityId);
		entity.setTitle("title");
		return entity;
	}
}
