package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModelMain: ViewModelMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelMain= ViewModelProvider(this).get(ViewModelMain::class.java)
        createList()


        auth=FirebaseAuth.getInstance()
        binding.fab.setOnClickListener {
            val builder= AlertDialog.Builder(this)
            val inflate=layoutInflater
            val dialogLayout=inflate.inflate(R.layout.edittext_dialog,null)
            val text=dialogLayout.findViewById<EditText>(R.id.editText)
            with(builder){
                setTitle("Enter the number")
                setPositiveButton("OK"){dialog,which->
                    if(!text.text.toString().isEmpty()){
                        var count =text.text.toString().toInt()
                        viewModelMain.updateCount(count)
                        createList()
                    }

                }
                setView(dialogLayout)
                show()
            }


        }
    }

    private fun createList() {
        val adaptor: ArrayAdapter<Int> = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,viewModelMain.getList())
        binding.listView.adapter=adaptor
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.logout,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        auth.signOut()
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        return super.onOptionsItemSelected(item)
    }


}
