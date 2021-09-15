package com.selim.expensetracker.data

import com.selim.expensetracker.models.Transactions

object MockData {

    private val transactionList = arrayListOf(
            Transactions("Shopping", "Buy some grocery", "10:00 AM", "-120"),
            Transactions("Subscription", "Disney+ Annual..", "03:30 PM", "-80"),
            Transactions("Food", "Buy a ramen", "07:30 PM", "-30"),
    )
    private val months = arrayListOf("January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December")


    fun getTransactions(): ArrayList<Transactions> {
        return transactionList
    }
    fun getMonths(): ArrayList<String> {
        return months
    }
}