package models

import helpers.Model

data class User(val username: String, var password: String): Model()
