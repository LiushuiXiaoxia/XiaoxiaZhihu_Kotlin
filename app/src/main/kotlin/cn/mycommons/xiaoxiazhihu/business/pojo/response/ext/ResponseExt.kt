package cn.mycommons.xiaoxiazhihu.business.pojo.response.ext

import cn.mycommons.xiaoxiazhihu.business.pojo.bean.*
import cn.mycommons.xiaoxiazhihu.business.pojo.response.BaseResponse
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * response_ext <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
class GetAllThemesResponse : BaseResponse() {

    @SerializedName("limit")
    var limit: Int = 0

    @SerializedName("subscribed")
    var subscribed: Array<Any> ? = null

    @SerializedName("others")
    var others: Array<ThemeItem> ? = null

    override fun toString(): String {
        return "GetAllThemesResponse{" +
                "limit=" + limit +
                ", subscribed=" + Arrays.toString(subscribed) +
                ", others=" + Arrays.toString(others) +
                "} " + super.toString()
    }
}

class GetLastThemeResponse : BaseResponse() {

    @SerializedName("date")
    var date: String ? = null

    @SerializedName("stories")
    var stories: Array<LastThemeStory>? = null

    @SerializedName("top_stories")
    var topStories: Array<LastTemeTopStory>? = null

    override fun toString(): String {
        return "GetLastThemeResponse{" +
                "date='" + date + '\'' +
                ", stories=" + Arrays.toString(stories) +
                ", topStories=" + Arrays.toString(topStories) +
                "} " + super.toString()
    }
}

class GetLongCommentsResponse : BaseResponse() {

    @SerializedName("comments")
    var comments: Array<Comment>? = null

    override fun toString(): String {
        return "GetLongCommentsResponse{" +
                "comments=" + Arrays.toString(comments) +
                "} " + super.toString()
    }
}

class GetNewsResponse : BaseResponse() {

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("type")
    var type: Int = 0
    @SerializedName("title")
    var title: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("image_source")
    var imageSource: String ? = null
    @SerializedName("body")
    var body: String? = null
    @SerializedName("share_url")
    var shareUrl: String? = null
    @SerializedName("css")
    var css: Array<String> ? = null
    @SerializedName("js")
    var js: Array<String>? = null
    @SerializedName("ga_prefix")
    var gaPrefix: String? = null

    override fun toString(): String {
        return "GetNewsResponse{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", body='" + body + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", css=" + Arrays.toString(css) +
                ", js=" + Arrays.toString(js) +
                ", gaPrefix='" + gaPrefix + '\'' +
                "} " + super.toString()
    }
}

class GetShortCommentsResponse : BaseResponse() {

    @SerializedName("comments")
    var comments: Array<Comment>? = null

    override fun toString(): String {
        return "GetShortCommentsResponse{" +
                "comments=" + Arrays.toString(comments) +
                "} " + super.toString()
    }
}

class GetStartInfoResponse : BaseResponse() {

    @SerializedName("text")
    var text: String? = null

    @SerializedName("img")
    var img: String? = null

    override fun toString(): String {
        return "StartInfoResponse{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                "} " + super.toString()
    }
}

class GetStoryExtraResponse : BaseResponse() {

    @SerializedName("long_comments")
    var longComments: Int = 0
    @SerializedName("popularity")
    var popularity: Int = 0
    @SerializedName("short_comments")
    var shortComments: Int = 0
    @SerializedName("comments")
    var comments: Int = 0

    override fun toString(): String {
        return "GetStoryExtraResponse{" +
                "longComments=" + longComments +
                ", popularity=" + popularity +
                ", shortComments=" + shortComments +
                ", comments=" + comments +
                "} " + super.toString()
    }
}

class GetThemeResponse : BaseResponse() {

    @SerializedName("stories")
    var stories: Array<LastThemeStory>? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("background")
    var background: String? = null
    @SerializedName("color")
    var color: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("editors")
    var editors: Array<ThemeEditor>? = null
    @SerializedName("image_srouce")
    var imageSrouce: String? = null

    override fun toString(): String {
        return "GetThemeResponse{" +
                "stories=" + Arrays.toString(stories) +
                ", description='" + description + '\'' +
                ", background='" + background + '\'' +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", editors=" + Arrays.toString(editors) +
                ", imageSrouce='" + imageSrouce + '\'' +
                "} " + super.toString()
    }
}