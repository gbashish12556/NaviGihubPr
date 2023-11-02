package com.example.navigithubpr.usecases

abstract class BaseCase<out Result,in Params> {

    abstract suspend operator fun invoke(parameters: Params? = null): Result
}