package com.allen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConsumerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="ribbonAddFallback")//指定回调方法
	public String ribbonAdd() throws InterruptedException{
		Thread.sleep(5000L);
		return restTemplate.getForEntity("http://provider-service/add?a=10&b=20", String.class).getBody();
	}
	
	/**
	 * 
	* @Title: ribbonAddFallback
	* @Description: 服务异常时，指定回调方法
	* @param @return
	* @return String
	* @throws
	 */
	public String ribbonAddFallback(){
		return "error";
	}

}
