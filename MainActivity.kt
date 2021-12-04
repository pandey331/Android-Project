package com.example.dailyhunt
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var recyclerView: RecyclerView
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        requestQueue = Volley.newRequestQueue(this)
        fetchData()


    }


    private fun fetchData() {
        //volly library
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=750fb56dd11a49d69e939229ab4ff879"
        //making a request
        val jsonObjectRequest = object : JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener {
                    val newsJsonArray = it.getJSONArray("articles")
                    val newsArray = ArrayList<News>()
                    System.out.println("jnhgyftuh" + newsArray + newsJsonArray)
                    for (i in 0 until newsJsonArray.length()) {
                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                        val news = News(
                                newsJsonObject.getString("title"),
                                newsJsonObject.getString("author"),
                                newsJsonObject.getString("url"),
                                newsJsonObject.getString("urlToImage")
                        )
                        System.out.println("hgtfrtdersdthj" + newsJsonObject.getString("title"))


                        newsArray.add(news)
                    }

                    val adapter = NewsListAdapter(this, newsArray)
                    recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
                    recyclerView.setHasFixedSize(true)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    adapter.updateNews(newsArray)
                },
                Response.ErrorListener {
                }

        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        requestQueue?.add(jsonObjectRequest)
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}