package com.lostportals.aequitas.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.lostportals.aequitas.exception.NotFoundException;
import com.lostportals.aequitas.exception.ValidationException;
import com.lostportals.aequitas.service.EntityTypeService;
import com.lostportals.aequitas.web.domain.EntityType;

@RunWith(MockitoJUnitRunner.class)
public class EntityTypeController_UT {

	@InjectMocks
	EntityTypeController testObj;

	@Mock
	EntityTypeService entityTypeService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void save() {
		EntityType entityTypeToSave = new EntityType();
		EntityType returnedEntityType = new EntityType();
		String id = "id";
		returnedEntityType.setId(id);
		when(entityTypeService.save(entityTypeToSave)).thenReturn(returnedEntityType);
		String postUrl = "/api/entityTypes";
		MockHttpServletRequest mockRequest = new MockHttpServletRequest(HttpMethod.POST.toString(), postUrl);

		ResponseEntity<Void> actualResponse = testObj.post(mockRequest, entityTypeToSave);

		assertNotNull(actualResponse);
		assertEquals(actualResponse.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(actualResponse.getHeaders());
		assertEquals(Collections.singletonList(postUrl + "/" + id), actualResponse.getHeaders().get("location"));
	}

	@Test
	public void save_rethrowException() {
		expectedException.expect(ValidationException.class);
		String expectedMessage = "Entity not found";
		expectedException.expectMessage(expectedMessage);
		EntityType entityTypeToSave = new EntityType();
		when(entityTypeService.save(entityTypeToSave)).thenThrow(new ValidationException(expectedMessage));

		testObj.post(null, entityTypeToSave);
	}

	@Test
	public void getAll() {
		List<EntityType> expectedList = Arrays.asList(new EntityType());
		when(entityTypeService.getAll()).thenReturn(expectedList);

		List<EntityType> actualList = testObj.getAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void get() {
		String id = "id";
		EntityType expectedEntityType = new EntityType();
		when(entityTypeService.get(id)).thenReturn(expectedEntityType);

		EntityType actualEntityType = testObj.get(id);

		assertEquals(expectedEntityType, actualEntityType);
	}

	@Test
	public void get_rethrowException() {
		expectedException.expect(NotFoundException.class);
		String expectedMessage = "Entity not found";
		expectedException.expectMessage(expectedMessage);
		String id = "id";
		when(entityTypeService.get(id)).thenThrow(new NotFoundException(expectedMessage));

		testObj.get(id);
	}
}
