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
import org.springframework.web.multipart.MultipartFile;

public class PicUploadUtil {

	public static String SavePicToDisk(HttpServletRequest request,String timespan){
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
		       
		        String fileName =CommonUtils.GenerateMaxPicName(file, timespan);
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
	
	
	/** 
     * 通过传入页面读取到的文件，处理后保存到本地磁盘，并返回一个已经创建好的File 
     * @param imgFile 从页面中读取到的文件 
     * @param typeName  商品的分类名称 
     * @param brandName 商品的品牌名称 
     * @param fileTypes 允许的文件扩展名集合 
     * @return 
     */  
    public static File getFile(MultipartFile imgFile,String typeName,String brandName,List fileTypes,String fileName,String basePath) {   
        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
         String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
         //对扩展名进行小写转换  
         ext = ext.toLowerCase();  
           
         File file = null;  
         if(fileTypes.contains(ext)) {                      //如果扩展名属于允许上传的类型，则创建文件  
             file = creatFolder(typeName, brandName, fileName,basePath);  
             try {  
                imgFile.transferTo(file);                   //保存上传的文件  
            } catch (IllegalStateException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
         }  
         return file;  
    } 
    
    /** 
     * 检测与创建一级、二级文件夹、文件名 
            这里我通过传入的两个字符串来做一级文件夹和二级文件夹名称 
           通过此种办法我们可以做到根据用户的选择保存到相应的文件夹下 
            
     */  
    public static File creatFolder(String typeName,String brandName,String fileName,String basePath) {  
         File file = null;  
         typeName = typeName.replaceAll("/", "");               //去掉"/"  
         typeName = typeName.replaceAll(" ", "");               //替换半角空格  
         typeName = typeName.replaceAll(" ", "");               //替换全角空格  
          
         brandName = brandName.replaceAll("/", "");             //去掉"/"  
         brandName = brandName.replaceAll(" ", "");             //替换半角空格  
         brandName = brandName.replaceAll(" ", "");             //替换全角空格  
          
         File firstFolder = new File(basePath + typeName);         //一级文件夹  
         if(firstFolder.exists()) {                             //如果一级文件夹存在，则检测二级文件夹  
             File secondFolder = new File(firstFolder,brandName);  
             if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                 file = new File(secondFolder,fileName);  
             }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                 secondFolder.mkdir();  
                 file = new File(secondFolder,fileName);        //创建完二级文件夹后，再合建文件  
             }  
         }else {                                                //如果一级不存在，则创建一级文件夹  
             firstFolder.mkdir();  
             File secondFolder = new File(firstFolder,brandName);  
             if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                 file = new File(secondFolder,fileName);  
             }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                 secondFolder.mkdir();  
                 file = new File(secondFolder,fileName);  
             }  
         }  
         return file;  
    }
}
