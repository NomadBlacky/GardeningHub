package org.nomadblacky.gardeninghub

import android.os.AsyncTask
import android.widget.TextView
import okhttp3.*
import java.io.IOException
import java.util.logging.Logger

/**
 * Created by blacky on 17/05/06.
 */
class GitHubApiTask(val userName: String, val resultView: TextView) : AsyncTask<Void,Void,String>() {

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
        resultView.text = result
    }
}