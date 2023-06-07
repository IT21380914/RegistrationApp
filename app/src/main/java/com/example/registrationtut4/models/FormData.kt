package com.example.registrationtut4.models

import com.example.registrationtut4.models.validations.Validations

class FormData(private var studentID:String,
               private var year:String,
               private var semester:String,
               private var agree:Boolean, ) {

    fun validateStudentId(): Validations {
        return if (studentID.isEmpty()) {
            Validations.Empty("Student ID is empty")
        } else if (!studentID.startsWith("IT")) {
            Validations.Invalid("Student ID is Invalid")
        } else if (studentID.length < 10) {
            Validations.Invalid("Student ID number should contain 10 values")
        } else if (studentID.length > 10) {
            Validations.Invalid("Student ID number should only contain 10 values")
        } else {
            Validations.Valid
        }
    }

    fun validateYear():Validations{
        return if(year.isEmpty()){
            Validations.Empty("Year is empty")
        }else{
            Validations.Valid
        }
    }

    fun validateSemester():Validations{
        return if(semester.isEmpty()){
            Validations.Empty("Semester is empty")
        }else{
            Validations.Valid
        }
    }

    fun validateAgreement():Validations{
        return if(!agree){
            Validations.Invalid("You must agree to temrs and condtions")
        }else{
            Validations.Valid
        }
    }
}