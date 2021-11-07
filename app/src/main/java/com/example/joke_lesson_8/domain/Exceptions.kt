package com.example.joke_lesson_8.domain

import java.io.IOException

class NoConnectionException: IOException()
class ServiceUnavailableException: IOException()
class SSLHandlerException: IOException()
class NoCachedJokesException: IOException()