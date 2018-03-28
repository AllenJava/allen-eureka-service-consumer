package com.allen.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
* @ClassName: ProviderServiceHystrix
* @Description: 创建回调类ComputeClientHystrix，实现@FeignClient的接口，此时实现的方法就是对应@FeignClient接口中映射的fallback函数。
* @author chenliqiao
* @date 2018年3月26日 下午2:58:09
*
 */
@Component
public class ProviderServiceHystrix implements ProviderService{

	@Override
	public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
		// TODO Auto-generated method stub
		return -99999;
	}

}
