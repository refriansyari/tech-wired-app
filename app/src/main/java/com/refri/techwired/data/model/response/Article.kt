package com.refri.techwired.data.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("content")
    var content: String? = null,
    @SerializedName("source")
    var source: Source? = null,
    @SerializedName("publishedAt")
    var publishedAt: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("urlToImage")
    var image: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Source::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(description)
        parcel.writeString(content)
        parcel.writeParcelable(source, flags)
        parcel.writeString(publishedAt)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}