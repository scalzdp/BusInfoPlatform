package com.bip.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PublishMessageController {

	@RequestMapping(value="PublishedProducts",method=RequestMethod.GET)
	private String getPublishProduct(){
		return "publishmessage/publishproduct";
	}
	
	@RequestMapping(value="ProductsClassManage",method=RequestMethod.GET)
	private String getProductsClassManage(){
		return "publishmessage/productsclassmanage";
	}
	
	@RequestMapping(value="AllPublishedProducts",method=RequestMethod.GET)
	private String getAllPublishedProducts(){
		return "publishmessage/allpublishedproducts";
	}
}
