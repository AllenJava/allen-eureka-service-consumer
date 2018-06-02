package com.allen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test(){
		System.out.println(restTemplate.postForEntity("http://provider-service/api/order/queryList",null,String.class).getBody());
	}

}
