package com.senin.notlarim.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.senin.notlarim.data.Note
import com.senin.notlarim.data.NoteDatabase
import com.senin.notlarim.databinding.ActivityAddEditNoteBinding
import kotlinx.coroutines.launch

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding
    private var existingNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = NoteDatabase.getDatabase(this).noteDao()
        val noteId = intent.getIntExtra("note_id", -1)

        if (noteId != -1) {
            lifecycleScope.launch {
                existingNote = dao.getNoteById(noteId)
                existingNote?.let {
                    binding.etTitle.setText(it.title)
                    binding.etContent.setText(it.content)
                }
            }
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isBlank() && content.isBlank()) {
                finish()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val current = existingNote
                if (current != null) {
                    dao.update(current.copy(title = title, content = content, updatedAt = System.currentTimeMillis()))
                } else {
                    dao.insert(Note(title = title, content = content))
                }
                finish()
            }
        }
    }
}
