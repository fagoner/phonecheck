package com.deviddle.phonecheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.deviddle.phonecheck.phone.CheckRequest;
import com.deviddle.phonecheck.phone.CheckResponse;
import com.deviddle.phonecheck.phone.PhoneLibService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PhonecheckApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	PhoneLibService phoneLibService;

	@Test
	void testingPhoneLibService_WithValidPhone() {
		CheckRequest cr = goodRequest();
		CheckResponse pn = phoneLibService.check(cr);
		assertTrue(pn.getCountryCode() == 502);
		assertTrue(pn.isValid());
	}

	@Test
	void testingPhoneLibService_WithInValidPhone() {
		CheckRequest cr = badRequest();
		CheckResponse checkResult = phoneLibService.check(cr);
		assertFalse(checkResult.isValid());
	}

	@Test
	void checkEndpoint() throws Exception {
		CheckRequest cr = goodRequest();

		mockMvc.perform(MockMvcRequestBuilders.post("/")
				.content(asJsonString(cr))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(true));
	}

	@Test
	void checkEndpoint_WithInvalidInput() throws Exception {
		CheckRequest cr = badRequest();

		mockMvc.perform(MockMvcRequestBuilders.post("/")
				.content(asJsonString(cr))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(false));
	}

	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private CheckRequest goodRequest() {
		String input = "+50241523547";
		CheckRequest cr = new CheckRequest();
		cr.setNumber(input);
		return cr;
	}

	private CheckRequest badRequest() {
		String input = "+50241a";
		CheckRequest cr = new CheckRequest();
		cr.setNumber(input);
		return cr;
	}
	
}
