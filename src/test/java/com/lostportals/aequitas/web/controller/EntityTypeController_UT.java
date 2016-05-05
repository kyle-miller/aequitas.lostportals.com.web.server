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
import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.service.EntityTypeService;
import com.lostportals.aequitas.web.admin.domain.EntityType;

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
		EntityType toSave = new EntityType();
		EntityType expectedResult = new EntityType();
		String id = "id";
		expectedResult.setId(id);
		when(entityTypeService.save(toSave)).thenReturn(expectedResult);
		String postUrl = "/api/entityTypes";
		MockHttpServletRequest mockRequest = new MockHttpServletRequest(HttpMethod.POST.toString(), postUrl);

		ResponseEntity<Void> actualResponse = testObj.post(mockRequest, toSave);

		assertNotNull(actualResponse);
		assertEquals(actualResponse.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(actualResponse.getHeaders());
		assertEquals(Collections.singletonList(postUrl + "/" + id), actualResponse.getHeaders().get("location"));
	}

	@Test
	public void save_rethrowException() {
		expectedException.expect(UnprocessableEntityException.class);
		String expectedMessage = "Error Saving";
		expectedException.expectMessage(expectedMessage);
		EntityType toSave = new EntityType();
		when(entityTypeService.save(toSave)).thenThrow(new UnprocessableEntityException(expectedMessage));

		testObj.post(null, toSave);
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
		EntityType expectedResult = new EntityType();
		when(entityTypeService.get(id)).thenReturn(expectedResult);

		EntityType actualEntityType = testObj.get(id);

		assertEquals(expectedResult, actualEntityType);
	}

	@Test
	public void get_rethrowException() {
		expectedException.expect(NotFoundException.class);
		String expectedMessage = "Not found";
		expectedException.expectMessage(expectedMessage);
		String id = "id";
		when(entityTypeService.get(id)).thenThrow(new NotFoundException(expectedMessage));

		testObj.get(id);
	}
}
