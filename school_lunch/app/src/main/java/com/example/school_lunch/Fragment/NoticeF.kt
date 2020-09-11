package com.example.school_lunch.Fragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school_lunch.Adapter.NoticeAdapter
import com.example.school_lunch.R
import org.jsoup.Jsoup

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NoticeF : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    val web_url = "http://jungang.jje.hs.kr/jungang-h/0201/board/16395"
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
        val view = inflater.inflate(R.layout.fragment_notice, container, false)
        rv = view.findViewById(R.id.notice_rv_list)
        rv?.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        rv?.adapter = NoticeAdapter(ArrayList(), view.context)

        NoticeAsyncTask().execute()
        return view
    }

    @SuppressLint("StaticFieldLeak")
    inner class NoticeAsyncTask: AsyncTask<String, String, List<NoticeItem>>(){
        override fun doInBackground(vararg params: String?): List<NoticeItem> {
            val doc = Jsoup.connect(web_url).get()
            val tables = doc.select("table.wb")
            val elts = tables.select("tr")

            val validBoxList = elts.filter{
                it.text().trim() != "" && it.select("td").size >= 5
            }

            val numberRegex = Regex("\\d+")
            val list = validBoxList.mapIndexed { index, element ->
                val number = element.select("td")[0].text()
                val title = element.select("td.link").text()
                val writer = element.select("td")[2].text()
                val day = element.select("td")[4].text()
                val jsCode = element.select("a").attr("onclick")
                val numberList = numberRegex.findAll(jsCode).toList()
                val urlCode = numberList[1].value
                val realUrl = "http://jungang.jje.hs.kr/jungang-h/0201/board/16395/$urlCode"
                if(number==""){
                    NoticeItem(" [공지] ",title,writer,day,realUrl)
                }
                else{
                    NoticeItem(number,title,writer,day,realUrl)
                }
            }
            return list
        }

        override fun onPostExecute(result: List<NoticeItem>?) {
            if(result != null) {
                val adapter = NoticeAdapter(result,view?.context)
                rv?.adapter = adapter
            }
        }
    }


    data class NoticeItem(val number:String,val title:String, val writer:String, val day:String, val url:String)
}
