package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.notekeeper.databinding.ActivityNoteListBinding;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ActivityNoteListBinding binding;
    private ArrayAdapter mNoteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Intent newNoteIntent = new Intent(this, NoteActivity.class);
        binding.fab.setOnClickListener(v -> {
            startActivity(newNoteIntent);
        });
        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNoteListAdapter.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        final ListView noteList = findViewById(R.id.lv);
        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        mNoteListAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, notes);
        noteList.setAdapter(mNoteListAdapter);
        noteList.setOnItemClickListener((parent, view, position, id) -> {
//            NoteInfo note = (NoteInfo) noteList.getItemAtPosition(position);
            Intent noteIntent = new Intent(NoteListActivity.this, NoteActivity.class);
            noteIntent.putExtra(NoteActivity.NOTE_POS, position);
            startActivity(noteIntent);
        });
    }
}