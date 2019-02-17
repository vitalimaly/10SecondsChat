package com.vitaliimalone.a10secondschat.di

import androidx.room.Room
import com.vitaliimalone.a10secondschat.data.repository.ChatRepository
import com.vitaliimalone.a10secondschat.data.repository.local.ChatLocalDataSource
import com.vitaliimalone.a10secondschat.data.repository.local.database.ChatDatabase
import com.vitaliimalone.a10secondschat.domain.interactors.ChatInteractor
import com.vitaliimalone.a10secondschat.domain.interactors.HomeInteractor
import com.vitaliimalone.a10secondschat.presentation.MainActivity
import com.vitaliimalone.a10secondschat.presentation.MainRouter
import com.vitaliimalone.a10secondschat.presentation.chat.ChatViewModel
import com.vitaliimalone.a10secondschat.presentation.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    // https://github.com/InsertKoinIO/koin/issues/49#issuecomment-414443350
    single { (activity: MainActivity) -> MainRouter(activity) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { ChatViewModel(get(), get()) }
}
val domainModule = module {
    single { HomeInteractor(get()) }
    single { ChatInteractor(get()) }
}
val repositoryModule = module {
    single { ChatRepository(get()) }
}
val localDataSourceModule = module {
    single { ChatLocalDataSource(get()) }
    single { get<ChatDatabase>().chatDao() }
    single {
        Room.databaseBuilder(androidContext(), ChatDatabase::class.java, ChatDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
val appModule = listOf(localDataSourceModule, repositoryModule, domainModule, presentationModule)