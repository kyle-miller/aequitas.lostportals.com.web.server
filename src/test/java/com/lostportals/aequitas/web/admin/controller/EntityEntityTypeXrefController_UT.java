package com.lostportals.aequitas.web.admin.controller;

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
import com.lostportals.aequitas.service.EntityEntityTypeXrefService;
import com.lostportals.aequitas.web.admin.domain.EntityEntityTypeXref;

@RunWith(MockitoJUnitRunner.class)
public class EntityEntityTypeXrefController_UT {

	@InjectMocks
	EntityEntityTypeXrefController testObj;

	@Mock
	EntityEntityTypeXrefService entityEntityTypeXrefService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void save() {
		EntityEntityTypeXref toSave = new EntityEntityTypeXref();
		EntityEntityTypeXref expectedResult = new EntityEntityTypeXref();
		String id = "id";
		expectedResult.setId(id);
		when(entityEntityTypeXrefService.save(toSave)).thenReturn(expectedResult);
		String postUrl = "/api/entityEntityTypeXrefs";
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
		EntityEntityTypeXref toSave = new EntityEntityTypeXref();
		when(entityEntityTypeXrefService.save(toSave)).thenThrow(new UnprocessableEntityException(expectedMessage));

		testObj.post(null, toSave);
	}

	@Test
	public void getAll() {
		List<EntityEntityTypeXref> expectedList = Arrays.asList(new EntityEntityTypeXref());
		when(entityEntityTypeXrefService.getAll()).thenReturn(expectedList);

		List<EntityEntityTypeXref> actualList = testObj.getAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void get() {
		String id = "id";
		EntityEntityTypeXref expectedResult = new EntityEntityTypeXref();
		when(entityEntityTypeXrefService.get(id)).thenReturn(expectedResult);

		EntityEntityTypeXref actualEntityType = testObj.get(id);

		assertEquals(expectedResult, actualEntityType);
	}

	@Test
	public void get_rethrowException() {
		expectedException.expect(NotFoundException.class);
		String expectedMessage = "Not found";
		expectedException.expectMessage(expectedMessage);
		String id = "id";
		when(entityEntityTypeXrefService.get(id)).thenThrow(new NotFoundException(expectedMessage));

		testObj.get(id);
	}
}
