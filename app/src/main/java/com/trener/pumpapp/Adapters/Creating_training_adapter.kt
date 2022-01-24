package com.trener.pumpapp.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.trener.pumpapp.Models.Excercise
import com.example.pumpapp.R


class Creating_training_adapter(private val dataSet: ArrayList<Excercise>) :
    RecyclerView.Adapter<Creating_training_adapter.ViewHolder>() {
    var time = 60
    var reps = 3
    val TAG = "adapter_creating"
    var iterator = 0

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val nameExcercise: TextView = v.findViewById<TextView>(R.id.textViewNameOfExcercise)
        val typeExcercise: TextView = v.findViewById<TextView>(R.id.textViewType)
        val reps = v.findViewById<TextView>(R.id.textViewReps)
        val time = v.findViewById<TextView>(R.id.textViewTime)
        val plusReps = v.findViewById<Button>(R.id.buttonAddingReps)
        val minusReps = v.findViewById<Button>(R.id.buttonDeletingReps)
        val plusTime = v.findViewById<Button>(R.id.buttonAddingTime)
        val minusTime = v.findViewById<Button>(R.id.buttonDeletingTime)
        val addExcercise = v.findViewById<Button>(R.id.ButtonAddingExcercise)
        var context: Context = v.context
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.training_adding_adapter, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataFromDatabase: Excercise = dataSet[position]
        holder.nameExcercise.text = dataFromDatabase.name_of_excercise
        holder.typeExcercise.text = dataFromDatabase.type_of_excercise
        holder.setIsRecyclable(true)
        holder.plusReps.setOnClickListener()
        {
            reps += 1
            holder.reps.text = "Reps: $reps"
        }
        holder.minusReps.setOnClickListener()
        {
            reps -= 1
            holder.reps.text = "Reps: $reps"
        }
        holder.plusTime.setOnClickListener()
        {
            time += 30
            holder.time.text = "Time: $time"
        }
        holder.minusTime.setOnClickListener()
        {
            time -= 30
            holder.time.text = "Time: $time"
        }
        holder.addExcercise.setOnClickListener()
        {
            holder.plusTime.isClickable = false
            holder.minusTime.isClickable = false
            holder.plusReps.isClickable = false
            holder.minusReps.isClickable = false
            holder.addExcercise.isClickable = false
            holder.nameExcercise.text="Added"

            //holder.addExcercise.setBackgroundColor(Color.GREEN)
            val intent = Intent("custom-message")
            intent.putExtra("exce", "${dataFromDatabase.id}|$reps|$time")
            time = 60
            reps = 3
            LocalBroadcastManager.getInstance(holder.context).sendBroadcast(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}