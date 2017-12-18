package com.lvqingyang.pero.net

import com.lvqingyang.pero.bean.Pero
import org.jsoup.Jsoup
import java.util.*

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2017/12/17
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

private val URL_AUTHER="http://www.shigeku.org/xlib/xd/sgdq/index.htm"

class KeyValue(val key: String, val value: String)

private fun getAuthor(): KeyValue{
    val doc=Jsoup.connect(URL_AUTHER).get()
    val eles=doc.select("td[width=10%] a[href$=.htm]")
    val auther=eles[Random().nextInt(eles.size)]

    return KeyValue(auther.text(), auther.attr("abs:href"))
}

fun getPero(): Pero{
    val kv= getAuthor()
    val doc=Jsoup.connect(kv.value).get()
    val eles=doc.select("hr ~ p")
    val which=Random().nextInt(eles.size/2)

    return Pero(eles[which*2].text(), kv.key, "", eles[which*2+1].text())
}