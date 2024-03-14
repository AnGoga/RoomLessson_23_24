package com.angoga.roomlessson_23_24.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.angoga.roomlessson_23_24.R;
import com.angoga.roomlessson_23_24.callbacks.OnPublicationClicked;
import com.angoga.roomlessson_23_24.database.entity.Publication;
import com.angoga.roomlessson_23_24.databinding.PublicationItemBinding;

import java.util.ArrayList;
import java.util.List;


public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {
    private List<Publication> publications = new ArrayList<>();
    private OnPublicationClicked callback;

    public PublicationAdapter(List<Publication> publications, OnPublicationClicked callback) {
        this.publications = publications;
        this.callback = callback;
    }

    @NonNull
    @Override
    public PublicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.publication_item, parent, false);
        return new PublicationViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationViewHolder holder, int position) {
        holder.update(publications.get(position));
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public void addNewPublication(Publication publication) {
        publications.add(publication);
        notifyItemInserted(publications.size() - 1);
    }

    public class PublicationViewHolder extends RecyclerView.ViewHolder {
        private PublicationItemBinding binding;

        public PublicationViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = PublicationItemBinding.bind(itemView);
            binding.buttonLiked.setOnClickListener(v -> {
                Publication publication = publications.get(getAdapterPosition());
                publication.setLiked(!publication.isLiked());
                notifyItemChanged(getAdapterPosition());

                callback.onLikeClicked(publication);
            });

            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Publication publication = publications.get(getAdapterPosition());
                    publications.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    callback.onRemoveClicked(publication);
                    return false;
                }
            });
        }

        public void update(Publication publication) {
            binding.textViewTitle.setText(publication.getTitle());
            binding.textViewContent.setText(publication.getContent());
            binding.buttonLiked.setText(String.valueOf(publication.isLiked()));
        }
    }
}
