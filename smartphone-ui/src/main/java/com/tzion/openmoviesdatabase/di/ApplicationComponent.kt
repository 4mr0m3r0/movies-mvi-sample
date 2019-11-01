package com.tzion.openmoviesdatabase.di

import android.app.Application
import com.tzion.openmoviesdatabase.OpenMoviesDatabaseApp
import com.tzion.openmoviesdatabase.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    CacheModule::class,
    DataModule::class,
    DomainModule::class,
    PresentationModule::class,
    RemoteModule::class,
    UiModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: OpenMoviesDatabaseApp)

}