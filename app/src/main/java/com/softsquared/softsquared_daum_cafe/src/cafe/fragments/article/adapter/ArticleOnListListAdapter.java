package com.softsquared.softsquared_daum_cafe.src.cafe.fragments.article.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.articledetail.ArticleDetailActivity;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.ArticleOnList;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;

import java.util.ArrayList;

import static com.softsquared.softsquared_daum_cafe.src.BaseActivity.imageStorageRef;

public class ArticleOnListListAdapter extends RecyclerView.Adapter<ArticleOnListListAdapter.ArticleViewHoler> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private ArrayList<CafeResponse.Result> articlesOnList;

    public ArticleOnListListAdapter(ArrayList<CafeResponse.Result> articlesOnList, Context context) {
        this.articlesOnList = articlesOnList;
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ArticleViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_article_cafe, parent, false);

        ArticleViewHoler vh = new ArticleViewHoler(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHoler holder, int position) {
        CafeResponse.Result article = articlesOnList.get(position);
        if (article != null) {
            holder.tvTitle.setText(article.getTitle());
            holder.tvCreateDate.setText(article.getCreatedAt());
            holder.tvAuthor.setText(article.getUserId());
            if (article.getImgUri() != null && !article.getImgUri().equals("")) {
                Glide.with(mContext).load(imageStorageRef.child(article.getImgUri())).into(holder.ivImg);
                holder.ivImg.setVisibility(View.VISIBLE);
            } else {
                holder.ivImg.setVisibility(View.GONE);
            }
            //holder.tvViewCount.setText("조회 " + article.getViewCount());
            /*if (article.getCommentCount() == 0) {
                holder.tvCommentCount.setVisibility(View.INVISIBLE);
            } else {
                holder.tvCommentCount.setText(String.valueOf(article.getCommentCount()));
            }*/
            //holder.tvBoard.setText(article.getBoard());
        }
    }

    @Override
    public int getItemCount() {
        return articlesOnList.size();
    }

    public class ArticleViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvTitle;
        private TextView tvCreateDate;
        private TextView tvAuthor;
        private TextView tvViewCount;
        private TextView tvCommentCount;
        private TextView tvBoard;
        private ImageView ivImg;

        public ArticleViewHoler(@NonNull View itemView) {
            super(itemView);

            /* findViewByID */
            tvTitle = itemView.findViewById(R.id.tv_article_title_articlelist);
            tvCreateDate = itemView.findViewById(R.id.tv_article_date_articlelist);
            tvAuthor = itemView.findViewById(R.id.tv_article_author_articlelist);
            tvViewCount = itemView.findViewById(R.id.tv_article_countview_articlelist);
            tvCommentCount = itemView.findViewById(R.id.tv_article_countcomment_articlelist);
            tvBoard = itemView.findViewById(R.id.tv_article_board_articlelist);
            ivImg = itemView.findViewById(R.id.iv_img_article_cafe);

            /* Set On Click Listener */
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == itemView) {
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                mContext.startActivity(intent);
            }
        }
    }
}
