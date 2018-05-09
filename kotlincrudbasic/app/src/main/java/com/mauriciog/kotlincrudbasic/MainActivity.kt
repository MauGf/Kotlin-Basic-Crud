package com.mauriciog.kotlincrudbasic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var  myDb  : DataBaseHelper
    internal lateinit var  edit1 : EditText
    internal lateinit var  edit2 : EditText
    internal lateinit var  edit3 : EditText
    internal lateinit var  edit4 : EditText
    internal lateinit var  btnUpdate : Button
    internal lateinit var  btnDelete : Button
    internal lateinit var  btn   : Button
    internal lateinit var  btn2  : Button
    internal lateinit var  txt   : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDb = DataBaseHelper(this)

        edit1 = findViewById<View>(R.id.id_name)   as EditText
        edit2 = findViewById<View>(R.id.id_email)  as EditText
        edit3 = findViewById<View>(R.id.id_mobile) as EditText
        edit4 = findViewById<View>(R.id.id_txt)    as EditText
        btnUpdate   = findViewById<View>(R.id.id_update) as Button
        btnDelete   = findViewById<View>(R.id.id_delete) as Button
        btn   = findViewById<View>(R.id.insert)    as Button
        btn2  = findViewById<View>(R.id.id_read)   as Button
        txt   = findViewById<View>(R.id.txt)       as TextView

        btn2.setOnClickListener {
            ReadMe()
        }
        btn.setOnClickListener{(ClicKMe())}
        btnUpdate.setOnClickListener { UpdateMe() }
        btnDelete.setOnClickListener { DeleteMe() }
    }

    // Leer Datos
    private fun ReadMe() {
        val res    = myDb.allData
        val buffer = StringBuffer()
        if(res != null && res.count>0){
            while (res.moveToNext()){
                buffer.append("ID: " + res.getString(0) + "\n")
                buffer.append("NAME: " + res.getString(1) + "\n")
                buffer.append("EMAIL: " + res.getString(2) + "\n")
                buffer.append("MOBILE: " + res.getString(3) + "\n\n")
            }
            txt.text = buffer.toString()
            Toast.makeText(this, "Data Retrieved Successfully", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "Data Retrieved Failed", Toast.LENGTH_SHORT).show()

        }

    }
    //inset data
    private fun ClicKMe() {

        val name   = edit1.text.toString()
        val email  = edit2.text.toString()
        val mobile = edit3.text.toString()

        val result = myDb.insertData(name, email, mobile)
        if (result == true)
            Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "Data insertion Failed", Toast.LENGTH_SHORT).show()
    }

    //Update data
    private fun UpdateMe(){
        val name   = edit1.text.toString()
        val email  = edit2.text.toString()
        val mobile = edit3.text.toString()
        val id     = edit4.text.toString()

        val result = myDb.updateData(id, name, email, mobile)
        if (result == true)
            Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "Data updation Failed", Toast.LENGTH_SHORT).show()
    }

    //Delete Data
    private fun DeleteMe(){
        val id     = edit4.text.toString()
        val result = myDb.deleteData(id)
        Toast.makeText(this, "Row has been  deleted Successfully", Toast.LENGTH_SHORT).show()

    }
}
