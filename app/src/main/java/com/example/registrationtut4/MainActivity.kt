package com.example.registrationtut4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.registrationtut4.models.FormData
import com.example.registrationtut4.models.validations.Validations

class MainActivity : AppCompatActivity() {

    lateinit var edtStudentId: EditText
    lateinit var spnYear: Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree: CheckBox
    lateinit var tvYear: TextView
    lateinit var tvSemester:TextView
    private var count = 0;
    lateinit var btnSubmit:Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtStudentId = findViewById(R.id.edtStudentId)
        tvYear = findViewById(R.id.tvYear)
        spnYear = findViewById(R.id.spnYear)
        tvSemester = findViewById(R.id.tvSemester)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgree = findViewById(R.id.cbAgree)
        btnSubmit=findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener(View.OnClickListener {
            submit(btnSubmit)
            if(count==4){ displayAlert("Success","You have successfully registered") }
        })




    }

    fun displayAlert(Title:String,Message:String){
        val builder=AlertDialog.Builder(this)
        builder.setTitle(Title)
        builder.setMessage(Message)
        builder.setPositiveButton("OK"){
            dialog,which->
        }
        val dialog=builder.create()
        dialog.show()
    }
    fun submit(v: View){
        val form=FormData(
            edtStudentId.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgree.isChecked
        )

        val studentIdValidation = form.validateStudentId()
        val spnYearValidation = form.validateYear()
        val spnSemesterValidation = form.validateSemester()
        val cbAgreeValidation = form.validateAgreement()

        when(studentIdValidation){
            is Validations.Valid->{count++}
            is Validations.Invalid->{
                edtStudentId.error=studentIdValidation.errorMessage
            }
            is Validations.Empty->{
                edtStudentId.error=studentIdValidation.errorMessage
            }
        }

        when(spnYearValidation){
            is Validations.Valid->{count++}
            is Validations.Invalid->{
                val tv:TextView = spnYear.selectedView as TextView
                tv.error =""
                tv.text = spnYearValidation.errorMessage
            }
            is Validations.Empty ->{ val tv:TextView = spnYear.selectedView as TextView
                tv.error =""
                tv.text = spnYearValidation.errorMessage }
        }

        when(spnSemesterValidation){
            is Validations.Valid ->{ count ++ }
            is Validations.Invalid ->{
                val tv:TextView = spnSemester.selectedView as TextView
                tv.error =""
                tv.text = spnSemesterValidation.errorMessage }
            is Validations.Empty ->{
                val tv:TextView = spnSemester.selectedView as TextView
                tv.error =""
                tv.text = spnSemesterValidation.errorMessage } }

        when(cbAgreeValidation){
            is Validations.Valid ->{ count ++ }
            is Validations.Invalid ->{
                displayAlert("Error", cbAgreeValidation.errorMessage) }
            is Validations.Empty ->{ }
        }


    }
}