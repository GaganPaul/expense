package com.example.expense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(private val expenses: List<Expense>, private val onDelete: (Expense) -> Unit) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvExpenseName: TextView = itemView.findViewById(R.id.tvExpenseName)
        val tvExpenseAmount: TextView = itemView.findViewById(R.id.tvExpenseAmount)
        val btnDeleteExpense: Button = itemView.findViewById(R.id.btnDeleteExpense)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.tvExpenseName.text = expense.name
        holder.tvExpenseAmount.text = "₹${expense.amount}"

        holder.btnDeleteExpense.setOnClickListener {
            onDelete(expense)
        }
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}
