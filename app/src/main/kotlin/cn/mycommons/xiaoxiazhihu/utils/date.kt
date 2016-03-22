package cn.mycommons.xiaoxiazhihu.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * date <br/>
 * Created by xiaqiulei on 2016-03-23.
 */

fun Date.format(format: String): String = SimpleDateFormat(format).format(this)