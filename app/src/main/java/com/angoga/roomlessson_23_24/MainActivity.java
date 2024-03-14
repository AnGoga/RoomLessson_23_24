package com.angoga.roomlessson_23_24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.angoga.roomlessson_23_24.callbacks.OnPublicationClicked;
import com.angoga.roomlessson_23_24.database.AppDatabase;
import com.angoga.roomlessson_23_24.database.DatabaseManager;
import com.angoga.roomlessson_23_24.database.entity.Publication;
import com.angoga.roomlessson_23_24.view.PublicationAdapter;
import com.angoga.roomlessson_23_24.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PublicationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initDatabase();
        initViews();
    }

    private void initDatabase() {
        database = DatabaseManager.getInstance(this).getDatabase();
    }

    private void initViews() {
        binding.buttonCreate.setOnClickListener(v -> {
            String title = binding.editTextTitle.getText().toString();
            String content = binding.editTextContent.getText().toString();
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Введите содержимое новости", Toast.LENGTH_LONG).show();
                return;
            }
            binding.editTextTitle.setText("");
            binding.editTextContent.setText("");

            Publication publication = new Publication(title, content);
            System.out.println(publication);

            database.getPublicationDao().insert(publication);
            System.out.println(publication);
            adapter.addNewPublication(publication);
        });
        initRecyclerView();
    }

    private void initRecyclerView () {
        List<Publication> publications = database.getPublicationDao().findAll();

        adapter = new PublicationAdapter(publications, new OnPublicationClicked() {
            @Override
            public void onLikeClicked(Publication publication) {
                database.getPublicationDao().update(publication);
            }

            @Override
            public void onRemoveClicked(Publication publication) {
                database.getPublicationDao().delete(publication);
            }
        });
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);
    }
}