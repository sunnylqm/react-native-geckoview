package cn.reactnative;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class GeckoViewManager extends SimpleViewManager<View> {

    public static final String REACT_CLASS = "GeckoView";
    private static GeckoRuntime mGeckoRuntime = null;

    protected static final String HTML_ENCODING = "UTF-8";
    protected static final String HTML_MIME_TYPE = "text/html";
    protected static final String JAVASCRIPT_INTERFACE = "ReactNativeWebView";
    protected static final String HTTP_METHOD_POST = "POST";
    // Use `webView.loadUrl("about:blank")` to reliably reset the view
    // state and release page resources (including any running JavaScript).
    protected static final String BLANK_URL = "about:blank";

    @Override
    public String getName() {
        return REACT_CLASS;
    }


    @Override
    public View createViewInstance(ThemedReactContext c) {
        GeckoView view = new GeckoView(c);
        GeckoSession session = new GeckoSession();
        if (mGeckoRuntime == null) {
            mGeckoRuntime = GeckoRuntime.create(c);
        }
        session.open(mGeckoRuntime);
        view.setSession(session);
        view.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @ReactProp(name = "source")
    public void setSource(GeckoView view, @Nullable ReadableMap source) {
        GeckoSession session = view.getSession();
        if (source != null) {

            if (source.hasKey("html")) {
                String html = source.getString("html");
//                String baseUrl = source.hasKey("baseUrl") ? source.getString("baseUrl") : "";
                session.loadString(html, HTML_MIME_TYPE);
                return;
            }
            if (source.hasKey("uri")) {
                String url = source.getString("uri");
                /*
                String previousUrl = view.getUrl();
                if (previousUrl != null && previousUrl.equals(url)) {
                    return;
                }
                if (source.hasKey("method")) {
                    String method = source.getString("method");
                    if (method.equalsIgnoreCase(HTTP_METHOD_POST)) {
                        byte[] postData = null;
                        if (source.hasKey("body")) {
                            String body = source.getString("body");
                            try {
                                postData = body.getBytes("UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                postData = body.getBytes();
                            }
                        }
                        if (postData == null) {
                            postData = new byte[0];
                        }
                        view.postUrl(url, postData);
                        return;
                    }
                }
                HashMap<String, String> headerMap = new HashMap<>();
                if (source.hasKey("headers")) {
                    ReadableMap headers = source.getMap("headers");
                    ReadableMapKeySetIterator iter = headers.keySetIterator();
                    while (iter.hasNextKey()) {
                        String key = iter.nextKey();
                        if ("user-agent".equals(key.toLowerCase(Locale.ENGLISH))) {
                            if (view.getSettings() != null) {
                                view.getSettings().setUserAgentString(headers.getString(key));
                            }
                        } else {
                            headerMap.put(key, headers.getString(key));
                        }
                    }
                }
                view.loadUrl(url, headerMap);
                */
                session.loadUri(url);
                return;
            }
        }
        session.loadUri(BLANK_URL);
    }
}
