package com.wonders.bigdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Properties;

/**
 * 首页非登录页面的属性读取
 * @author xcl
 *
 */
public class  NotLoginPropertyTool {
	static String fileName = "";  

	/**
	 * 保存property文件
	 */
	public void saveProperty(Properties propertie,String description) { 
		fileName = "com/wonders/bigdata/catalogData.properties";
		if(propertie==null)	propertie = new Properties();		
		fileName = fileName.replace("\\", "/");
		
        // 保存文件转换地址  
        String dString = new NotLoginPropertyTool().getClass().getClassLoader().getResource("").getPath();
        fileName = dString + fileName;
       System.out.println("------------------------------------s"+fileName);
        try {  
            FileOutputStream outputFile = new FileOutputStream(fileName);
  
            FileChannel fcin=outputFile.getChannel();    
            FileLock flin=null;
            flin = fcin.tryLock();            
          
            propertie.store(outputFile, description);// property类关键的store方法  
           
            flin.release();    
            fcin.close();    
            outputFile.close();  

        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        }  
    }  
	
	/**
	 * 读取property文件
	 * @param 从页面传入的值  要读取的key值
	 * @return 此键对应的值，适合读取update_time、catalog_name_info和catalog_name_count
	 * @throws IOException
	 */
	public String readProperty(String info) throws IOException{
		fileName = "com/wonders/bigdata/catalogData.properties";
		Properties readProps = new Properties();  
        FileInputStream inStream;  
        String result="";
        
        String dString = new NotLoginPropertyTool().getClass().getClassLoader().getResource("").getPath();
        fileName = dString + fileName;
        System.out.println("------------------------------------d"+fileName);
 		
        try {  
            inStream = new FileInputStream(fileName);  
            readProps.load(inStream);// read from fileinputStream  
          
            if (readProps.get(info) != null) {                
            	result = readProps.getProperty(info);                
            } else {          
                result = null;
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } 
        return result;
	}
}

