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

import com.lostportals.aequitas.exception.UnprocessableEntityException;
import com.lostportals.aequitas.service.MapEntityService;
import com.lostportals.aequitas.web.domain.MapEntity;

@RunWith(MockitoJUnitRunner.class)
public class MapEntityController_UT {

	@InjectMocks
	MapEntityController testObj;

	@Mock
	MapEntityService mapEntityService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void save() {
		MapEntity toSave = new MapEntity();
		MapEntity expectedResult = new MapEntity();
		String id = "id";
		expectedResult.setId(id);
		when(mapEntityService.save(toSave)).thenReturn(expectedResult);
		String postUrl = "/api/entitys";
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
		MapEntity toSave = new MapEntity();
		when(mapEntityService.save(toSave)).thenThrow(new UnprocessableEntityException(expectedMessage));

		testObj.post(null, toSave);
	}

	@Test
	public void getAll() {
		List<MapEntity> expectedList = Arrays.asList(new MapEntity());
		when(mapEntityService.getAll()).thenReturn(expectedList);

		List<MapEntity> actualList = testObj.getAll();

		assertEquals(expectedList, actualList);
	}
}
