package org.nomadblacky.gardeninghub

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    val logger: Logger = Logger.getLogger(javaClass.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "そんなものはない", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val textViewUserName = findViewById(R.id.editTextGithubUserName) as TextView
        // TODO: Insert saved username.
        textViewUserName.text = "NomadBlacky"

        val resultView = findViewById(R.id.editTextResult) as TextView

        val execButton = findViewById(R.id.buttonExec)
        execButton.setOnClickListener {
            logger.info("onClick")
            GitHubApiTask(textViewUserName.text.toString()) {
                resultView.text = it
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
