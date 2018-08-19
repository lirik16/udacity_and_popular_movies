package com.and.movies.di;

import com.and.movies.domain.thread.ThreadUtil;
import com.and.movies.domain.usecase.scheduler.PriorityUseCaseScheduler;
import com.and.movies.domain.usecase.scheduler.UseCaseScheduler;
import com.and.movies.thread.LongTaskExecutor;
import com.and.movies.thread.MainThreadExecutor;
import com.and.movies.thread.ShortTaskExecutor;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ThreadsModule {
    @Binds
    @Named(ThreadUtil.UI_THREAD)
    abstract Executor uiThreadExecutor(MainThreadExecutor mainThreadExecutor);

    @Binds
    @Named(ThreadUtil.SHORT_TASK_THREAD)
    abstract Executor shortTaskExecutor(ShortTaskExecutor shortTaskExecutor);

    @Binds
    @Named(ThreadUtil.LONG_TASK_THREAD)
    abstract Executor longTaskExecutor(LongTaskExecutor longTaskExecutor);

    @Binds
    abstract UseCaseScheduler useCaseScheduler(PriorityUseCaseScheduler priorityUseCaseScheduler);
}
