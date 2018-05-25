package com.allen.service;

import java.util.HashMap;
import java.util.Map;

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
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000"),//设置调用者等待命令执行的超时限制，超过此时间，HystrixCommand被标记为TIMEOUT，并执行回退逻辑
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="50")//设置在一个滚动窗口中，打开断路器的最少请求数
			}
	)
	public String ribbonCall(){
		return restTemplate.postForObject("http://provider-service/api/order/queryList",null,String.class);
	}
	
	public String placeOrder(Integer goodId){
		Map<String, Object> param=new HashMap<>();
		param.put("sid", goodId);
		return restTemplate.postForObject("http://provider-service/api/order/create", param, String.class);
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
