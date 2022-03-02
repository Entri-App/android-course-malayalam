package com.example.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.utils.PrefUtils
import com.example.myapplication.databinding.ActivityDashbaordBinding
import org.json.JSONObject

class DashbaordActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashbaordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashbaordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()

    }


    private fun setupUI() {
        val username = PrefUtils.getUsername(this)
    }

    companion object {
        val ID_USERNAME = "USERNAME"
        val ID_PASSWORD = "PASSWORD"

        fun start(context: Context, username: String, password: String) {
            val intent = getIntent(context)
            intent.putExtra(ID_USERNAME, username)
            intent.putExtra(ID_PASSWORD, password)
            intent.putExtra(ID_PASSWORD, password)
            context.startActivity(intent)
        }

        private fun getIntent(context: Context) = Intent(context, DashbaordActivity::class.java)
    }

    fun processJSON(view: View) {

        val jsonString = getJsonString()
        val newsObject = JSONObject(jsonString)

        val articlesArray = newsObject.getJSONArray("articles")
//        Log.d(TAG, "articlesArray: $articlesArray")
        for (i in 0 until articlesArray.length()) {
            val article = articlesArray[i] as JSONObject
            val title = article.getString("title")
            Log.d(TAG, "author of article ${i + 1}: $title")
        }


//        val status = newsObject.getString("status")
//        Log.d(TAG, "status in json: $status")
//        val totalResults = newsObject.getLong("totalResults")
//        Log.d(TAG, "total results : $totalResults")


    }

    private fun getJsonString(): String {
        return "{\n" +
                "  \"status\": \"ok\",\n" +
                "  \"totalResults\": 56103,\n" +
                "  \"articles\": [\n" +
                "    {\n" +
                "      \"source\": {\n" +
                "        \"id\": null,\n" +
                "        \"name\": \"Aajtak.in\"\n" +
                "      },\n" +
                "      \"author\": \"aajtak.in\",\n" +
                "      \"title\": \"Ukraine संकट पर बोले रूस के राष्ट्रपति पुतिन- हमारे पास कोई और विकल्प नहीं था\",\n" +
                "      \"description\": \"Russia-Ukraine: राजधानी मॉस्को में व्यापारियों से मुलाकात के दौरान रूसी राष्ट्रपति पुतिन ने कहा कि हम दुनिया की अर्थव्यवस्था को कोई नुकसान नहीं पहुंचाना चाहते.\",\n" +
                "      \"url\": \"https://www.aajtak.in/world/story/president-putin-says-no-other-way-to-defend-russia-other-than-by-invading-ukraine-ntc-1417675-2022-02-24\",\n" +
                "      \"urlToImage\": \"https://akm-img-a-in.tosshub.com/aajtak/images/story/202202/russia_3.jpg\",\n" +
                "      \"publishedAt\": \"2022-02-24T18:38:08Z\",\n" +
                "      \"content\": \"Copyright © 2022 Living Media India Limited. For reprint rights: Syndications Today\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"source\": {\n" +
                "        \"id\": null,\n" +
                "        \"name\": \"allears.net\"\n" +
                "      },\n" +
                "      \"author\": \"Madeleine Zaharchuk\",\n" +
                "      \"title\": \"Menus Revealed for the 2022 Disney California Adventure Food & Wine Festival!\",\n" +
                "      \"description\": \"Take a look at the new menus for the Disney California Adventure Food and Wine Festival! \\nThe post Menus Revealed for the 2022 Disney California Adventure Food & Wine Festival! appeared first on AllEars.Net.\",\n" +
                "      \"url\": \"https://allears.net/2022/02/24/menus-revealed-for-the-2022-disney-california-adventure-food-wine-festival/\",\n" +
                "      \"urlToImage\": \"https://allears.net/wp-content/uploads/2018/12/Disney-California-Adventure-Food-and-Wine-Festival.jpg\",\n" +
                "      \"publishedAt\": \"2022-02-24T18:35:22Z\",\n" +
                "      \"content\": \"The Disney California Adventure Food and Wine Festival is returning on March 4th, 2022! The event will be filled with special snacks, drinks, and events, plus Disney will be re-introducing the Soarin… [+13159 chars]\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"
    }


}