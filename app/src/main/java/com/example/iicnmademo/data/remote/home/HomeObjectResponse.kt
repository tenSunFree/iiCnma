package com.example.iicnmademo.data.remote.home

import com.google.gson.annotations.SerializedName
import com.example.iicnmademo.data.local.record.RecordEntity

data class HomeObjectResponse(
    @SerializedName("info")
    val infoResponse: InfoResponse,
    @SerializedName("records")
    val recordResponses: List<RecordResponse>
) {
    data class InfoResponse(
        @SerializedName("next")
        val next: String,
        @SerializedName("page")
        val page: Int,
        @SerializedName("pages")
        val pages: Int,
        @SerializedName("totalrecords")
        val totalrecords: Int,
        @SerializedName("totalrecordsperquery")
        val totalrecordsperquery: Int,
    )

    data class RecordResponse(
        @SerializedName("accessionmethod")
        val accessionmethod: String,
        @SerializedName("accessionyear")
        val accessionyear: Int,
        @SerializedName("accesslevel")
        val accesslevel: Int,
        @SerializedName("century")
        val century: String,
        @SerializedName("classification")
        val classification: String,
        @SerializedName("classificationid")
        val classificationid: Int,
        @SerializedName("colorcount")
        val colorcount: Int,
        @SerializedName("colors")
        val colors: List<Color>,
        @SerializedName("commentary")
        val commentary: Any,
        @SerializedName("contact")
        val contact: String,
        @SerializedName("contextualtextcount")
        val contextualtextcount: Int,
        @SerializedName("copyright")
        val copyright: Any,
        @SerializedName("creditline")
        val creditline: String,
        @SerializedName("culture")
        val culture: String,
        @SerializedName("datebegin")
        val datebegin: Int,
        @SerializedName("dated")
        val dated: String,
        @SerializedName("dateend")
        val dateend: Int,
        @SerializedName("dateoffirstpageview")
        val dateoffirstpageview: String,
        @SerializedName("dateoflastpageview")
        val dateoflastpageview: String,
        @SerializedName("department")
        val department: String,
        @SerializedName("description")
        val description: Any,
        @SerializedName("dimensions")
        val dimensions: String,
        @SerializedName("division")
        val division: String,
        @SerializedName("edition")
        val edition: Any,
        @SerializedName("exhibitioncount")
        val exhibitioncount: Int,
        @SerializedName("groupcount")
        val groupcount: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("imagecount")
        val imagecount: Int,
        @SerializedName("imagepermissionlevel")
        val imagepermissionlevel: Int,
        @SerializedName("images")
        val images: List<Image>,
        @SerializedName("labeltext")
        val labeltext: Any,
        @SerializedName("lastupdate")
        val lastupdate: String,
        @SerializedName("lendingpermissionlevel")
        val lendingpermissionlevel: Int,
        @SerializedName("markscount")
        val markscount: Int,
        @SerializedName("mediacount")
        val mediacount: Int,
        @SerializedName("medium")
        val medium: Any,
        @SerializedName("objectid")
        val objectid: Int,
        @SerializedName("objectnumber")
        val objectnumber: String,
        @SerializedName("people")
        val people: List<People>,
        @SerializedName("peoplecount")
        val peoplecount: Int,
        @SerializedName("period")
        val period: Any,
        @SerializedName("periodid")
        val periodid: Any,
        @SerializedName("primaryimageurl")
        val primaryimageurl: String?,
        @SerializedName("provenance")
        val provenance: Any,
        @SerializedName("publicationcount")
        val publicationcount: Int,
        @SerializedName("rank")
        val rank: Int,
        @SerializedName("relatedcount")
        val relatedcount: Int,
        @SerializedName("seeAlso")
        val seeAlso: List<SeeAlso>,
        @SerializedName("signed")
        val signed: Any,
        @SerializedName("standardreferencenumber")
        val standardreferencenumber: Any,
        @SerializedName("state")
        val state: Any,
        @SerializedName("style")
        val style: Any,
        @SerializedName("technique")
        val technique: String,
        @SerializedName("techniqueid")
        val techniqueid: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("titlescount")
        val titlescount: Int,
        @SerializedName("totalpageviews")
        val totalpageviews: Int,
        @SerializedName("totaluniquepageviews")
        val totaluniquepageviews: Int,
        @SerializedName("url")
        val url: String,
        @SerializedName("verificationlevel")
        val verificationlevel: Int,
        @SerializedName("verificationleveldescription")
        val verificationleveldescription: String,
        @SerializedName("worktypes")
        val worktypes: List<Worktype>,
    ) {
        data class Color(
            @SerializedName("color")
            val color: String,
            @SerializedName("css3")
            val css3: String,
            @SerializedName("hue")
            val hue: String,
            @SerializedName("percent")
            val percent: Double,
            @SerializedName("spectrum")
            val spectrum: String,
        )

        data class Image(
            @SerializedName("alttext")
            val alttext: Any,
            @SerializedName("baseimageurl")
            val baseimageurl: String,
            @SerializedName("copyright")
            val copyright: Any,
            @SerializedName("date")
            val date: String,
            @SerializedName("description")
            val description: Any,
            @SerializedName("displayorder")
            val displayorder: Int,
            @SerializedName("format")
            val format: String,
            @SerializedName("height")
            val height: Int,
            @SerializedName("idsid")
            val idsid: Int,
            @SerializedName("iiifbaseuri")
            val iiifbaseuri: String,
            @SerializedName("imageid")
            val imageid: Int,
            @SerializedName("publiccaption")
            val publiccaption: Any,
            @SerializedName("renditionnumber")
            val renditionnumber: String,
            @SerializedName("technique")
            val technique: Any,
            @SerializedName("width")
            val width: Int,
        )

        data class People(
            @SerializedName("alphasort")
            val alphasort: String,
            @SerializedName("birthplace")
            val birthplace: String,
            @SerializedName("culture")
            val culture: String,
            @SerializedName("deathplace")
            val deathplace: String,
            @SerializedName("displaydate")
            val displaydate: String,
            @SerializedName("displayname")
            val displayname: String,
            @SerializedName("displayorder")
            val displayorder: Int,
            @SerializedName("gender")
            val gender: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("personid")
            val personid: Int,
            @SerializedName("prefix")
            val prefix: Any,
            @SerializedName("role")
            val role: String,
        )

        data class SeeAlso(
            @SerializedName("format")
            val format: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("profile")
            val profile: String,
            @SerializedName("type")
            val type: String,
        )

        data class Worktype(
            @SerializedName("worktype")
            val worktype: String,
            @SerializedName("worktypeid")
            val worktypeid: String,
        )
    }

    fun toRecordEntityList() = recordResponses.map {
        RecordEntity(
            id = it.id.toLong(),
            title = it.title,
            accessionyear = it.accessionyear,
            primaryimageurl = it.primaryimageurl ?: "",
        )
    }
}