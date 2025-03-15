package com.fady.data.di

import com.fady.data.repo.base.IApartmentRepo
import com.fady.data.repo.base.IAuthRepository
import com.fady.data.repo.base.IFavouriteApartmentRepo
import com.fady.data.repo.base.IOwnerRepo
import com.fady.data.repo.base.IStudentRepo
import com.fady.data.repo.impl.ApartmentRepo
import com.fady.data.repo.impl.AuthRepository
import com.fady.data.repo.impl.FavouriteApartmentRepo
import com.fady.data.repo.impl.OwnerRepo
import com.fady.data.repo.impl.StudentRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoBinder {
    @Binds
    @Singleton
    fun bindOwnerRepo(ownerRepo: OwnerRepo): IOwnerRepo

    @Binds
    @Singleton
    fun bindApartmentRepo(apartmentRepo: ApartmentRepo): IApartmentRepo

    @Binds
    @Singleton
    fun bindStudentRepo(studentRepo: StudentRepo): IStudentRepo

    @Binds
    @Singleton
    fun bindFavouriteApartmentRepo(favouriteApartmentRepo: FavouriteApartmentRepo): IFavouriteApartmentRepo

    @Binds
    @Singleton
    fun bindAuthRepository(authRepository: AuthRepository): IAuthRepository



}