package com.adawoud.thed.data.mapper

/**
 * This interface defines the contract for mapping between different data types
 * */
interface Mapper<From, To> {

    fun map(from: From): To

}