package com.lib.json;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URLEncodedUtils;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.util.EntityUtils;

import com.lib.Debug;

import android.net.Uri;

public class ServiceHandler {

	static String response = null;
	public final static String GET = "GET";
	public final static String POST = "POST";
	public int timeoutMillis = 3000;
	public String user_agent = Browser.Mozilla.getValue();
	private String charset = "utf8";
	private boolean isDebug = false;
	public String TOKEN = null;
	private HttpURLConnection connection = null;

	@Deprecated
	public ServiceHandler() {
	}

	public ServiceHandler(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public String getErrorConver(String response) {
		return response = (response.startsWith("[")) ? response : response.substring(1);
	}

	/**
	 * Viết hỗ trợ dưới API 23
	 * 
	 * @param url
	 * @param method
	 * @param params
	 * @return
	 */
	// public String makeServiceCall(String url, String method,
	// List<NameValuePair> params) {
	// try {
	// HttpParams httpParameters = new BasicHttpParams();
	// HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutMillis);
	// HttpConnectionParams.setSoTimeout(httpParameters, timeoutMillis);
	// HttpClient httpClient = new DefaultHttpClient(httpParameters);
	// HttpEntity httpEntity = null;
	// HttpResponse httpResponse = null;
	// // httpResponse.setHeader("Content-type", "application/json");
	// if (method.equals(POST)) {
	// HttpPost httpPost = new HttpPost(url);
	// if (params != null) {
	// httpPost.setEntity(new UrlEncodedFormEntity(params));
	// }
	// httpResponse = httpClient.execute(httpPost);
	//
	// } else if (method.equals(GET)) {
	// if (params != null) {
	// String paramString = URLEncodedUtils.format(params, "utf-8");
	// url += "?" + paramString;
	// }
	// if (isDebug) {
	// Debug.e("link:" + url);
	// }
	// HttpGet httpGet = new HttpGet(url);
	// httpResponse = httpClient.execute(httpGet);
	// }
	// httpEntity = httpResponse.getEntity();
	// response = EntityUtils.toString(httpEntity);
	// return response;
	// } catch (Exception e) {
	// Debug.e("Lỗi: " + e.toString());
	// return null;
	// }
	// }

	/**
	 * Viết hỗ trợ trên API 23
	 * 
	 * @url - url to make request
	 * @method - http request method
	 * @params - http request params
	 */
	public String PostRespont(String url, Uri.Builder builder) {
		if (isDebug) {
			Debug.e("link: " + url);
		}
		try {
			URL web = new URL(URLDecoder.decode(url, "utf8"));
			connection = (HttpURLConnection) web.openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			connection.setReadTimeout(timeoutMillis);
			connection.setConnectTimeout(timeoutMillis);
			connection.setRequestProperty("Accept", "*/*");
			if (TOKEN != null)
				connection.setRequestProperty("Authorization", TOKEN);
			connection.setRequestMethod(POST);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(true);
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			if (builder != null) {
				String query = builder.build().getEncodedQuery();
				if (isDebug) {
					Debug.e("PARAM: " + query);
				}
				writer.write(query);
			}
			writer.flush();
			writer.close();
			os.close();
			connection.connect();

			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				Debug.e(connection.getResponseMessage());
				return null;
			}

			InputStream in = connection.getInputStream();
			return readData(in);
		} catch (Exception e) {
			Debug.e("Lỗi " + e.toString());
			return null;
		} finally {
			connection.disconnect();
		}
	}

	public String PostRespont(String url) {
		return PostRespont(url, null);
	}

	public String GetRespont(String url) {
		if (isDebug) {
			Debug.e("link: " + url);
		}
		try {
			URL web = new URL(url);
			connection = (HttpURLConnection) web.openConnection();
			connection.setRequestMethod(GET);
			connection.setReadTimeout(timeoutMillis);
			connection.setConnectTimeout(timeoutMillis);
			connection.setRequestProperty("User-Agent", user_agent);
			if (TOKEN != null)
				connection.setRequestProperty("Authorization", TOKEN);
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			connection.setRequestProperty("Accept", "*/*");
			connection.setDoOutput(false);
			connection.setUseCaches(true);
			connection.setInstanceFollowRedirects(false);
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				Debug.e(connection.getResponseMessage());
				return null;
			}

			InputStream in = connection.getInputStream();
			return readData(in);
		} catch (Exception e) {
			Debug.e("Lỗi: " + e.toString());
			return null;
		} finally {
			connection.disconnect();
		}
	}

	public String GetRespont(String url, LinkedHashMap<String, String> param) {

		try {
			int i = 0;
			for (Entry<String, String> e : param.entrySet()) {
				if (i == 0)
					url += "?" + e.getKey() + "=" + e.getValue() + "&";
				i++;
			}
			url = url.replaceAll("/&$/", "");
			if (isDebug) {
				Debug.e("link: " + url);
			}
			URL web = new URL(url);
			connection = (HttpURLConnection) web.openConnection();
			connection.setRequestMethod(GET);
			connection.setReadTimeout(timeoutMillis);
			connection.setConnectTimeout(timeoutMillis);
			connection.setRequestProperty("User-Agent", user_agent);
			if (TOKEN != null)
				connection.setRequestProperty("Authorization", TOKEN);
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			connection.setRequestProperty("Accept", "*/*");
			connection.setDoOutput(false);
			connection.setUseCaches(true);
			connection.setInstanceFollowRedirects(false);
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				Debug.e(connection.getResponseMessage());
				return null;
			}

			InputStream in = connection.getInputStream();
			return readData(in);
		} catch (Exception e) {
			Debug.e("Lỗi: " + e.toString());
			return null;
		} finally {
			connection.disconnect();
		}
	}

	private String readData(InputStream in) throws Exception {
		StringBuffer buffer = null;
		InputStreamReader isw = new InputStreamReader(in);
		int data = isw.read();
		buffer = new StringBuffer();
		while (data != -1) {
			char current = (char) data;
			data = isw.read();
			buffer.append(current);
		}
		return buffer.toString().replace("\n", "");
	}

}
