package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemNormalBinding



class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

open class NormalHolder(
    private val binding: ListItemNormalBinding
) : ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewBinding
        if(viewType == 0) {
            return NormalHolder(ListItemNormalBinding.inflate(inflater, parent, false))
        }
        else {
            return CrimeHolder(ListItemCrimeBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crime = crimes[position]
        when(crimes[position].requiresPolice) {
            true -> (holder as CrimeHolder).bind(crime)
            false-> (holder as NormalHolder).bind(crime)
        }
    }

    override fun getItemViewType(index: Int): Int {
        return if(crimes[index].requiresPolice) { 1 }
               else{ 0 }
    }

    fun onBindViewHolder(holder: CrimeHolder, position: Int) {

    }

    override fun getItemCount() = crimes.size
}
