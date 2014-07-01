package com.bip.Controller;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WzXxfbPdtpController {

	@RequestMapping(value="/fileUpload1",method=RequestMethod.GET)
	public String getUploadPage(Model model){
		return "test/testUploadFile";
	}
	
	@RequestMapping(value="/fileUpload1",method=RequestMethod.POST)
	public String postUploadPage(Model model,HttpServletRequest request){
		try{
			//文件初始化
			DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置最大文件蛳
		   factory.setSizeThreshold(200*1024*1024);
		//设置临时文件夹
		   File tempFile=new File("d:/");
		   factory.setRepository(tempFile);
		//建文件项列表
		   ServletFileUpload upload = new ServletFileUpload(factory);
		//分析构成文件列表,把表单每个项都列表了,要进行判断
		   List items = upload.parseRequest(request);
		   Iterator iter = items.iterator();
		//对列表项进行处理
		   while (iter.hasNext()) {
		       FileItem item = (FileItem) iter.next();
		//当是不是一个form项时就是一个文件项
		       if(!item.isFormField()){
		//获取文件名
		        String file=item.getName();
		//对文件名进行分析
		         int pos = file.lastIndexOf(File.separator);
		            if (pos > 0) {
		             file=file.substring(pos + 1);
		            }
		        File o=new File(request.getRealPath("/upload")+File.separator+file);
		//写入文件之中
		     item.write(o);
		       }
		   }

		  } catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		  catch (FileUploadException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		  catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		return "";
	}
}
