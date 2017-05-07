package org.nomadblacky.gardeninghub

import android.os.AsyncTask
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.logging.Logger

/**
 * Created by blacky on 17/05/06.
 */
class GitHubApiTask(val userName: String, val callback: (JsonArray<JsonObject>)->Unit) : AsyncTask<Void,Void,String>() {

    val logger: Logger = Logger.getLogger(javaClass.name)

    override fun doInBackground(vararg params: Void?): String {
        val request = Request.Builder()
                .url("https://api.github.com/users/$userName/events")
                .get()
                .build()
        logger.info("doRequest: $request")
        val response = OkHttpClient().newCall(request).execute()
        return response.body().string()
    }

    override fun onPostExecute(result: String?) {
        val parser = Parser()
        callback(parser.parse(StringBuilder(result)) as JsonArray<JsonObject>)
    }
}