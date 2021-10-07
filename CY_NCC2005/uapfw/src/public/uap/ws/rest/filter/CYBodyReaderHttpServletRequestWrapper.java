package uap.ws.rest.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 
 
 
 
public class CYBodyReaderHttpServletRequestWrapper extends
		HttpServletRequestWrapper {
	
	private final byte[] body;
 
    public CYBodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        System.out.println("-------------------------------------------------");  
        Enumeration e = request.getHeaderNames()   ;  
         while(e.hasMoreElements()){  
             String name = (String) e.nextElement();  
             String value = request.getHeader(name);  
             System.out.println(name+" = "+value);  
               
         }  
        body = CYHttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }
 
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
 
    @Override
    public ServletInputStream getInputStream() throws IOException {
 
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
 
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener paramReadListener) {
				// TODO Auto-generated method stub
				
			}
        };
    }
 
	@Override
	public String getHeader(String name) {
		return super.getHeader(name);
	}
 
	@Override
	public Enumeration<String> getHeaderNames() {
		return super.getHeaderNames();
	}
 
	@Override
	public Enumeration<String> getHeaders(String name) {
		return super.getHeaders(name);
	}
    
}

