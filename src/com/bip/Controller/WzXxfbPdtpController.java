package com.bip.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class WzXxfbPdtpController {
	
	
	@RequestMapping(value="fileUploadTest",method=RequestMethod.GET)
	public String addImage(Model model) {
		return "test/testUploadFile";
	}
	
	/** 
     * 上传图片文件,并保存到指定的路径当中 
     */ 
	@RequestMapping(value="fileUploadTest",method=RequestMethod.POST)
    public void addImage(HttpServletRequest request) {  
        //转型为MultipartHttpRequest(重点的所在)  
         MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
         //  获得第1张图片（根据前台的name名称得到上传的文件）   
         MultipartFile imgFile1  =  multipartRequest.getFile("imgFile");  
          
         //定义一个数组，用于保存可上传的文件类型  
         List fileTypes = new ArrayList();  
         fileTypes.add("jpg");  
         fileTypes.add("jpeg");  
         fileTypes.add("bmp");  
         fileTypes.add("gif");  
         fileTypes.add("png"); 
         //保存第一张图片  
         if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
/*下面调用的方法，主要是用来检测上传的文件是否属于允许上传的类型范围内，及根据传入的路径名 
*自动创建文件夹和文件名，返回的File文件我们可以用来做其它的使用，如得到保存后的文件名路径等 
*这里我就先不做多的介绍。 
*/  
        	 String path1="12";
        	 String path2="34";
        	 String fileName = imgFile1.getOriginalFilename();
             File file1 = this.getFile(imgFile1,path1,path2,fileTypes,fileName);  
             
         }     
    } 
    
    /** 
     * 通过传入页面读取到的文件，处理后保存到本地磁盘，并返回一个已经创建好的File 
     * @param imgFile 从页面中读取到的文件 
     * @param typeName  商品的分类名称 
     * @param brandName 商品的品牌名称 
     * @param fileTypes 允许的文件扩展名集合 
     * @return 
     */  
    private File getFile(MultipartFile imgFile,String typeName,String brandName,List fileTypes,String fileName) {   
        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
         String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
         //对扩展名进行小写转换  
         ext = ext.toLowerCase();  
           
         File file = null;  
         if(fileTypes.contains(ext)) {                      //如果扩展名属于允许上传的类型，则创建文件  
             file = this.creatFolder(typeName, brandName, fileName);  
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
    private File creatFolder(String typeName,String brandName,String fileName) {  
         File file = null;  
         typeName = typeName.replaceAll("/", "");               //去掉"/"  
         typeName = typeName.replaceAll(" ", "");               //替换半角空格  
         typeName = typeName.replaceAll(" ", "");               //替换全角空格  
          
         brandName = brandName.replaceAll("/", "");             //去掉"/"  
         brandName = brandName.replaceAll(" ", "");             //替换半角空格  
         brandName = brandName.replaceAll(" ", "");             //替换全角空格  
          
         File firstFolder = new File("c:/" + typeName);         //一级文件夹  
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