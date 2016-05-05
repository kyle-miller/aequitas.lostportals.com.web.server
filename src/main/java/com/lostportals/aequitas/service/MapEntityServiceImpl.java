package com.lostportals.aequitas.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;
import com.lostportals.aequitas.web.domain.MapCircle;
import com.lostportals.aequitas.web.domain.MapEntity;
import com.lostportals.aequitas.web.domain.MapEntityType;
import com.lostportals.aequitas.web.domain.MapIcon;
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

		mapMarkerList.forEach(m -> { m.setIcon(mapIconMap.get(m.getIconId())); });

		mapEntityList.forEach(e -> {
			entityEntityTypeXrefList.stream().filter(x -> e.getId().equals(x.getEntityId())).forEach(x -> { e.addType(mapEntityTypeMap.get(x.getEntityTypeId())); });
			mapNoteList.stream().filter(n -> e.getId().equals(n.getEntityId())).forEach(e::addNote);
			mapMarkerList.stream().filter(m -> e.getId().equals(m.getEntityId())).forEach(e::addMarker);
			mapCircleList.stream().filter(c -> e.getId().equals(c.getEntityId())).forEach(e::addCircle);
			mapPolygonList.stream().filter(p -> e.getId().equals(p.getEntityId())).forEach(e::addPolygon);
		});

		return mapEntityList;
	}

	@Override
	public MapEntity save(MapEntity mapEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
