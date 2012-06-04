package org.ktonga.greenpeace

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

class DownloaderService {
    private static final int CON_TIMEOUT = 5000;
	private static final int SO_TIMEOUT = 5000;

    public String download(String url) {
        try {
            url = url.trim();

            DefaultHttpClient client = new DefaultHttpClient(defaultHttpParams());
            HttpGet method = new HttpGet(url);
            HttpResponse response;
            try {
                response = client.execute(method);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String content = EntityUtils.toString(entity);
                    return content;
                }
            } catch (IOException e) {
            	log.error("An error occured", e);
            }
            return "";
        } catch (IllegalArgumentException e) {
            /**
             * The constructor HttpGet(String) uses the method URI.create wich may throw an IllegalArgumentException:
             * 
             * This convenience factory method works as if by invoking the URI(String) constructor; any
             * URISyntaxException thrown by the constructor is caught and wrapped in a new IllegalArgumentException
             * object, which is then thrown.
             */
            return "";
        }
    }
    
    private HttpParams defaultHttpParams() {
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used. 
		HttpConnectionParams.setConnectionTimeout(httpParameters, CON_TIMEOUT);
		// Set the default socket timeout (SO_TIMEOUT) 
		// in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(httpParameters, SO_TIMEOUT);
		return httpParameters;
	}
}
