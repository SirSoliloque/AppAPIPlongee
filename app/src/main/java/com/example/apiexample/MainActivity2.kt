package com.example.apiexample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apiexample.api.AdherentModel
import com.example.apiexample.api.ApiClient
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.apiexample.api.PlongeeClient
import com.example.apiexample.api.PlongeeModel


class MainActivity2 : AppCompatActivity() {

    private lateinit var plongeesclient: PlongeeClient

    override fun onCreate(savedInstanceState: Bundle?) {

        plongeesclient = PlongeeClient()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.listeplongees)

        val plongees: List<PlongeeModel>? = plongeesclient.getPlongees();

        var liste = emptyArray<String>()

        if (plongees != null) {
            for(plongee in plongees){
                liste.set(plongee.id, plongee.date.toString() + plongee.moment.toString())
            }
        }

        val listeplongees = findViewById<ListView>(R.id.listView)

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
             this, android.R.layout.simple_list_item_1, liste
        )

        listeplongees.adapter = arrayAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

