package com.example.school_lunch.Fragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Adapter.MealAdapter
import com.example.school_lunch.R
import kotlinx.android.synthetic.main.meal_item.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MealServiceF : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    val Weburl = "http://jungang.jje.hs.kr/jungang-h/19258/food"
    var rv : RecyclerView? = null

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
        val view = inflater.inflate(R.layout.fragment_meal_service, container, false)
        rv  = view.findViewById(R.id.meal_rv_list)
        rv?.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        rv?.scrollToPosition(1)

        MealAsyncTask().execute(Weburl)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MealServiceF().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    @SuppressLint("StaticFieldLeak")
    inner class MealAsyncTask: AsyncTask<String, String, List<Item>>(){
        override fun doInBackground(vararg params: String?): List<Item> {
            val doc: Document = Jsoup.connect("$Weburl").get()
            val tables: Elements = doc.select("table.tb_calendar")
            val elts: Elements = tables[0].select("td")

            val validBoxList = elts.filter{
                it.text().trim() != ""
            }


            val list = validBoxList.mapIndexed { index, elem ->
                val mealElemnt = elem.select("div")
                val findDayRegex = Regex("\\d+")
                val day = findDayRegex.find(elem.text())?.value?.toInt() ?: -1
                if(mealElemnt.size == 0) {
                    Item(day, "급식 없는 날!")
                } else {
                    Item(day, mealElemnt.text())
                }
            }

            return list
        }

        override fun onPostExecute(result: List<Item>?) {
            if (result != null) {
                setList(result)
            }
        }
    }

    private fun setList(result: List<Item>) {
        val nowItem = result.find { item ->
            val now = Date()
            item.day == now.date
        } ?: throw Exception("there is no now day item")
        val nowItemIndex = result.indexOf(nowItem)
        val from = if (nowItemIndex - 2 > 0) {
            nowItemIndex - 2
        } else {
            0
        }
        val to = if (nowItemIndex + 2 < result.size) {
            nowItemIndex + 2
        } else {
            result.size
        }
        val resultList = result.subList(from, to)
        Log.d("Toooo",to.toString())
        val adapter = MealAdapter(resultList, view?.context)
        rv?.adapter = adapter
    }

    data class Item(val day:Int, val meal:String)
}
