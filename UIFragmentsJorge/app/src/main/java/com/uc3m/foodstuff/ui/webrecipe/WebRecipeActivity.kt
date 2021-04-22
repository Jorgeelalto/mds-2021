package com.uc3m.foodstuff.ui.webrecipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uc3m.foodstuff.MainActivity
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.ui.search.SearchFragment


class WebRecipeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_recipe)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Get the recipe URL
        var recipeUrl: String = intent.extras?.get("recipeUrl") as String
        if ("https" !in recipeUrl) recipeUrl = recipeUrl.replace("http", "https")
        Log.d("WebRecipeActivity", recipeUrl)
        val webView: WebView = findViewById(R.id.webview)
        // TODO additional security measures?
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                ContextCompat.startActivity(applicationContext, Intent(applicationContext, MainActivity::class.java), null)

            }
        }
        webView.loadUrl(recipeUrl)
    }
}