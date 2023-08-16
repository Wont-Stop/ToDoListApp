package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutput
import java.io.ObjectOutputStream

class FileHelper {

    val FILENAME = "Listinfo.dat" // this is the file name that we will save in the device's memory

    //now we will create a method that will write data to this file

    fun writeData(item : ArrayList<String>, context: Context){


        //now we will create an object from FileOutputStream class
        var fos : FileOutputStream = context.openFileOutput(FILENAME,Context.MODE_PRIVATE) //this method will create a file in device memory and open it.
        //this method will take two parameters , first one is file name and second one is mode, by using MODE_PRIVATE we can reach this file everywhere inside the application.

        var oas = ObjectOutputStream(fos)

       // FileOutputStream and ObjectOutputStream are the two classes that are used together to write data into the file. This is they work as a team
        oas.writeObject(item)  // item will be written into the file
        oas.close() // we need to close the opened file in device cuz if it stays open, some problems might occur.
    }

    // now we will write a method to read this saved file in the device's memory

    fun readData(context:Context): ArrayList<String>
    {
        var itemList : ArrayList<String>

        try {
            var fis : FileInputStream = context.openFileInput(FILENAME)
            var ois = ObjectInputStream(fis)
            itemList = ois.readObject() as ArrayList<String>
        }catch (e : FileNotFoundException){

            itemList = ArrayList()

        }

        return itemList

    }



}