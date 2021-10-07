package nc.utils.commonplugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import nc.bs.framework.common.RuntimeEnv;

/**
 * 统一生成文本来记录，奇葩异常
 */
public class NoteDataUtils {
	
	/**
	 * 记录数据
	 * @param logStr 日志
	 * @param fileName 文件名称
	 * @param isClean 是否清空文件内容
	 * @param url 请求url地址
	 */
	public static void addLog(String logStr,String fileName,boolean isClean,String url){
		try {
			String path=RuntimeEnv.getInstance().getNCHome()+"\\devlopFolder";
			
			if(isClean) {
				clearInfoForFile(path, fileName);
			}
			
	        // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
	        /* 写入Txt文件 */
	        File writename = new File(path);// 相对路径，如果没有则要建立文件夹
	        if(!writename.exists()){
	            writename.mkdirs();
	        }
	        writename = new File(path+"\\"+fileName);// 相对路径，如果没有则要建立一个新的noteData.txt文件
	        
	        if(!writename.exists()) {
	        	writename.createNewFile(); // 创建新文件
            }
	        
	        Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateNowStr = sdf.format(d);
			
			if(StringUtils.isBlank(url)) {
				logStr="【记录时间】："+dateNowStr+"\r\n【记录内容】："+logStr+"\r\n"+"\r\n"+"\r\n";
			}else {
				logStr="【记录时间】："+dateNowStr+"\r\n【调用接口url】："+url+"\r\n【记录内容】："+logStr+"\r\n"+"\r\n"+"\r\n";
			}
	        
	        FileOutputStream o = null;
	        byte[] buff = new byte[]{};

            buff=logStr.getBytes();
            o=new FileOutputStream(writename,true);
            o.write(buff);
            o.flush();
            o.close();
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 清空nchome\wslog\note下的指定文件的文件内容
	 * @param path 根路径
	 * @param fileName 文件名称
	 * @throws IOException 
	 */
	private static void clearInfoForFile(String path,String fileName) throws IOException {
    	File writename = new File(path);
        if(writename.exists()){
        	writename = new File(path+"\\"+fileName);
	        if(writename.exists()) {
	        	FileWriter fileWriter =new FileWriter(writename);
	            fileWriter.write("");
	            fileWriter.flush();
	            fileWriter.close();
            }
        }
    }

}