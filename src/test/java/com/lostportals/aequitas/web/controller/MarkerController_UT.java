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
import com.lostportals.aequitas.service.MarkerService;
import com.lostportals.aequitas.web.domain.Marker;

@RunWith(MockitoJUnitRunner.class)
public class MarkerController_UT {

	@InjectMocks
	MarkerController testObj;

	@Mock
	MarkerService markerService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void save() {
		Marker toSave = new Marker();
		Marker expectedResult = new Marker();
		String id = "id";
		expectedResult.setId(id);
		when(markerService.save(toSave)).thenReturn(expectedResult);
		String postUrl = "/api/markers";
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
		Marker toSave = new Marker();
		when(markerService.save(toSave)).thenThrow(new UnprocessableEntityException(expectedMessage));

		testObj.post(null, toSave);
	}

	@Test
	public void getAll() {
		List<Marker> expectedList = Arrays.asList(new Marker());
		when(markerService.getAll()).thenReturn(expectedList);

		List<Marker> actualList = testObj.getAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void get() {
		String id = "id";
		Marker expectedResult = new Marker();
		when(markerService.get(id)).thenReturn(expectedResult);

		Marker actualEntityType = testObj.get(id);

		assertEquals(expectedResult, actualEntityType);
	}

	@Test
	public void get_rethrowException() {
		expectedException.expect(NotFoundException.class);
		String expectedMessage = "Not found";
		expectedException.expectMessage(expectedMessage);
		String id = "id";
		when(markerService.get(id)).thenThrow(new NotFoundException(expectedMessage));

		testObj.get(id);
	}
}
