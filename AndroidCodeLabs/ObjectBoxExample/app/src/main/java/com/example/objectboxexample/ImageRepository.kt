package com.example.objectboxexample

import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import io.objectbox.query.QueryBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ImageRepository {
    suspend fun getLatest10ImagesInCity(city: String, country: String): Flow<List<Image>>
    
    suspend fun getLatest10ImagesInCountry(country: String): Flow<List<Image>>

    suspend fun getLatest10ImagesByOwner(user: String): Flow<List<Image>>
}

class ObjectBoxImageRepository(
    val boxStore: BoxStore
) : ImageRepository {

    private val imageBox: Box<Image> = boxStore.boxFor()

    override suspend fun getLatest10ImagesInCity(city: String, country: String): Flow<List<Image>> {
        return flow {
            val query = imageBox.query().run {
                Image_.location.equal(
                    "${city},${country}",
                    QueryBuilder.StringOrder.CASE_INSENSITIVE
                )
                orderDesc(Image_.timestamp)
            }
            val imagesInCityList = query.build().find(0, 10)
            emit(imagesInCityList)
        }
    }

    override suspend fun getLatest10ImagesInCountry(country: String): Flow<List<Image>> {
        return flow {
            val query = imageBox.query().run {
                contains(
                    Image_.location,
                    ",$country",
                    QueryBuilder.StringOrder.CASE_INSENSITIVE
                )
                orderDesc(Image_.timestamp)
            }
            val imagesInCountryList = query.build().find(0, 10)
            emit(imagesInCountryList)
        }
    }

    override suspend fun getLatest10ImagesByOwner(owner: String): Flow<List<Image>> {
        return flow {
            val query = imageBox.query().run {
                Image_.owner.equal(owner)
                orderDesc(Image_.timestamp)
            }
            val imagesByUserList = query.build().find(0, 10)
            emit(imagesByUserList)
        }
    }
}
