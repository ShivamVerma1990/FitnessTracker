package com.candroid.realtracker.articales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.candroid.realtracker.R
import com.candroid.realtracker.databinding.ActivityArticlesBinding
import com.candroid.realtracker.databinding.ActivityCovidBinding
import kotlinx.android.synthetic.main.activity_articles.*

class CovidActivity : AppCompatActivity() {
    lateinit var binding: ActivityCovidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding=DataBindingUtil.setContentView(this,R.layout.activity_covid)

        val url="https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public/myth-busters"

        if(url!=null) {

            binding.we.settings.javaScriptEnabled = true
            binding.we.settings.userAgentString =
                "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
            binding.we.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar4.visibility = View.GONE
                    we.visibility = View.VISIBLE

                }


            }

            we.loadUrl(url)
        }

    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }

}
