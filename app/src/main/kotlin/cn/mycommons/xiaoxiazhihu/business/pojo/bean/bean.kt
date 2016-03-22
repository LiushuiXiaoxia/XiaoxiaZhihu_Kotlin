package cn.mycommons.xiaoxiazhihu.business.pojo.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * bean <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
data class Comment(
        @SerializedName("id")
        var id: Int,
        @SerializedName("author")
        var author: String,
        @SerializedName("content")
        var content: String,
        @SerializedName("likes")
        var likes: Int,
        @SerializedName("time")
        var time: Long,
        @SerializedName("avatar")
        var avatar: String
) : Serializable

data class LastTemeTopStory(
        @SerializedName("id")
        var id: Int,
        @SerializedName("type")
        var type: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("ga_prefix")
        var gaPrefix: String,
        @SerializedName("image")
        var image: String
) : Serializable

data class LastThemeStory(
        @SerializedName("id")
        var id: Int,
        @SerializedName("type")
        var type: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("ga_prefix")
        var gaPrefix: String,
        @SerializedName("images")
        var images: Array<String>?
) : Serializable

data class ThemeEditor(
        @SerializedName("url")
        var url: String,
        @SerializedName("bio")
        var bio: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("avatar")
        var avatar: String,
        @SerializedName("name")
        var name: String
) : Serializable

data class ThemeItem(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("thumbnail")
        var thumbnail: String,
        @SerializedName("description")
        var description: String,
        @SerializedName("color")
        var color: Int
) : Serializable