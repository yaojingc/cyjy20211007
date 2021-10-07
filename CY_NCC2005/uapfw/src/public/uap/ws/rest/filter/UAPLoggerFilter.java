/*    */ package uap.ws.rest.filter;
/*    */ 
/*    */ import java.util.Date;
/*    */ import nc.bs.logging.Logger;
/*    */ import org.restlet.Context;
/*    */ import org.restlet.Request;
/*    */ import org.restlet.Response;
/*    */ import org.restlet.routing.Filter;
/*    */ import uap.ws.rest.container.ModuleRestContainerProxy;
/*    */ import uap.ws.rest.service.UAPLogeerService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UAPLoggerFilter
/*    */   extends Filter
/*    */ {
/* 17 */   private static String COSTTIME_START = "uap.restservice.startTime";
/* 18 */   public static ThreadLocal<ResourceAccessInfo> accessInfo = new ThreadLocal();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public UAPLoggerFilter(Context context, UAPLogeerService uapLogeerService) { super(context); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int beforeHandle(Request request, Response response) {
/* 29 */     request.getAttributes().put(COSTTIME_START, Long.valueOf(System.currentTimeMillis()));
/* 30 */     accessInfo.remove();
/* 31 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int doHandle(Request request, Response response) {
/* 36 */     int result = 0;
/*    */     try {
/* 38 */       result = super.doHandle(request, response);
/* 39 */     } catch (NullPointerException e) {
/* 40 */       Logger.error(e);
/* 41 */       logRecord(request, response);
/* 42 */       throw new RuntimeException("resource notfound,plrase check path", e);
/* 43 */     } catch (Exception e) {
/* 44 */       Logger.error(e);
/* 45 */       logRecord(request, response);
/* 46 */       throw new RuntimeException(e);
/*    */     } 
/* 48 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 53 */   protected void afterHandle(Request request, Response response) { logRecord(request, response); }
/*    */ 
/*    */ 
/*    */   
/*    */   private void logRecord(Request request, Response response) {
/* 58 */     long startTime = ((Long)request.getAttributes().get(COSTTIME_START)).longValue();
/* 59 */     int duration = (int)(System.currentTimeMillis() - startTime);
/*    */     
/* 61 */     String httpVersion = (String)request.getAttributes().get("org.restlet.http.version");
/* 62 */     request.getResourceRef().toString();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     String clientIP = request.getClientInfo().getAddress();
/* 69 */     int clientPort = request.getClientInfo().getPort();
/* 70 */     String clientAddress = clientIP + ":" + clientPort;
/* 71 */     String logMsg = "uap_restful_webservice:" + request.getResourceRef().toString() + " /////http" + httpVersion + "/client:" + clientAddress + "   /cost_time:" + duration;
/*    */ 
/*    */     
/* 74 */     ResourceAccessInfo resourceAccessInfo = (ResourceAccessInfo)accessInfo.get();
/* 75 */     if (resourceAccessInfo != null) {
/* 76 */       resourceAccessInfo.setCostTime(duration);
/* 77 */       resourceAccessInfo.setUserIP(clientAddress);
/* 78 */       resourceAccessInfo.setUrl(request.getResourceRef().toString());
/* 79 */       resourceAccessInfo.setDataTime((new Date()).toString());
/* 80 */       logTrace(resourceAccessInfo);
/* 81 */       Logger.debug(logMsg);
/*    */     } 
/* 83 */     accessInfo.remove();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 88 */   private void logTrace(ResourceAccessInfo resourceAccessInfo) { ModuleRestContainerProxy.addAccessRecord(resourceAccessInfo); }
/*    */ }


/* Location:              D:\homeCIYUAN\home\module\\uapfw\lib\pubuapfw_restframeworkLevel-1.jar!/uap/ws/rest/filter/UAPLoggerFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */