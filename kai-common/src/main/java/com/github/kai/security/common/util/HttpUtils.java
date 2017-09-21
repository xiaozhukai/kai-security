package com.github.kai.security.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用HttpClient3 实现get、post及 ssl get、ssl post.<br/>
 *
 * 也用到了HttpsURLConnection.
 *
 * @author Administrator
 *
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static final String CONTACT_TYPE_DEFAULT = "text/plain;charset=UTF-8";

    private static final int CONNECTION_TIMEOUT = 5000; // 连接超时时间，默认5s
    private static final int READ_TIMEOUT = 60000; // 读超时时间，默认60s

    public static String executeGet(String url) throws Exception {
        byte[] responseBody = executeGetAsByte(url);
        return new String(responseBody, "UTF-8");
    }

    /**
     *
     * 非安全模式调用.
     *
     * @param url
     * @return 响应字节数组
     * @throws Exception
     */
    public static byte[] executeGetAsByte(String url) throws Exception {

        if (StringUtils.startsWith(url, "https://")) {
            return HttpUtils.executeSSLGetAsByte(url);
        }

        byte[] responseBody = null;
        GetMethod getMethod = null;
        try {
            // 定义HttpClient
            HttpClient client = new HttpClient();
            client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            client.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
            client.getHttpConnectionManager().getParams().setSoTimeout(READ_TIMEOUT);

            // 实例化HTTP方法
            getMethod = new GetMethod(url);
            getMethod.setRequestHeader("Content-Type", "text/html;charset=utf-8");
            // 使用系统提供的默认的恢复策略,此处HttpClient的恢复策略可以自定义（通过实现接口HttpMethodRetryHandler来实现）。
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

            // 执行getMethod
            int statusCode = client.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                throw new Exception(getMethod.getStatusText());
            }
            // 读取内容
            responseBody = getMethod.getResponseBody();
            // 处理内容

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            // 释放连接
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    /**
     *
     * 安全模式调用.
     *
     * @param url
     * @return 响应字节数组
     * @throws Exception
     */
    public static byte[] executeSSLGetAsByte(String url) throws Exception {

        if (StringUtils.startsWith(url, "http://")) {
            return HttpUtils.executeGetAsByte(url);
        }

        byte[] responseBody = null;

        HttpsURLConnection connection = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            int code = connection.getResponseCode();
            logger.info("http code : {}", code);

            if (200 == code) {
                in = connection.getInputStream();

                out = new ByteArrayOutputStream();
                byte[] b = new byte[4096];
                for (int n; (n = in.read(b)) != -1;) {
                    out.write(b, 0, n);
                }
                responseBody = out.toByteArray();
            }

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            closeOutputStream(out);
            closeInputStream(in);
            closeConnection(connection);
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    public static String executePost(String url, String requestBody) throws Exception {

        String responseBody = "";
        PostMethod method = null;
        try {

            HttpClient client = new HttpClient();
            client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            client.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
            client.getHttpConnectionManager().getParams().setSoTimeout(READ_TIMEOUT);

            method = new PostMethod(url);
            method.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

            // 请求参数
            InputStream input = new ByteArrayInputStream(requestBody.getBytes("UTF-8"));
            method.setRequestEntity(new InputStreamRequestEntity(input));

            int statusCode = client.executeMethod(method);
            logger.info("http status = {}", statusCode);

            if (HttpStatus.SC_OK == statusCode) {
                byte[] bys = method.getResponseBody();
                responseBody = new String(bys, "UTF-8");
            }

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    public static String executePostHttpConn(String url, String requestBody) throws Exception {
        String responseBody = "";

        HttpURLConnection connection = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            connection.getOutputStream().write(requestBody.getBytes("UTF-8"));
            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            int code = connection.getResponseCode();
            logger.info("http code : {}", code);

            if (200 == code) {
                in = connection.getInputStream();
                out = new ByteArrayOutputStream();
                byte[] b = new byte[4096];
                for (int n; (n = in.read(b)) != -1;) {
                    out.write(b, 0, n);
                }
                responseBody = out.toString("utf-8");
            }

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            closeOutputStream(out);
            closeInputStream(in);
            closeConnection(connection);
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    public static String executePostHttpConn(String url, String requestBody, String contentType) throws Exception {
        String responseBody = "";

        HttpURLConnection connection = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            if (StringUtils.isEmpty(contentType)) {
                connection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
            } else {
                connection.setRequestProperty("Content-Type", contentType);
            }

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            connection.getOutputStream().write(requestBody.getBytes("UTF-8"));
            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            int code = connection.getResponseCode();
            logger.info("http code : {}", code);

            if (200 == code) {
                in = connection.getInputStream();

                out = new ByteArrayOutputStream();
                byte[] b = new byte[4096];
                for (int n; (n = in.read(b)) != -1;) {
                    out.write(b, 0, n);
                }
                responseBody = out.toString("utf-8");
            }

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            closeOutputStream(out);
            closeInputStream(in);
            closeConnection(connection);
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    /**
     *
     * 重载方法，加入参数request，并转发请求所有头信息.
     *
     */
    public static String executePostHttpConn(String url, String requestBody, HttpServletRequest request, String contentType) throws Exception {
        String responseBody = "";

        HttpURLConnection connection = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();

            @SuppressWarnings("rawtypes")
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {// 将请求的所有头信息转发
                String headerName = (String) headerNames.nextElement();
                connection.setRequestProperty(headerName, request.getHeader(headerName));
            }

            if (StringUtils.isEmpty(contentType)) {
                connection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
            } else {
                connection.setRequestProperty("Content-Type", contentType);
            }
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            connection.getOutputStream().write(requestBody.getBytes("UTF-8"));
            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            int code = connection.getResponseCode();
            logger.info("http code : {}", code);

            if (200 == code) {
                in = connection.getInputStream();
                out = new ByteArrayOutputStream();
                byte[] b = new byte[4096];
                for (int n; (n = in.read(b)) != -1;) {
                    out.write(b, 0, n);
                }

                responseBody = out.toString("utf-8");
            }

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            closeOutputStream(out);
            closeInputStream(in);
            closeConnection(connection);
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    public static String executeSSLGet(String url) throws Exception {

        if (StringUtils.startsWith(url, "http://")) {
            return executeGet(url);
        }

        String responseBody = "";

        HttpsURLConnection connection = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {

            connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            int code = connection.getResponseCode();
            logger.info("http code : {}", code);

            if (200 == code) {
                in = connection.getInputStream();
                out = new ByteArrayOutputStream();
                byte[] b = new byte[4096];
                for (int n; (n = in.read(b)) != -1;) {
                    out.write(b, 0, n);
                }
                responseBody = out.toString("utf-8");
            }

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            closeOutputStream(out);
            closeInputStream(in);
            closeConnection(connection);
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    public static String executeSSLPost(String url, String request) throws Exception {

        logger.debug("请求报文：\n{}", request);

        url = url.trim();
        if (StringUtils.startsWith(url, "http://")) {
            return HttpUtils.executePost(url, request);
        }

        String responseBody = "";

        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {

            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            connection.getOutputStream().write(request.getBytes("UTF-8"));
            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            int code = connection.getResponseCode();
            logger.info("http code : {}", code);

            if (200 == code) {

                in = new InputStreamReader(connection.getInputStream(), "UTF-8");

                StringWriter output = new StringWriter();
                int n = 0;
                char[] buffer = new char[1024 * 4];
                while (-1 != (n = in.read(buffer))) {
                    output.write(buffer, 0, n);
                }
                responseBody = output.toString();

                output.close();
            }

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            closeInputStreamReader(in);
            closeConnection(connection);
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    public static String executeSSLPostInputStream(String url, InputStream input) throws Exception {
        logger.debug("流文件ssl post提交URL=\n{}", url);

        String responseBody = "";

        HttpURLConnection connection = null;
        InputStreamReader ins = null;
        try {

            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);

            StreamUtils.write(input, connection.getOutputStream());

            connection.getOutputStream().flush();
            connection.getOutputStream().close();

            int code = connection.getResponseCode();
            logger.info("http code : {}", code);

            if (200 == code) {

                ins = new InputStreamReader(connection.getInputStream(), "UTF-8");

                StringWriter output = new StringWriter();
                int n = 0;
                char[] buffer = new char[1024 * 4];
                while (-1 != (n = ins.read(buffer))) {
                    output.write(buffer, 0, n);
                }
                responseBody = output.toString();

                output.close();
            }

        } catch (Exception e) {
            handleSocketTimeoutException(e, url);
            throw e;
        } finally {
            closeInputStream(input);
            closeInputStreamReader(ins);
            closeConnection(connection);
        }

        logger.debug("响应报文：{}", responseBody);

        return responseBody;
    }

    private static void closeOutputStream(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.close();
                outputStream = null;
            }
        } catch (Exception e) {
        }
    }

    private static void closeInputStream(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
        } catch (Exception e) {
        }
    }

    private static void closeConnection(HttpURLConnection connection) {
        try {
            if (connection != null) {
                connection.disconnect();
            }
        } catch (Exception e) {
        }
    }

    private static void closeInputStreamReader(InputStreamReader inputStreamReader) {
        try {
            if (inputStreamReader != null) {
                inputStreamReader.close();
                inputStreamReader = null;
            }
        } catch (Exception e) {
        }
    }

    private static class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

    private static class MyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    static {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[] { new MyTrustManager() }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleSocketTimeoutException(Exception e, String url) {
        if (e instanceof SocketTimeoutException) {
            logger.error("读取超时：{}", url);
        }
    }

}