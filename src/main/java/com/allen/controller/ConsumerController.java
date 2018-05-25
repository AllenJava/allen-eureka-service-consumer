package com.allen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.allen.service.ConsumerService;
import com.allen.service.ProviderService;

@RestController
public class ConsumerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private ConsumerService consumerService;
	
	/**
	 * 
	* @Title: ribbonAdd
	* @Description: ribbon方式调用服务实现负载均衡
	* @param @return
	* @return String
	* @throws
	 */
	@RequestMapping(value="/ribbonAdd",method=RequestMethod.GET)
	public String ribbonAdd(){
		return restTemplate.getForEntity("http://provider-service/add?a=10&b=20", String.class).getBody();
	}
	
	/**
	 * 
	* @Title: feignAdd
	* @Description: feign方式调用服务实现负载均衡
	* @param @return
	* @return Integer
	* @throws
	 */
	@RequestMapping(value="/feignAdd",method=RequestMethod.GET)
	public Integer feignAdd(){
		return this.providerService.add(55, 66);
	}
	
	
	/**
	 * @throws InterruptedException 
	 * 
	* @Title: hystrixAdd
	* @Description: hystrix实现服务断路功能(ribbon方式)
	* @param @return
	* @return String
	* @throws
	 */
	@RequestMapping(value="/hystrixAdd",method=RequestMethod.GET)
	public String hystrixAdd() throws InterruptedException{
		return this.consumerService.ribbonCall();
	}
	
	
	/**
	 * 下单
	 */
	@RequestMapping(value="/placeOrder",method=RequestMethod.POST)
	public String placeOrder(@RequestParam(required=true) Integer goodId){
		return this.consumerService.placeOrder(goodId);
	}

}
