package com.devopsusach2020;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
@AutoConfigureMockMvc
class RestDataTests {
	@Test
	void contextLoads() {
		assertNull(null);
		System.out.println("contextLoads Rest Data Tests");
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	void retrieveGetDataTest() throws Exception {
		String msg = "este es el mensaje";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/mscovid/test").param("msg", msg)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{'deaths':0,'confirmed':0,'date':null,'mensaje':'Mensaje Recibido: " + msg
				+ "','country':null,'recovered':0,'active':0}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	void retrieveGetTotalPaisTest() throws Exception {
		String country = "CL";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/mscovid/estadoPais").param("pais", country)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{'deaths':2989,'confirmed':227882,'date':'2020-05-06t00:00:00z','mensaje':'ok','country':'CL','recovered':104727,'active':11031}";

		// JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
		// false);
	}

	@Test
	void retrieveGetTotalMundialTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/mscovid/estadoMundial")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{'totalConfirmed':68884181,'totalDeaths':1569277,'totalRecovered':44373880}";

		// JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
		// false);
	}
}
