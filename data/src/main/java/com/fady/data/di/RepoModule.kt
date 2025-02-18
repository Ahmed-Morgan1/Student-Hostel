package com.fady.data.di

import com.fady.data.dataSoure.local.dao.ApartmentDao
import com.fady.data.dataSoure.local.dao.OwnerDao
import com.fady.data.dataSoure.local.dao.StudentDao
import com.fady.data.dataSoure.remote.ApiService
import com.fady.data.networkConnectivity.IConnectivityHandler
import com.fady.data.repo.base.IApartmentRepo
import com.fady.data.repo.base.IOwnerRepo
import com.fady.data.repo.base.IStudentRepo
import com.fady.data.repo.impl.ApartmentRepo
import com.fady.data.repo.impl.OwnerRepo
import com.fady.data.repo.impl.StudentRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideOwnerRepo(
        ownerDao: OwnerDao,
        apiService: ApiService,
        connectivityHandler: IConnectivityHandler
    ): IOwnerRepo = OwnerRepo(
        ownerDao = ownerDao,
        apiService = apiService,
        connectivityHandler = connectivityHandler
    )


    @Provides
    @Singleton
    fun provideApartmentRepo(
        apartmentDao: ApartmentDao,
        apiService: ApiService,
        ownerDao: OwnerDao,
        connectivityHandler: IConnectivityHandler
    ): IApartmentRepo = ApartmentRepo(
        apartmentDao = apartmentDao,
        connectivityHandler = connectivityHandler,
        apiService = apiService,
        ownerDao = ownerDao
    )

    @Provides
    @Singleton
    fun provideStudentRepo(
        studentDao: StudentDao,
        apiService: ApiService,
        connectivityHandler: IConnectivityHandler
    ): IStudentRepo = StudentRepo(
        studentDao = studentDao,
        connectivityHandler = connectivityHandler,
        apiService = apiService
    )

}