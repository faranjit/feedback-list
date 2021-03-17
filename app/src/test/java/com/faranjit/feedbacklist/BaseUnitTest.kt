package com.faranjit.feedbacklist

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.serialization.json.Json
import org.koin.test.KoinTest

/**
 * Created by Bulent Turkmen on 17.03.2021.
 */
@ExperimentalCoroutinesApi
abstract class BaseUnitTest : KoinTest {

    val dispatcher = TestCoroutineDispatcher()
    val testScope = TestCoroutineScope(dispatcher)
    val json = Json { ignoreUnknownKeys = true }
}