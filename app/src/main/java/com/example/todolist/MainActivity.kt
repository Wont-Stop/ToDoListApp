package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var item : EditText
    lateinit var  add : Button
    lateinit var listView : ListView

    var fileHelper = FileHelper()

    var itemList = ArrayList<String>()  // we will add items to the list view using this array list.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        listView = findViewById(R.id.list)

        itemList = fileHelper.readData(this) //this method will read data and send it to the array list if there is any data.
        //and then will send this data to the list view  using the array adapter

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,android.R.id.text1,itemList)

        listView.adapter = arrayAdapter //now list view is ready , if there is any saved data from before, it will be written to the list view

        add.setOnClickListener {

            var itemName : String = item.text.toString()
            itemList.add(itemName)
            item.setText("")
            fileHelper.writeData(itemList,applicationContext)
            arrayAdapter.notifyDataSetChanged()


        }
        listView.setOnItemClickListener { adapterView, view, position, l ->

            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete ")
            alert.setCancelable(false)
            alert.setNegativeButton("No", DialogInterface.OnClickListener{  dialogInterface, i ->

                dialogInterface.cancel()


            })
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener{dialogInterface, i->

                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                fileHelper.writeData(itemList,applicationContext)

            })
            alert.create().show()
        }

    }
}