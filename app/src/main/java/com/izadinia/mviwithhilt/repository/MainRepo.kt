package com.izadinia.mviwithhilt.repository

import com.izadinia.mviwithhilt.model.Blog
import com.izadinia.mviwithhilt.network.IHttpCommands
import com.izadinia.mviwithhilt.network.NetworkMapper
import com.izadinia.mviwithhilt.room.BlogDao
import com.izadinia.mviwithhilt.room.CacheMapper
import com.izadinia.mviwithhilt.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepo(
    private val blogDao: BlogDao,
    private val blogHttp: IHttpCommands,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        //delay(1000)
        val networkBlogs = blogHttp.get()
        val blogModels = networkMapper.mapFromEntityList(networkBlogs)
        for (blogModel in blogModels) {
            blogDao.insert(cacheMapper.mapToEntity(blogModel))
        }
        val cachedInDatabaseBlogs = blogDao.getBlogList()
        try {
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedInDatabaseBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }

    }
}