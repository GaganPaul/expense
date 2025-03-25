package com.example.expense

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    private lateinit var database: ExpenseDatabase
    private lateinit var expenseAdapter: ExpenseAdapter
    private var budgetLimit: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvBudgetLimit = findViewById<TextView>(R.id.tvBudgetLimit)
        val btnSetBudget = findViewById<Button>(R.id.btnSetBudget)
        val rvExpenses = findViewById<RecyclerView>(R.id.rvExpenses)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)

        database = ExpenseDatabase.getDatabase(this)

        rvExpenses.layoutManager = LinearLayoutManager(this)
        loadExpenses()

        btnSetBudget.setOnClickListener {
            val input = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("Set Budget Limit")
                .setView(input)
                .setPositiveButton("Save") { _, _ ->
                    budgetLimit = input.text.toString().toDouble()
                    tvBudgetLimit.text = "Budget Limit: ₹$budgetLimit"
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        btnAddExpense.setOnClickListener {
            val expense = Expense(name = "Lunch", amount = 200.0)
            database.expenseDao().insertExpense(expense)
            loadExpenses()
        }
    }

    private fun loadExpenses() {
        val expenses = database.expenseDao().getAllExpenses()
        expenseAdapter = ExpenseAdapter(expenses) { expense ->
            database.expenseDao().deleteExpense(expense)
            loadExpenses()
        }
        findViewById<RecyclerView>(R.id.rvExpenses).adapter = expenseAdapter
    }
}
