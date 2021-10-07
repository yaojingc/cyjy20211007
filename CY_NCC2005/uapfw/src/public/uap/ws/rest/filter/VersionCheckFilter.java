package uap.ws.rest.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.URIDereferencer;
 
 
public class VersionCheckFilter implements Filter {
 
	@Override
	public void destroy() {
 
	}
 
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
	    String uri = hreq.getRequestURI();
	    if(uri.contains("uploadImageServlet")){
	    	//图像上传的请求，不做处理
	    	chain.doFilter(req, res);
	    }else{
	    	String reqMethod = hreq.getMethod();
	    	if("POST".equals(reqMethod)){
	    		
	    		PrintWriter out = null; 
	    		HttpServletResponse response = (HttpServletResponse) res;
	    		response.setCharacterEncoding("UTF-8");  
 			    response.setContentType("application/json; charset=utf-8");  
 			    
 			  /* String requestStr = this.inputStreamToString(hreq.getInputStream());
 		        String[] arrs = requestStr.split("&"); 
 		        for (String strs : arrs) {
 		            String[] strs2 = strs.split("=");
 		            for (int i = 0; i < strs2.length; i++) {
 		                if (strs2[0].equals("param")) {
 		                    if (strs2[1] != null &&!strs2[1].equals("")) {
 		                        System.out.println("test=" + strs2[1]);
 		                    }
 		                }
 		            }
 		        }*/
 			    
 			   ServletRequest requestWrapper = new CYBodyReaderHttpServletRequestWrapper(hreq);
               String body = CYHttpHelper.getBodyString(requestWrapper);
 			    
	    		//如果是POST请求则需要获取 param 参数
 		       String param = URLDecoder.decode(body,"utf-8");
	    		//String param = hreq.getParameter("param");
	    		//json串 转换为Map
// 		        if(param!=null&m.contains("=")){
// 		        	param = param.split("=")[1];
//	    		}
	    		Map paramMap = null;//JsonHelper.getGson().fromJson(param, Map.class);
	    		Object obj_clientversion = paramMap.get("clientVersion");
	    		
	    		String clientVersion = null;
	    		if(obj_clientversion != null){
	    			clientVersion = obj_clientversion.toString();
	    			System.out.println(clientVersion);
    			    /*try {  
    			        out = response.getWriter();
    			        Map remap = new HashMap<String, Object>();
    			        remap.put("code", 9);
    			        remap.put("message", Constant.SYSTEM_ERR_DESC);
    			        out.append(JsonHelper.getGson().toJson(remap));  
    			    } catch (IOException e) {  
    			        e.printStackTrace();  
    			    } finally {  
    			        if (out != null) {  
    			            out.close();  
    			        }  
    			    }*/
	    			chain.doFilter(requestWrapper, res);	
    		}else{
    			chain.doFilter(requestWrapper, res);	
    		}
	    	}else{
	    		//get请求直接放行
	    		chain.doFilter(req, res);
	    	}
	    }
	}
 
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
 
	/*public String inputStreamToString(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
	}*/
}

