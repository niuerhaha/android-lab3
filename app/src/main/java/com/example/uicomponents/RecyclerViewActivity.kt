package com.example.uicomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {

    data class Animal(val name: String, val icon: Int)

    private val animals = mutableListOf(
        Animal("Lion", R.drawable.lion),
        Animal("Tiger", R.drawable.tiger),
        Animal("Monkey", R.drawable.monkey),
        Animal("Dog", R.drawable.dog),
        Animal("Cat", R.drawable.cat),
        Animal("Elephant", R.drawable.elephant)
    )

    private lateinit var adapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AnimalAdapter(animals) { animal ->
            Toast.makeText(this, "You clicked: ${animal.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            animals.add(Animal("New Animal ${animals.size + 1}", R.drawable.cat))
            adapter.notifyItemInserted(animals.size - 1)
        }
    }

    class AnimalAdapter(
        private val items: List<Animal>,
        private val onClick: (Animal) -> Unit
    ) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

        class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val icon: ImageView = view.findViewById(R.id.item_icon)
            val name: TextView = view.findViewById(R.id.item_name)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler, parent, false)
            return AnimalViewHolder(view)
        }

        override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
            val animal = items[position]
            holder.icon.setImageResource(animal.icon)
            holder.name.text = animal.name
            holder.itemView.setOnClickListener { onClick(animal) }
        }

        override fun getItemCount(): Int = items.size
    }
}
