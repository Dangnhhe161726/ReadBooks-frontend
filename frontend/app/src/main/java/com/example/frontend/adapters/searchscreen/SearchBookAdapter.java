package com.example.frontend.adapters.searchscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frontend.R;
import com.example.frontend.response.BookResponse;

import java.util.ArrayList;
import java.util.List;


public class SearchBookAdapter extends RecyclerView.Adapter<SearchBookAdapter.SearchBookViewHolder> {

    private List<BookResponse> bookResponses = new ArrayList<>();
    private SearchBookItemListener searchBookItemListener;

    public SearchBookAdapter() {
    }

    public List<BookResponse> getBookResponses() {
        return bookResponses;
    }

    public void setBookResponses(List<BookResponse> bookResponses) {
        this.bookResponses = bookResponses;
    }

    public SearchBookItemListener getSearchBookItemListener() {
        return searchBookItemListener;
    }

    public void setSearchBookItemListener(SearchBookItemListener searchBookItemListener) {
        this.searchBookItemListener = searchBookItemListener;
    }

    @NonNull
    @Override
    public SearchBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_book_screen, parent, false);
        return new SearchBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchBookViewHolder holder, int position) {
        BookResponse bookResponse = bookResponses.get(position);
        String url = bookResponse.getThumbnail();
        Glide.with(holder.itemView.getContext())
                .load(url)
                .placeholder(R.drawable.img_loading)
                .error(R.drawable.img_error)
                .into(holder.imgISearch);
        holder.tvNameISearch.setText(bookResponse.getName());
        holder.tvAuthorISearch.setText(bookResponse.getAuthor().getName());
        holder.tvFavoritesISearch.setText(String.format("%d", bookResponse.getFavorites()));
        if(bookResponse.getCategories() == null){
            holder.btnCategoryISearch.setText("Uncategorized yet");
        }else {
            holder.btnCategoryISearch.setText(bookResponse.getCategories().get(0).getName());
        }
    }

    public void addBooks(List<BookResponse> bookResponseList) {
        if(bookResponseList != null){
            bookResponses.addAll(bookResponseList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return bookResponses.size();
    }

    public interface SearchBookItemListener {
        void onItemClick(View view, int position);
    }

    public class SearchBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton imgISearch;
        TextView tvNameISearch, tvAuthorISearch, tvFavoritesISearch;
        Button btnCategoryISearch;

        public SearchBookViewHolder(@NonNull View view) {
            super(view);
            imgISearch = view.findViewById(R.id.imgISearch);
            tvNameISearch = view.findViewById(R.id.tvNameISearch);
            tvAuthorISearch = view.findViewById(R.id.tvAuthorISearch);
            tvFavoritesISearch = view.findViewById(R.id.tvFavoritesISearch);
            btnCategoryISearch = view.findViewById(R.id.btnCategoryISearch);
            btnCategoryISearch.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}