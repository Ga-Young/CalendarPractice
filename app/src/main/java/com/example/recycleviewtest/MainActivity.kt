package com.example.recycleviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //옆으로 넘기기
        val snapHelper : SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        //어댑터 등록
        recyclerView.adapter = RecyclerviewAdapter(setData())

    }

    //data 생성
    fun setData(): ArrayList<Data> {
        val list = arrayListOf<Data>()

        val instance = Calendar.getInstance()
        val cells : ArrayList<Date> = ArrayList()
        val calendar : Calendar = instance.clone() as Calendar

        //마지막날짜가 몇째주인지 구하기
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        calendar.set(Calendar.DAY_OF_MONTH,lastDay)
        val lastWeek = calendar.get(Calendar.WEEK_OF_MONTH)

        //오늘 날짜를 1일로 변경
        calendar.set(Calendar.DAY_OF_MONTH,1)
        val firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1 //일 : 1 ~ 토 : 7

        //달력에서 저번달을 표시해주기 위해
        calendar.add(Calendar.DAY_OF_MONTH, -firstDay)
        Log.i("첫칸",calendar.add(Calendar.DAY_OF_MONTH, -firstDay).toString())

        //날짜 채우기
        while (cells.size < 7*lastWeek){
            cells.add(calendar.time)
            list.add(Data(Calendar.DAY_OF_MONTH))
            calendar.add(Calendar.DAY_OF_MONTH,1) //저번달부터 +1하면서 add
            val dd :Int = Calendar.DAY_OF_MONTH
            Log.i("더해져라",dd.toString())

        }

        list.forEach {
            Log.e("log", "$it")
        }
        return list
    }

//    val cal = Calendar.getInstance()
//    val year = cal.get(Calendar.YEAR).toString()
//    val month = (cal.get(Calendar.MONTH)+1).toString()
//    val day = cal.getActualMinimum(Calendar.DATE).toString()
}