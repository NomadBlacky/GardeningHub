package org.nomadblacky.gardeninghub

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.beust.klaxon.obj
import com.beust.klaxon.string
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    val logger: Logger = Logger.getLogger(javaClass.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val textViewUserName = findViewById(R.id.editTextGithubUserName) as TextView
        // TODO: Insert saved username.
        textViewUserName.text = "NomadBlacky"

        val resultView = findViewById(R.id.editTextResult) as TextView

        val execButton = findViewById(R.id.buttonExec)
        execButton.setOnClickListener {
            logger.info("onClick")
            GitHubApiTask(textViewUserName.text.toString()) { jsonAry ->
                jsonAry.fold(StringBuilder()) { sb, jsonObj ->
                    sb.appendln("${jsonObj.string("type")}: ${jsonObj.obj("repo")?.string("name") ?: "-"}")
                }.let {
                    resultView.text = it.toString()
                }
            }.execute()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
