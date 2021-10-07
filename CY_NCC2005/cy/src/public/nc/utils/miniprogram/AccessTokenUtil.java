package nc.utils.miniprogram;

import com.alibaba.fastjson.JSONObject;

import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.ErpItfDataPool;


public class AccessTokenUtil {


    private static AccessToken accessToken;
    
	//该方法不会被外界调用
    private static AccessToken getToken() {
        try {
        	String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + AppletsParams.AppID + "&secret=" + AppletsParams.AppSecret;
        	JSONObject tokenObj = ErpItfDataPool.erpHttpGet(url);
        	accessToken = new AccessToken(tokenObj.getString("access_token"), Long.parseLong(tokenObj.getString("expires_in")));
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
	//业务层获取token调用该方法
    public static String getAccessToken() {
        if (CyCommonUtils.isEmpty(accessToken) || accessToken.isTimeOut()) {
            accessToken = AccessTokenUtil.getToken();
            return accessToken.getAccess_token();
        } else {
            return accessToken.getAccess_token();
        }
    }
}
