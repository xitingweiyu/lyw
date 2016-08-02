package com.wonders.bigdata.manageplatform.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Properties;

/**
 * 系统权限动态配置文件的写入和读取操作
 * @author hsw
 *
 */
public class  PropertyTool {
	static String fileName = "com/wonders/bigdata/manageplatform/authority.properties";  

	/**
	 * b保存property文件
	 */
	public static void saveProperty(Properties propertie) {  
		if(propertie==null)
			propertie = new Properties();
		
		fileName = "com/wonders/bigdata/manageplatform/authority.properties";
		
		fileName = fileName.replace("\\", "/");
        // 保存文件  
        String description = "authority properity file";  
        String dString = new PropertyTool().getClass().getClassLoader().getResource("").getPath();
        fileName = dString + fileName;
        try {  
            FileOutputStream outputFile = new FileOutputStream(fileName);
          //给该文件加锁    
//            RandomAccessFile fis = new RandomAccessFile(fileName, "rw");   
            FileChannel fcin=outputFile.getChannel();    
            FileLock flin=null;
            flin = fcin.tryLock();
            propertie.store(outputFile, description);// property类关键的store方法  
            // propertie.list(System.out);  
            System.out.println("File was saved!");  
            flin.release();    
            fcin.close();    
            outputFile.close();  
//            fis.close();    
//            fis=null;
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        }  
    }  
	
	/**
	 * 读取property文件
	 * @param key 要读取的key值
	 * @return
	 * @throws IOException
	 */
	public static String readProperty(String key) throws IOException{
		Properties readProps = new Properties();  
        FileInputStream inStream;  
        String result="";
        try {  
            inStream = new FileInputStream(fileName);  
            readProps.load(inStream);// read from fileinputStream  
            // props.list(System.out);  
            if (readProps.get(key) != null) {  
                System.out.println(key + "="  
                        + new String(readProps.getProperty(key)  
                                .getBytes("ISO-8859-1"), "UTF-8"));  
                System.out.println(key + "="  
                        + new String(readProps.getProperty(key)  
                                .getBytes("UTF-8"), "UTF-8"));  
                result = readProps.getProperty(key);
            } else {  
                System.out.println(readProps.get(key));
                result = null;
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } 
        return result;
	}
	
	public static void main(String[] args) {  
		
		 Properties propertie = new Properties();  
	     String characterString = "1中国e6的";  
	     propertie.setProperty("character", characterString);  
	     //propertie.setProperty("date", new Date().toString()); 
		saveProperty(propertie);
		
		try {
			readProperty("character");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
