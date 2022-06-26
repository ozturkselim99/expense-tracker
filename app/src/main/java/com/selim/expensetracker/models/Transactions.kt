package com.selim.expensetracker.models

import java.io.Serializable

data class Transactions(
    var transactionId:String,
    var title: String,
    var description: String,
    var createdAt: String,
    var amount: String,
    var transactionType:String,
    var imageUrl: String,
    var selectedBank: String,
    ):Serializable