package com.selim.expensetracker.data

import com.selim.expensetracker.models.Account
import com.selim.expensetracker.models.Budget
import com.selim.expensetracker.models.Notification
import com.selim.expensetracker.models.Transactions

object MockData {

    private val transactionList = arrayListOf(
        Transactions("1","Shopping", "Buy some grocery", "10:00 AM", "-120$","income","1",""),
        Transactions("1","Subscription", "Disney+ Annual..", "03:30 PM", "-80$","income","",""),
        Transactions("1","Food", "Buy a ramen", "07:30 PM", "-30$","income","",""),
    )
    private val months = arrayListOf(
        "All Months","January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"
    )
    private val budgets = arrayListOf(Budget("Shopping", "1000"))
    private val accounts = arrayListOf(
        Account("Wallet", "400$"),
        Account("Chase", "1000$"), Account
            ("Citi", "6000$"), Account
            ("Paypal", "2000$")
    )
    private val notifications = arrayListOf(
        Notification("Your Shopping budget has exceeds the limit", "19:30"),
        Notification("Your Utilities budget has exceeds the limit", "19:30")
    )
    private val accountTypes = arrayListOf("Bank", "Wallet")
    private val banks = arrayListOf(
        "Halkbank",
        "VakıfBank",
        "Ziraat Bankası",
        "Akbank",
        "Anadolubank",
        "Fibabank",
        "Şekerbank",
        "Türk Ekonomi Bankası",
        "Türkiye İş Bankası",
        "Yapı Kredi Bankası",
        "Garanti BBVA",
        "DenizBank",
        "QNB FinansBank"
    )
    private val categories = arrayListOf(
        "Markte ve AVM",
        "Bireysel Emeklilik",
        "Çeşitli gıda",
        "Sağlık-Kozmetik",
        "Elektrik-Elektronik",
        "Araç satış, servis, yedek parça",
        "Sigorta",
        "Akaryakıt",
        "Telokomünikasyon",
        "Mobilya ve Dekorasyon",
        "Online Alışveriş",
        "Eğitim",
        "Yemek",
        "Giyim",
        "Ulaşım",
        "Konaklama",
    )


    fun getTransactions(): ArrayList<Transactions> {
        return transactionList
    }

    fun getMonths(): ArrayList<String> {
        return months
    }

    fun getBudgets(): ArrayList<Budget> {
        return budgets
    }

    fun getAccounts(): ArrayList<Account> {
        return accounts
    }

    fun getNotifications(): ArrayList<Notification> {
        return notifications
    }

    fun getAccountTypes(): ArrayList<String> {
        return accountTypes
    }
    fun getBanks():ArrayList<String>{
        return banks
    }
    fun getCategories():ArrayList<String>{
        return categories
    }
}