package com.allen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ConsumerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(
			fallbackMethod="ribbonAddFallback",//指定回调方法
			threadPoolProperties={
					@HystrixProperty(name="coreSize",value="30"),
					@HystrixProperty(name="maxQueueSize",value="100"),
					@HystrixProperty(name="queueSizeRejectionThreshold",value="20")
			},
			commandProperties={
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="100"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="50")
			}
	)
	public String ribbonAdd(){
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
