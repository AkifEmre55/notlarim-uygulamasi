package com.senin.notlarim.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.senin.notlarim.data.Note
import com.senin.notlarim.data.NoteDatabase
import com.senin.notlarim.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = NoteDatabase.getDatabase(this).noteDao()

        adapter = NoteAdapter(
            onClick = { note ->
                val intent = Intent(this, AddEditNoteActivity::class.java)
                intent.putExtra("note_id", note.id)
                startActivity(intent)
            },
            onLongClick = { note ->
                lifecycleScope.launch { dao.delete(note) }
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            dao.getAllNotes().collect { notes ->
                adapter.submitList(notes)
                binding.tvEmpty.visibility =
                    if (notes.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
            }
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditNoteActivity::class.java))
        }
    }
}
