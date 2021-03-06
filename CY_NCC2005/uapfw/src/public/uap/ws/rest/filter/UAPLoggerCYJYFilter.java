package uap.ws.rest.filter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletRequest;

import org.json.JSONObject;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.routing.Filter;


import nc.bs.logging.Logger;
import nc.utils.commonplugin.NoteDataUtils;
import uap.ws.rest.service.UAPLoggerCYJYService;

public class UAPLoggerCYJYFilter extends Filter {
	
	private static String COSTTIME_START = "uap.restservice.startTime";

	public UAPLoggerCYJYFilter(Context context, UAPLoggerCYJYService uapLogeerCYJYService) {
		super(context);
	}

	@Override
	protected void afterHandle(Request request, Response response) {
		System.out.println(request);
		this.requestFilterLogProcessor(request, response);
		super.afterHandle(request, response);
	}

	@Override
	protected int beforeHandle(Request request, Response response) {
		System.out.println(request);
		this.requestFilterLogProcessor(request, response);
		return super.beforeHandle(request, response);
	}

	@Override
	protected int doHandle(Request request, Response response) {
//		 ServletRequest requestWrapper = new CYBodyReaderHttpServletRequestWrapper(request);
		
		
		
		
		return super.doHandle(request, response);
	}

	private void requestFilterLogProcessor(Request request, Response response) {
		long startTime = ((Long) request.getAttributes().get(COSTTIME_START)).longValue();
		int duration = (int) (System.currentTimeMillis() - startTime);

		String httpVersion = (String) request.getAttributes().get("org.restlet.http.version");
		request.getResourceRef().toString();

		String clientIP = request.getClientInfo().getAddress();
		int clientPort = request.getClientInfo().getPort();
		String clientAddress = clientIP + ":" + clientPort;
		String logMsg = "uap_restful_webservice:" + request.getResourceRef().toString() + " /////http" + httpVersion
				+ "/client:" + clientAddress + "   /cost_time:" + duration;

		Representation representation = request.getEntity();
		String qwe = "";
		try {
//			JsonRepresentation jr = new JsonRepresentation(representation);
//			InputStream is = jr.getStream();
//			String result = this.changeInputStream(is, "utf-8");
			
			NoteDataUtils.addLog("logMsg: "+logMsg, "logMsg", false, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		NoteDataUtils.addLog("logMsg: "+logMsg, "logMsg", false, null);
	}
	
	
	public static String changeInputStream(InputStream inputStream, String encode) {  
        String res = "";  
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
       // byte[] data = new byte[100];///?????? hello123456  
        byte[] data = new byte[5];///????????????5?????????
        int len = 0;  
        try {  
            while ((len = inputStream.read(data)) != -1) { ////inputStream-->data[]  
//               InputStream??? int read(byte[] b)  
//                ?????????????????????????????????????????????????????????????????????????????? b ??????  
//                ?????????  
//                ?????????????????????????????????????????????????????????????????????????????????????????????????????? -1???  
  
                outputStream.write(data, 0, len);/////outputStream<--data[]  
//              ByteArrayOutputStream???  void write(byte[] b, int off, int len)  
//                ????????? byte ????????????????????? off ????????? len ?????????????????? byte ??????????????????  
            }  
            res = new String(outputStream.toByteArray(), encode);  
//          ByteArrayOutputStream???  byte[] toByteArray()  
//            ???????????????????????? byte ?????????  
//          String???  String(byte[] bytes, Charset charset)  
//            ????????????????????? charset ??????????????? byte ??????????????????????????? String???  
  
            inputStream.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return res;  
    }//changeInputStream  

}
