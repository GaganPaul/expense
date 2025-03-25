package com.example.expense

import androidx.room.*

@Dao
interface ExpenseDao {
    @Insert
    fun insertExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): List<Expense>
}