package hello;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.prefs.InvalidPreferencesFormatException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class ProxySender extends Sender {

	public ProxySender(final String key) {
		super(key);
		System.setProperty("https.proxySet", "true");
		System.setProperty("https.proxyHost", "proxy");
		System.setProperty("https.proxyPort", "3128");
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "proxy");
		System.setProperty("http.proxyPort", "3128");
	}

	@Override
	public Result send(final Message message, final String registrationId, final int retries) throws IOException {
		return super.send(message, registrationId, retries);
	}

	@Override
	public Result sendNoRetry(final Message message, final String registrationId) throws IOException {
		return super.sendNoRetry(message, registrationId);
	}

	@Override
	public MulticastResult send(final Message message, final List<String> regIds, final int retries) throws IOException {
		return super.send(message, regIds, retries);
	}

	@Override
	public MulticastResult sendNoRetry(final Message message, final List<String> registrationIds) throws IOException {
		return super.sendNoRetry(message, registrationIds);
	}

	@Override
	protected HttpURLConnection post(final String url, final String body) throws IOException {
		return super.post(url, body);
	}

	@Override
	protected HttpURLConnection post(final String url, final String contentType, final String body) throws IOException {
		return super.post(url, contentType, body);
	}

	@Override
	protected HttpURLConnection getConnection(final String url) throws IOException {
		return super.getConnection(url);
	}

}
