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
	 * http post提交
	 * @param url
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 */
	public static String post(String url, Map<String, Object> params, String jsonBody) {
		Header[] headers = defaultHeaders();
		return post(url, headers, params, jsonBody);
	}
	/**
	 * http post提交
	 * @param url
	 * @param headers
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 */
	public static String post(String url, Header[] headers, Map<String, Object> params, String jsonBody) {
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			result = post(url, headers, params, jsonBody, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * http get提交
	 * @param url
	 * @param params <k, v>参数
	 * @return
	 */
	public static String get(String url, Map<String, Object> params) {
		Header[] headers = defaultHeaders();
		return get(url, headers, params);
	}
	/**
	 * http get提交
	 * @param url
	 * @param headers
	 * @param params <k, v>参数
	 * @return
	 */
	public static String get(String url, Header[] headers, Map<String, Object> params) {
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			result = get(url, headers, params, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
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
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 */
	public static String postSsl(String url, String keystoreFile, String password, Map<String, Object> params, String jsonBody) {
		Header[] headers = defaultHeaders();
		return postSsl(url, keystoreFile, password, headers, params, jsonBody);
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
	public static String postSsl(String url, String keystoreFile, String password, Header[] headers, Map<String, Object> params, String jsonBody) {
		
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient(keystoreFile, password);
			result = post(url, headers, params, jsonBody, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
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
	 * @param params <k, v>参数
	 * @return
	 */
	public static String getSsl(String url, String keystoreFile, String password, Map<String, Object> params) {
		Header[] headers = defaultHeaders();
		return getSsl(url, keystoreFile, password, headers, params);
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
	public static String getSsl(String url, String keystoreFile, String password, Header[] headers, Map<String, Object> params) {
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient(keystoreFile, password);
			result = get(url, headers, params, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * http post ssl 提交
	 * 忽略证书
	 * @param url
	 * @param params <k, v>参数
	 * @param jsonBody
	 * @return
	 */
	public static String postSsl(String url, Map<String, Object> params, String jsonBody) {
		Header[] headers = defaultHeaders();
		return postSsl(url, headers, params, jsonBody);
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
	public static String postSsl(String url, Header[] headers, Map<String, Object> params, String jsonBody) {
		
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient();
			result = post(url, headers, params, jsonBody, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * http get ssl 提交
	 * 忽略证书
	 * @param url
	 * @param params <k, v>参数
	 * @return
	 */
	public static String getSsl(String url, Map<String, Object> params) {
		Header[] headers = defaultHeaders();
		return getSsl(url, headers, params);
	}
	
	/**
	 * http get ssl 提交
	 * 忽略证书
	 * @param url
	 * @param headers
	 * @param params <k, v>参数
	 * @return
	 */
	public static String getSsl(String url, Header[] headers, Map<String, Object> params) {
		
		CloseableHttpClient httpClient = null;
		String result = null;
		try {
			httpClient = createSSLClient();
			result = get(url, headers, params, httpClient);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
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
	public static String createKvParams(Map<String, Object> params) {
		ByteArrayOutputStream os = null;
		String kvParams = null;
		try {
			if (params != null && !params.isEmpty()) {
				List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
				for (Map.Entry<String, Object> param : params.entrySet()) {
					BasicNameValuePair nvp = new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue()));
					nvps.add(nvp);
				}
				UrlEncodedFormEntity ufe = new UrlEncodedFormEntity(nvps, Charset.forName("utf-8"));
				os = new ByteArrayOutputStream();

				ufe.writeTo(os);
				kvParams = os.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return kvParams;
	}
	
	/**
	 * K,V参数拼接到URL上
	 * @param url
	 * @param params
	 * @return
	 */
	public static String createPath(String url, Map<String, Object> params) {
		String kvPath = createKvParams(params);
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
	 */
	private static String get(String url, Header[] headers, Map<String, Object> params, CloseableHttpClient httpClient) {
		HttpGet get = null;
		CloseableHttpResponse response = null;
		HttpEntity responseEntity = null;
		
		String result = null;
		try {
			url = createPath(url, params);
			get = new HttpGet(url);
			if (headers != null) {
				get.setHeaders(headers);
			}
			response = httpClient.execute(get);
			if (response != null) {
				responseEntity = response.getEntity();
				result = EntityUtils.toString(responseEntity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (responseEntity != null) {
				try {
					EntityUtils.consume(responseEntity);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (get != null) {
				get.releaseConnection();
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
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
	 */
	private static String post(String url, Header[] headers, Map<String, Object> params, String jsonBody, CloseableHttpClient httpClient) {
		HttpPost post = null;
		CloseableHttpResponse response = null;
		HttpEntity responseEntity = null;
		
		String result = null;
		try {
			url = createPath(url, params);
			post = new HttpPost(url);
			if (headers != null) {
				post.setHeaders(headers);
			}
			if (StringUtils.isNotBlank(jsonBody)) {
				StringEntity requestEntity = new StringEntity(jsonBody, Charset.forName("utf-8"));
				post.setEntity(requestEntity);
			}
			response = httpClient.execute(post);
			if (response != null) {
				responseEntity = response.getEntity();
				result = EntityUtils.toString(responseEntity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (responseEntity != null) {
				try {
					EntityUtils.consume(responseEntity);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (post != null) {
				post.releaseConnection();
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
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
	private static CloseableHttpClient createSSLClient()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
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
	private static CloseableHttpClient createSSLClient(String keystoreFile, String password)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException,
			KeyManagementException {
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
				new BasicHeader("Content-Type", "application/json;charset=utf-8")
		};
		return headers;
	}
	
	public static void main(String[] args) {
		String url = "http://www.baidu.com?id=123";
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
	}
}
