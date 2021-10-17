package com.selim.expensetracker.data

import com.selim.expensetracker.models.Account
import com.selim.expensetracker.models.Budget
import com.selim.expensetracker.models.Notification
import com.selim.expensetracker.models.Transactions

object MockData {

    private val transactionList = arrayListOf(
            Transactions("Shopping", "Buy some grocery", "10:00 AM", "-120$"),
            Transactions("Subscription", "Disney+ Annual..", "03:30 PM", "-80$"),
            Transactions("Food", "Buy a ramen", "07:30 PM", "-30$"),
    )
    private val months = arrayListOf("January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December")
    private val budgets= arrayListOf(Budget("Shopping","1000"))
    private val accounts= arrayListOf(Account("Wallet","400$"),
        Account("Chase","1000$"), Account
    ("Citi","6000$"), Account
    ("Paypal","2000$")
    )
    private val notifications= arrayListOf(Notification("Your Shopping budget has exceeds the limit","19:30"),
    Notification("Your Utilities budget has exceeds the limit","19:30")
    )


    fun getTransactions(): ArrayList<Transactions> {
        return transactionList
    }
    fun getMonths(): ArrayList<String> {
        return months
    }
    fun getBudgets():ArrayList<Budget>
    {
        return budgets
    }
    fun getAccounts():ArrayList<Account>
    {
        return accounts
    }
    fun getNotifications():ArrayList<Notification>
    {
        return notifications
    }
}