package nc.utils.invoice;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.core.io.ClassPathResource;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.compression.CompressionCodecs;
import nc.utils.commonConstant.ConstantUtil;

public class CertificateUtil {
	   public CloseableHttpClient createSSLClientDefault() {
	        try {
	            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
	                @Override
	                public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
	                    return true;
	                }
	            }).build();

	            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
	                @Override
	                public boolean verify(String s, SSLSession sslSession) {
	                    return true;
	                }
	            });
	            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	        } catch (KeyManagementException e) {
	            e.printStackTrace();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (KeyStoreException e) {
	            e.printStackTrace();
	        }
	        return HttpClients.createDefault();
	    }
	
	
	 /**
     * ??????
     *
     * @param paramsMap ????????????
     * @return ?????????
     * @throws Exception
     */
    public String sign(String paramsMap) throws Exception {

        // ??????CA?????????PEM??????????????????????????????????????????????????????,????????????????????????
        PrivateKey privateKey = loadPrivateKeyOfCA();

        Map<String, Object> claims =
                JwtParamBuilder.build().setSubject("tester").setIssuer("einvoice").setAudience("einvoice")
                        .addJwtId().addIssuedAt().setExpirySeconds(3600).setNotBeforeSeconds(3600).getClaims();

        // ?????????????????????requestdatas???????????????md5????????????????????????????????????requestdatas??????
        // ?????????????????????????????????????????????????????????????????????
        claims.put("requestdatas", getMD5(paramsMap));
        // ??????jdk1.6????????????????????????????????????.compressWith(CompressionCodecs.DEFLATE)
        String compactJws = Jwts.builder().signWith(SignatureAlgorithm.RS512, privateKey)
                .setClaims(claims).compressWith(CompressionCodecs.DEFLATE).compact();

        return compactJws;
    }
    
    /**
     * ??????????????????
     *
     * @return
     * @throws UnrecoverableKeyException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     */
    protected PrivateKey loadPrivateKeyOfCA() throws UnrecoverableKeyException, KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {
    	//FileInputStream in = new FileInputStream(this.getClass().getResource("/????????????????????????????????????????????????.p12").getFile());
    	ClassPathResource classPathResource = new ClassPathResource("static" + ConstantUtil.CAFILE);// "static" + ConstantUtil.CAFILE
    	InputStream in = classPathResource.getInputStream();
    	KeyStore ks = KeyStore.getInstance("pkcs12");
        ks.load(in, ConstantUtil.PASSWORD.toCharArray());
        String alias = ks.aliases().nextElement();
        PrivateKey caprk = (PrivateKey) ks.getKey(alias, ConstantUtil.PASSWORD.toCharArray());
        return caprk;
    }
    
    /**
     * ??????MD5
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private String getMD5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] buf = null;
        buf = str.getBytes("utf-8");
        MessageDigest md5 = null;
        md5 = MessageDigest.getInstance("MD5");
        md5.update(buf);
        byte[] tmp = md5.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : tmp) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
   
}
