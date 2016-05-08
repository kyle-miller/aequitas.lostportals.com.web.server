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
import com.lostportals.aequitas.service.ImageService;
import com.lostportals.aequitas.web.admin.domain.Image;

@RunWith(MockitoJUnitRunner.class)
public class ImageController_UT {

	@InjectMocks
	ImageController testObj;

	@Mock
	ImageService imageService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void save() {
		Image toSave = new Image();
		Image expectedResult = new Image();
		String id = "id";
		expectedResult.setId(id);
		when(imageService.save(toSave)).thenReturn(expectedResult);
		String postUrl = "/api/images";
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
		Image toSave = new Image();
		when(imageService.save(toSave)).thenThrow(new UnprocessableEntityException(expectedMessage));

		testObj.post(null, toSave);
	}

	@Test
	public void getAll() {
		List<Image> expectedList = Arrays.asList(new Image());
		when(imageService.getAll()).thenReturn(expectedList);

		List<Image> actualList = testObj.getAll();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void get() {
		String id = "id";
		Image expectedResult = new Image();
		when(imageService.get(id)).thenReturn(expectedResult);

		Image actualEntityType = testObj.get(id);

		assertEquals(expectedResult, actualEntityType);
	}

	@Test
	public void get_rethrowException() {
		expectedException.expect(NotFoundException.class);
		String expectedMessage = "Not found";
		expectedException.expectMessage(expectedMessage);
		String id = "id";
		when(imageService.get(id)).thenThrow(new NotFoundException(expectedMessage));

		testObj.get(id);
	}
}
