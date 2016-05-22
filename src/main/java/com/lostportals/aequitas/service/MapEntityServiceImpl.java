package com.lostportals.aequitas.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lostportals.aequitas.web.admin.domain.Circle;
import com.lostportals.aequitas.web.admin.domain.Entity;
import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;
import com.lostportals.aequitas.web.admin.domain.Image;
import com.lostportals.aequitas.web.admin.domain.Marker;
import com.lostportals.aequitas.web.admin.domain.Note;
import com.lostportals.aequitas.web.admin.domain.Polygon;
import com.lostportals.aequitas.web.domain.MapCircle;
import com.lostportals.aequitas.web.domain.MapEntity;
import com.lostportals.aequitas.web.domain.MapEntityType;
import com.lostportals.aequitas.web.domain.MapIcon;
import com.lostportals.aequitas.web.domain.MapImage;
import com.lostportals.aequitas.web.domain.MapMarker;
import com.lostportals.aequitas.web.domain.MapNote;
import com.lostportals.aequitas.web.domain.MapPolygon;

@Service
public class MapEntityServiceImpl implements MapEntityService {

	@Autowired
	EntityService entityService;

	@Autowired
	EntityTypeService entityTypeService;

	@Autowired
	EntityEntityTypeXrefService entityEntityTypeXrefService;

	@Autowired
	CircleService circleService;

	@Autowired
	MarkerService markerService;

	@Autowired
	PolygonService polygonService;

	@Autowired
	NoteService noteService;

	@Autowired
	IconService iconService;

	@Autowired
	ImageService imageService;

	@Override
	public List<MapEntity> getAll() {
		List<MapEntity> mapEntityList = entityService.getAll().stream().map(MapEntity::new).collect(Collectors.toList());
		List<EntityEntityTypeXref> entityEntityTypeXrefList = entityEntityTypeXrefService.getAll().stream().collect(Collectors.toList());

		Map<String, MapIcon> mapIconMap = iconService.getAll().stream().map(MapIcon::new).collect(Collectors.toMap(MapIcon::getId, Function.identity()));
		Map<String, MapEntityType> mapEntityTypeMap = entityTypeService.getAll().stream().map(MapEntityType::new).collect(Collectors.toMap(MapEntityType::getId, Function.identity()));
		List<MapNote> mapNoteList = noteService.getAll().stream().map(MapNote::new).collect(Collectors.toList());
		List<MapCircle> mapCircleList = circleService.getAll().stream().map(MapCircle::new).collect(Collectors.toList());
		List<MapPolygon> mapPolygonList = polygonService.getAll().stream().map(MapPolygon::new).collect(Collectors.toList());
		List<MapMarker> mapMarkerList = markerService.getAll().stream().map(MapMarker::new).collect(Collectors.toList());
		List<MapImage> mapImageList = imageService.getAll().stream().map(MapImage::new).collect(Collectors.toList());

		mapMarkerList.forEach(m -> { m.setIcon(mapIconMap.get(m.getIconId())); });

		mapEntityList.forEach(e -> {
			entityEntityTypeXrefList.stream().filter(x -> e.getId().equals(x.getEntityId())).forEach(x -> { e.addType(mapEntityTypeMap.get(x.getEntityTypeId())); });
			mapNoteList.stream().filter(n -> e.getId().equals(n.getEntityId())).forEach(e::addNote);
			mapMarkerList.stream().filter(m -> e.getId().equals(m.getEntityId())).forEach(e::addMarker);
			mapCircleList.stream().filter(c -> e.getId().equals(c.getEntityId())).forEach(e::addCircle);
			mapPolygonList.stream().filter(p -> e.getId().equals(p.getEntityId())).forEach(e::addPolygon);
			mapImageList.stream().filter(i -> e.getId().equals(i.getEntityId())).forEach(e::addImage);
		});

		return mapEntityList;
	}

	@Override
	public MapEntity save(MapEntity mapEntity) {
		validate(mapEntity);
		
		// TODO - This method needs to roll back on fail
		Entity entity = new Entity(mapEntity);
		entity = entityService.save(entity);
		MapEntity savedMapEntity = new MapEntity(entity);
		
		List<MapEntityType> mapEntityTypes = mapEntity.getTypes();
		if (!CollectionUtils.isEmpty(mapEntityTypes)) {
			for (MapEntityType mapEntityType : mapEntityTypes) {
				EntityEntityTypeXref entityEntityTypeXref = new EntityEntityTypeXref();
				entityEntityTypeXref.setId(mapEntityType.getId());
				entityEntityTypeXref.setEntityId(entity.getId());
				entityEntityTypeXref.setEntityTypeId(mapEntityType.getId());
				entityEntityTypeXrefService.save(entityEntityTypeXref);
				savedMapEntity.addType(mapEntityType);
			}
		}
		
		List<MapMarker> mapMarkers = mapEntity.getMarkers();
		if (!CollectionUtils.isEmpty(mapMarkers)) {
			Map<String, MapIcon> mapIconMap = iconService.getAll().stream().map(MapIcon::new).collect(Collectors.toMap(MapIcon::getId, Function.identity()));
			for (MapMarker mapMarker : mapMarkers) {
				Marker marker = new Marker(mapMarker);
				marker.setEntityId(entity.getId());
				marker = markerService.save(marker);
				MapMarker savedMapMarker = new MapMarker(marker);
				savedMapMarker.setIcon(mapIconMap.get(savedMapMarker.getIconId()));
				savedMapEntity.addMarker(savedMapMarker);
			}
		}

		List<MapCircle> mapCircles = mapEntity.getCircles();
		if (!CollectionUtils.isEmpty(mapCircles)) {
			for (MapCircle mapCircle : mapCircles) {
				Circle circle = new Circle(mapCircle);
				circle.setEntityId(entity.getId());
				circle = circleService.save(circle);
				savedMapEntity.addCircle(new MapCircle(circle));
			}
		}

		List<MapPolygon> mapPolygons = mapEntity.getPolygons();
		if (!CollectionUtils.isEmpty(mapPolygons)) {
			for (MapPolygon mapPolygon : mapPolygons) {
				Polygon polygon = new Polygon(mapPolygon);
				polygon.setEntityId(entity.getId());
				polygon = polygonService.save(polygon);
				savedMapEntity.addPolygon(new MapPolygon(polygon));
			}
		}

		List<MapNote> mapNotes = mapEntity.getNotes();
		if (!CollectionUtils.isEmpty(mapNotes)) {
			for (int i = 0; i < mapNotes.size(); i++) {
				MapNote mapNote = mapNotes.get(i);
				Note note = new Note(mapNote);
				note.setEntityId(entity.getId());
				note.setPosition(i);
				note = noteService.save(note);
				savedMapEntity.addNote(new MapNote(note));
			}
		}

		List<MapImage> mapImages = mapEntity.getImages();
		if (!CollectionUtils.isEmpty(mapImages)) {
			for (MapImage mapImage : mapImages) {
				Image image = new Image(mapImage);
				image.setEntityId(entity.getId());
				image = imageService.save(image);
				savedMapEntity.addImage(new MapImage(image));
			}
		}
		
		return savedMapEntity;
	}

	void validate(MapEntity mapEntity) {
		// TODO Auto-generated method stub
	}

}
