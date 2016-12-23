package site.jjilikeyou.www.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;



public class Https {
	private static Logger logger = Logger.getLogger(Https.class);
    public static void main(String[] a){
        Https t = new Https();
        //String httpUrl = "https://gm.sailcraft.uqsoft.com/gate/echo";
       // String httpUrl="https://gm.sailcraft.uqsoft.com/gate/lmgr/";
        String httpUrl="https://gm.sailcraft.uqsoft.com/gate/lmgr";
        String jsonString="{\"action\":"+"\""+"add"+"\""+",\"languages\":"+"\""+"zh_Hans"+"\""+",\"content\":{\"mailtitle\":{"+"\""+123+"\":"+"\""+"ceshi"+"\"},"+"\"maildescription\":{"+"\""+123+"\":"+"\""+"哈哈哈哈"+"\""+"}}}";
        t.setUp();
        t.testHttpsClient(httpUrl,jsonString);
    }
   // private String httpUrl = "https://gm.sailcraft.uqsoft.com/gate/echo";
 // private String httpUrl="https://gm.sailcraft.uqsoft.com/gate/mail/";
   //private String httpUrl="http://gm.sailcraft.uqsoft.com/gate/lmgr/";
    // 客户端密钥库
 	private String sslKeyStorePath;
 	private String sslKeyStorePassword;
 //	private String sslKeyStoreType;
 	// 客户端信任的证书
 	private String sslTrustStore;
 	private String sslTrustStorePassword;
	
    
    public  void setUp() {
    	//String filePath =  this.getClass().getResource("/").getPath()  + "gm.jks" ;
    	//服务器绝对路径
    	String filePath = "/data/www/hz-gmtool/https/gm.jks";
        sslKeyStorePath = filePath;
        sslKeyStorePassword = "123456";
       // String filePath2 =  this.getClass().getResource("/").getPath()  + "t.jks" ;
        //服务器绝对路径
        String filePath2 ="/data/www/hz-gmtool/https/t.jks";
        sslTrustStore =filePath2;
        sslTrustStorePassword = "123456";
        /*   sslKeyStoreType = "JKS";
       System.setProperty("javax.net.ssl.keyStore", sslKeyStorePath);
		System.setProperty("javax.net.ssl.keyStorePassword",
				sslKeyStorePassword);
		System.setProperty("javax.net.ssl.keyStoreType", sslKeyStoreType);
		// 设置系统参数
		System.setProperty("javax.net.ssl.trustStore", sslTrustStore);
		System.setProperty("javax.net.ssl.trustStorePassword",
				sslTrustStorePassword);*/
        
    }
    public String testHttpsClient(String httpUrl,String sendData ) {
        SSLContext sslContext = null;
        StatusLine statusLine = null;
        int statusCode=0;
        try {
            KeyStore kstore = KeyStore.getInstance(KeyStore.getDefaultType());
            kstore.load(new FileInputStream(sslKeyStorePath),sslKeyStorePassword.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(kstore, sslKeyStorePassword.toCharArray());
            
            KeyStore tstore = KeyStore.getInstance(KeyStore.getDefaultType());
            tstore.load(new FileInputStream(sslTrustStore),sslTrustStorePassword.toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(tstore);
            
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            HttpClient httpClient = new DefaultHttpClient();
            SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext);
            Scheme sch = new Scheme("https", 443, socketFactory);
            httpClient.getConnectionManager().getSchemeRegistry().register(sch);
            HttpPost httpPost = new HttpPost(httpUrl);
//           List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            nvps.add(new BasicNameValuePair("user", "abin"));
//            nvps.add(new BasicNameValuePair("pwd", "abing"));
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            httpPost.setEntity(new StringEntity(sendData, HTTP.UTF_8));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));
            StringBuffer stb=new StringBuffer();
            String line=null;
            while((line=buffer.readLine())!=null){
                stb.append(line);
            }
            buffer.close();
            String result=stb.toString();
           logger.info("result="+result);
             statusLine = httpResponse.getStatusLine();
           // ProtocolVersion protocolVersion = httpResponse.getProtocolVersion();
            statusCode = statusLine.getStatusCode();
            //System.out.println("protocolVersion:"+protocolVersion);
            logger.info("StatusCode："+statusCode);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return "faild`文件损坏"+statusCode;
        }
    }
    
}
