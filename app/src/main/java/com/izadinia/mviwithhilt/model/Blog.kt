package com.izadinia.mviwithhilt.model

/**
 * by the definition of the clean architecture this is a Domain Model
 * class to represent given JSON object at application level.
 * also a way to map domain model to an entity(which is used for transactions between the app and
 * given data sources) and vice versa has been implemented.
 *
 * @see com.izadinia.mviwithhilt.util.EntityMapper
 */
data class Blog(
    val id:Int,
    val title:String,
    val body:String,
    val image:String,
    val category:String,
)