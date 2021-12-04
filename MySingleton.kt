package com.example.dailyhunt


import android.content.Context
import android.content.SharedPreferences

class MySingleteon private constructor(context: Context) {
    private val sharedPreferences:SharedPreferences?
    private var prefsEditor:SharedPreferences.Editor?=null
    fun saveData(key:String?,value:String?){
        prefsEditor=sharedPreferences!!.edit()
        prefsEditor?.putString(key, value)
        prefsEditor?.apply()
    }
    fun getData(key: String?):String?{
        return if (sharedPreferences!=null){
            sharedPreferences.getString(key,"")
        } else ""
    }
    fun clearData(){
        sharedPreferences!!.edit().clear().apply()
    }

    companion object {
        var MySingleteon: MySingleteon? = null
        fun getInstance(context: Context) :MySingleteon?{
            if(MySingleteon==null){
                MySingleteon= MySingleteon(context)

            }
            return MySingleteon

        }
    }
    init{
        sharedPreferences=context.getSharedPreferences(AppConstant.USERNAME,Context.MODE_PRIVATE)



    }
}



