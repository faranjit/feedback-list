package com.faranjit.feedbacklist.base

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
abstract class BaseUseCase<T, in Params> {

    protected abstract suspend fun buildUseCaseObservable(params: Params? = null): T

    /**
     * Executes the current use case.
     */
    open suspend fun execute(params: Params? = null): T {
        return this.buildUseCaseObservable(params)
    }
}