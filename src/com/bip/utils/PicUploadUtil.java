package com.bip.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PicUploadUtil {

	public static String SavePicToDisk(HttpServletRequest request,Date now){
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
		       
		        String fileName =CommonUtils.GenerateMaxPicName(file, now);
		        //给文件区名称
		//对文件名进行分析
		         int pos = file.lastIndexOf(File.separator);
		            if (pos > 0) {
		             file=file.substring(pos + 1);
		            }
		        File o=new File(request.getRealPath("/upload")+File.separator+fileName);
		        //写入文件之中
		        item.write(o);
		        return fileName;
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
