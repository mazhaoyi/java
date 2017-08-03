package mb_redis_util.com.doordu.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

/**
 * http帮助类
 */
public class HttpUtils {
	/**
	 * http get提交
	 * @param url
	 * @param params <k, v>参数
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static String get(String url, Map<String, Object> params) throws IOException, Exception {
		Header[] headers = defaultHeaders();
		Charset charset = Charset.forName("utf-8");
		return get(url, params, charset, headers);
	}
	/**
	 * http post提交
	 * @param url
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static String post(String url, Map<String, Object> params, String jsonBody) throws IOException, Exception {
		Header[] headers = defaultHeaders();
		Charset charset = Charset.forName("utf-8");
		return post(url, params, jsonBody, charset, headers);
	}
	/**
	 * http ssl get提交
	 * @param url
	 * @param keystoreFile 证书文件
	 * @param password 密码
	 * @param params <k, v>参数
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static String getSsl(String url, String keystoreFile, String password, Map<String, Object> params) throws IOException, Exception {
		Header[] headers = defaultHeaders();
		Charset charset = Charset.forName("utf-8");
		return getSsl(url, keystoreFile, password, params, charset, headers);
	}
	/**
	 * http ssl post提交
	 * @param url
	 * @param keystoreFile 证书文件
	 * @param password 密码
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static String postSsl(String url, String keystoreFile, String password, Map<String, Object> params, String jsonBody) throws IOException, Exception {
		Header[] headers = defaultHeaders();
		Charset charset = Charset.forName("utf-8");
		return postSsl(url, keystoreFile, password, params, jsonBody, charset, headers);
	}
	/**
	 * http get ssl 提交
	 * 忽略证书
	 * @param url
	 * @param params <k, v>参数
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static String getSsl(String url, Map<String, Object> params) throws IOException, Exception {
		Header[] headers = defaultHeaders();
		Charset charset = Charset.forName("utf-8");
		return getSsl(url, params, charset, headers);
	}
	/**
	 * http post ssl 提交
	 * 忽略证书
	 * @param url
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static String postSsl(String url, Map<String, Object> params, String jsonBody) throws IOException, Exception {
		Header[] headers = defaultHeaders();
		Charset charset = Charset.forName("utf-8");
		return postSsl(url, params, jsonBody, charset, headers);
	}
	/**
	 * post form 提交
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postForm(String url, Map<String, Object> params) throws Exception {
		Charset charset = Charset.forName("utf-8");
		Header[] headers = {
				new BasicHeader("Content-Type", "application/x-www-form-urlencoded"),
		};
		return postForm(url, params, headers, charset);
	}
	/**
	 * post form ssl 提交
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postFormSsl(String url, Map<String, Object> params) throws Exception {
		Charset charset = Charset.forName("utf-8");
		Header[] headers = {
				new BasicHeader("Content-Type", "application/x-www-form-urlencoded"),
		};
		return postFormSsl(url, params, charset, headers);
	}
	/**
	 * post form ssl 提交
	 * @param url
	 * @param keystoreFile
	 * @param password
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postFormSsl(String url, String keystoreFile, String password, Map<String, Object> params) throws Exception {
		Header[] headers = {
				new BasicHeader("Content-Type", "application/x-www-form-urlencoded"),
		};
		Charset charset = Charset.forName("utf-8");
		return postFormSsl(url, keystoreFile, password, params, charset, headers);
	}
	/**
	 * http post提交
	 * @param url
	 * @param headers
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 */
	public static String post(String url, Map<String, Object> params, String jsonBody, Charset charset, Header[] headers) throws IOException, Exception {
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			result = post(url, params, jsonBody, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	/**
	 * post提交表单
	 * 
	 * Charset utf-8
	 * Content-Type application/x-www-form-urlencoded
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postForm(String url, Map<String, Object> params, Header[] headers, Charset charset) throws Exception {
		CloseableHttpClient httpClient = null;
		String response = null;
		try {
			response = postForm(url, params, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return response;
	}
	/**
	 * http get提交
	 * @param url
	 * @param headers
	 * @param params <k, v>参数
	 * @return
	 */
	public static String get(String url, Map<String, Object> params, Charset charset, Header[] headers) throws IOException, Exception {
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			result = get(url, params, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	/**
	 * post form ssl 提交
	 * @param url
	 * @param params
	 * @param charset
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String postFormSsl(String url, Map<String, Object> params, Charset charset, Header[] headers) throws Exception {
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient();
			result = postForm(url, params, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	/**
	 * post form ssl 提交
	 * @param url
	 * @param keystoreFile
	 * @param password
	 * @param params
	 * @param charset
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String postFormSsl(String url, String keystoreFile, String password, Map<String, Object> params, Charset charset, Header[] headers) throws Exception {
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient(keystoreFile, password);
			result = postForm(url, params, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	/**
	 * http ssl post提交
	 * @param url
	 * @param keystoreFile 证书文件
	 * @param password 密码
	 * @param headers
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 */
	public static String postSsl(String url, String keystoreFile, String password, Map<String, Object> params, String jsonBody, Charset charset, Header[] headers) throws IOException, Exception {
		
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient(keystoreFile, password);
			result = post(url, params, jsonBody, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	/**
	 * http ssl get提交
	 * @param url
	 * @param keystoreFile 证书文件
	 * @param password 密码
	 * @param headers
	 * @param params <k, v>参数
	 * @return
	 */
	public static String getSsl(String url, String keystoreFile, String password, Map<String, Object> params, Charset charset, Header[] headers) throws IOException, Exception {
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient(keystoreFile, password);
			result = get(url, params, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	/**
	 * http post ssl 提交
	 * 忽略证书
	 * @param url
	 * @param headers
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 */
	public static String postSsl(String url, Map<String, Object> params, String jsonBody, Charset charset, Header[] headers) throws IOException, Exception {
		
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient();
			result = post(url, params, jsonBody, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	
	/**
	 * http get ssl 提交
	 * 忽略证书
	 * @param url
	 * @param headers
	 * @param params <k, v>参数
	 * @return
	 */
	public static String getSsl(String url, Map<String, Object> params, Charset charset, Header[] headers) throws IOException, Exception {
		
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient();
			result = get(url, params, charset, headers, httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	
	/**
	 * 创建KV形式的参数(utf-8编码)
	 * @param params
	 * @return a=b&c=d
	 */
	public static String createKvParams(Map<String, Object> params) throws IOException, Exception {
		Charset charset = Charset.forName("utf-8");
		String kvPath = createKvParams(params, charset);
		return kvPath;
	}
	
	/**
	 * 创建KV形式的参数
	 * @param params
	 * @return a=b&c=d
	 */
	public static String createKvParams(Map<String, Object> params, Charset charset) throws IOException, Exception {
		ByteArrayOutputStream os = null;
		String kvParams = null;
		try {
			if (params != null && !params.isEmpty()) {
				List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
				for (Map.Entry<String, Object> param : params.entrySet()) {
					BasicNameValuePair nvp = new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue()));
					nvps.add(nvp);
				}
				UrlEncodedFormEntity ufe = new UrlEncodedFormEntity(nvps, charset);
				os = new ByteArrayOutputStream();

				ufe.writeTo(os);
				kvParams = os.toString();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return kvParams;
	}
	
	/**
	 * K,V参数拼接到URL上 utf-8
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static String createPath(String url, Map<String, Object> params) throws IOException, Exception {
		Charset charset = Charset.forName("utf-8");
		String path = createPath(url, params, charset);
		return path;
	}
	
	/**
	 * K,V参数拼接到URL上
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static String createPath(String url, Map<String, Object> params, Charset charset) throws IOException, Exception {
		String kvPath = createKvParams(params, charset);
		if (StringUtils.isNotBlank(kvPath)) {
			if (StringUtils.contains(url, "?")) {
				url = url + "&" + kvPath;
			} else {
				url = url + "?" + kvPath;
			}
		}
		return url;
	}
	
	/**
	 * get请求实现
	 * @param url
	 * @param headers
	 * @param params
	 * @param httpClient
	 * @return
	 * @throws ClientProtocolException 
	 * @throws Exception 
	 */
	public static String get(String url, Map<String, Object> params, Charset charset, Header[] headers, CloseableHttpClient httpClient) throws ClientProtocolException, IOException, Exception {
		HttpGet get = null;
		CloseableHttpResponse response = null;
		HttpEntity responseEntity = null;
		
		String result = null;
		try {
			url = createPath(url, params, charset);
			get = new HttpGet(url);
			if (headers != null) {
				get.setHeaders(headers);
			}
			response = httpClient.execute(get);
			if (response != null) {
				responseEntity = response.getEntity();
				result = EntityUtils.toString(responseEntity, charset);
			}
		} catch (ClientProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			if (responseEntity != null) {
				try {
					EntityUtils.consume(responseEntity);
				} catch (IOException e) {
					throw e;
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw e;
				}
			}
			if (get != null) {
				get.releaseConnection();
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	
	/**
	 * post请求实现
	 * @param url
	 * @param headers
	 * @param params
	 * @param jsonBody
	 * @param httpClient
	 * @return
	 * @throws ClientProtocolException 
	 */
	private static String post(String url, Map<String, Object> params, String jsonBody, Charset charset, Header[] headers, CloseableHttpClient httpClient) throws ClientProtocolException, IOException, Exception {
		HttpPost post = null;
		CloseableHttpResponse response = null;
		HttpEntity responseEntity = null;
		
		String result = null;
		try {
			url = createPath(url, params, charset);
			post = new HttpPost(url);
			if (headers != null) {
				post.setHeaders(headers);
			}
			if (StringUtils.isNotBlank(jsonBody)) {
				StringEntity requestEntity = new StringEntity(jsonBody, charset);
				post.setEntity(requestEntity);
			}
			response = httpClient.execute(post);
			if (response != null) {
				responseEntity = response.getEntity();
				result = EntityUtils.toString(responseEntity, charset);
			}
		} catch (ClientProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (responseEntity != null) {
				try {
					EntityUtils.consume(responseEntity);
				} catch (IOException e) {
					throw e;
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw e;
				}
			}
			if (post != null) {
				post.releaseConnection();
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	
	/**
	 * post提交表单
	 * @param url
	 * @param headers
	 * @param params
	 * @param charset
	 * @return
	 */
	public static String postForm(String url, Map<String, Object> params, Charset charset, Header[] headers, CloseableHttpClient httpClient) throws Exception {
		HttpPost post = null;
		CloseableHttpResponse response = null;
		HttpEntity responseEntity = null;
		StringEntity reqEntity = null;
		
		String result = null;
		try {
			post = new HttpPost(url);
			if (headers != null) {
				post.setHeaders(headers);
			}
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> nvps = new ArrayList<>();
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
					nvps.add(nvp);
				}
				reqEntity = new UrlEncodedFormEntity(nvps, charset);
			}
			if (reqEntity != null) {
				post.setEntity(reqEntity);
			}
			response = httpClient.execute(post);
			if (response != null) {
				responseEntity = response.getEntity();
				result = EntityUtils.toString(responseEntity, charset);
			}
		} catch (ClientProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (responseEntity != null) {
				try {
					EntityUtils.consume(responseEntity);
				} catch (IOException e) {
					throw e;
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw e;
				}
			}
			if (post != null) {
				post.releaseConnection();
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return result;
	}
	
	/**
	 * SSL的HttpClient，信任所有证书
	 * 
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 */
	private static CloseableHttpClient createSSLClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		CloseableHttpClient httpClient = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			throw e;
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} catch (KeyStoreException e) {
			throw e;
		}
		return httpClient;
	}

	/**
	 * SSL的HttpClient，相信自己的CA和所有自签名的证书
	 * 支持TLS/SSL/SSLv2协议
	 * 
	 * @param keystoreFile
	 * @param password
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws KeyManagementException
	 */
	private static CloseableHttpClient createSSLClient(String keystoreFile, String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException {
		CloseableHttpClient httpClient = null;
		FileInputStream fis = null;
		try {
			KeyStore truststore = KeyStore.getInstance(KeyStore.getDefaultType());
			fis = new FileInputStream(new File(keystoreFile));
			// 加载keyStore
			truststore.load(fis, password.toCharArray());

			// 相信自己的CA和所有自签名的证书
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(truststore, new TrustSelfSignedStrategy())
					.build();
			// 支持TLS/SSL/SSLv2协议
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
						sslContext,
						new String[] {
								SSLConnectionSocketFactory.TLS,
								SSLConnectionSocketFactory.SSL,
								SSLConnectionSocketFactory.SSLV2
						},
						null,
						SSLConnectionSocketFactory.getDefaultHostnameVerifier()
					);
			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return httpClient;
	}
	
	/**
	 * 默认Header[]
	 * @return
	 */
	private static Header[] defaultHeaders() {
		Header[] headers = {
				new BasicHeader("Content-Type", "application/json"),
//				new BasicHeader("Content-Type", "text/html")
		};
		return headers;
	}
	
	public static void main(String[] args) throws Exception {
		String url = "https://www.baidu.com?id=123";
		Map<String, Object> params = new HashMap<>();
		params.put("appid", 1234L);
		params.put("age", 10);
		params.put("money", 1.001D);
		params.put("secret", "AFDXD");
		params.put("cn", "中国");
		params.put("and", "?");
		String kvParams = createKvParams(params);
		if (StringUtils.contains(url, "?")) {
			url = url + "&" + kvParams;
		} else {
			url = url + "?" + kvParams;
		}
		System.out.println(url);
		String resp = getSsl(url, params);
		System.out.println(resp);
	}
}
