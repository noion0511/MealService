package com.example.school_lunch.Fragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Adapter.CalendarAdapter
import com.example.school_lunch.BaseCalendar
import com.example.school_lunch.R
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [academicCalendar.newInstance] factory method to
 * create an instance of this fragment.
 */
class AcademicCalendarF : Fragment(), CalendarAdapter.OnMonthChangeListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val Weburl = "http://jungang.jje.hs.kr/jungang-h/0204/schedule/month"

    lateinit var calendarAdapter: CalendarAdapter

    var calMonth: TextView? = null
    var calPre: ImageView? = null
    var calNext: ImageView? = null
    var calRV: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_academic_calendar, container, false)
        initView(view)
        CalendarAsyncTask().execute()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment academicCalendar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AcademicCalendarF().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun initView(view: View) {
        calendarAdapter = CalendarAdapter(onMonthChangeListener = this)

        calRV = view.findViewById(R.id.fg_cal_rv)
        calRV?.layoutManager = GridLayoutManager(view.context, BaseCalendar.DAYS_OF_WEEK)
        calRV?.adapter = calendarAdapter

        calPre = view.findViewById(R.id.fg_cal_pre)
        calPre?.setOnClickListener {
            calendarAdapter.changeToPrevMonth()
        }

        calNext = view.findViewById(R.id.fg_cal_next)
        calNext?.setOnClickListener {
            calendarAdapter.changeToNextMonth()
        }
        calMonth = view.findViewById(R.id.fg_cal_month)
    }


    @SuppressLint("StaticFieldLeak")
    inner class CalendarAsyncTask: AsyncTask<String, String, List<Schedule>>(){
        override fun doInBackground(vararg params: String?): List<Schedule> {
            val doc: Document = Jsoup.connect("$Weburl").get()
            val tables: Elements = doc.select("table.tb_calendar")
            val elts: Elements = tables[0].select("td")
            Log.d("printLTS", elts.toString())

            val validBoxList = elts.filter{
                it.text().trim() != "" && it.select("a, span").size != 0
            }

            Log.d("Crawl", "validBox number: ${validBoxList.size.toString()}")


            val list = validBoxList.mapIndexed { index, elem ->
                val scheduleElemnt = elem.select("a")
                val holiday = elem.select("span")
                val findDayRegex = Regex("\\d+")
                val day = findDayRegex.find(elem.text())?.value?.toInt() ?: -1
//                Log.d("printLTS", "${scheduleElemnt}")
                Log.d("printData", "day=$day, schedule=${scheduleElemnt.text()}, holiday=${holiday.text()}")
                Schedule(day, scheduleElemnt.text(),holiday.text())
            }
            Log.d("Crawl", list.size.toString())
            return list
        }

        override fun onPostExecute(result: List<Schedule>?) {
            if (result != null) {
                val adapter = CalendarAdapter(result, this@AcademicCalendarF)
                calRV?.adapter = adapter
            }
        }
    }

    data class Schedule(val day:Int, val schedule:String, val holiday:String)

    override fun onMonthChanged(calendar: Calendar) {
        val sdf = SimpleDateFormat("yyyy년 MM월", Locale.KOREAN)
        calMonth?.text = sdf.format(calendar.time)
    }
}

