package io.github.wellingtoncosta.customviews.di

import dagger.Binds
import dagger.Module
import io.github.wellingtoncosta.customviews.api.RepositoryService
import io.github.wellingtoncosta.customviews.api.UserService
import io.github.wellingtoncosta.customviews.api.impl.RepositoryServiceFuelImpl
import io.github.wellingtoncosta.customviews.api.impl.UserServiceFuelImpl

@Module interface ServiceModule {

    @Binds fun userRepository(impl: UserServiceFuelImpl): UserService

    @Binds fun repositoryService(impl: RepositoryServiceFuelImpl): RepositoryService

}
