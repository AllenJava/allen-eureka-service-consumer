package com.allen.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//使用@FeignClient注解中的fallback属性指定回调类
@FeignClient(value="provider-service",fallback=ProviderServiceHystrix.class)
public interface ProviderService {

	@RequestMapping(value="/add",method=RequestMethod.GET)
	Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
