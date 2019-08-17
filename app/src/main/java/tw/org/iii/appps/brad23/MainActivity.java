package tw.org.iii.appps.brad23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    123);
        }

        webView = findViewById(R.id.webview);
        initWebView();
    }

    private void initWebView(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        WebViewClient client = new WebViewClient();
        webView.setWebViewClient(client);

        webView.addJavascriptInterface(new MyBradJS(), "brad");

        webView.loadUrl("file:///android_asset/brad.html");

    }

    public class MyBradJS {
        @JavascriptInterface
        public void callFromJS(){
            gotoScan();
        }
    }

    private void gotoScan(){
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

}
