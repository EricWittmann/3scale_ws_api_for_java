package threescale.v3.api.impl;

import java.util.concurrent.Future;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import threescale.v3.api.HttpResponse;
import threescale.v3.api.ServerAccessor;
import threescale.v3.api.ServerError;

public class AsyncServerAccessor extends AbstractServerAccessor implements ServerAccessor {

	AsyncHttpClient httpClient = new DefaultAsyncHttpClient();

    /**
     * @param urlParams url + parameter string
     * @return Http Response
     * @throws ServerError
     * @see ServerAccessor
     */
    public HttpResponse get(final String urlParams) throws ServerError {
    	try {
            Future<Response> f = httpClient.prepareGet(urlParams)
            		.setRequestTimeout(10000)
            		.addHeader("Accept-Charset", "UTF-8")
            		.addHeader(X_3SCALE_USER_CLIENT_HEADER, pluginHeaderValue)
            		.execute();
			Response r = f.get();
            return new HttpResponse(r.getStatusCode(), r.getResponseBody());
		} catch (Exception ex) {
            throw new RuntimeException(ex);
		}
    }

    /**
     * @param urlParams url to access
     * @param data      The data to be sent
     * @return Response from the server
     * @throws ServerError
     * @see ServerAccessor
     */
    public HttpResponse post(final String urlParams, final String data) throws ServerError {
    	try {
            Future<Response> f = httpClient.preparePost(urlParams)
            		.setRequestTimeout(10000)
                    .addHeader("Accept", "*/*")
                    .addHeader("Accept-Charset", "UTF-8")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader(X_3SCALE_USER_CLIENT_HEADER, pluginHeaderValue)
                    .setBody(data)
            		.execute();
			Response r = f.get();
            return new HttpResponse(r.getStatusCode(), r.getResponseBody());
		} catch (Exception ex) {
            throw new RuntimeException(ex);
		}
    }

}
