package com.example.test4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostCommentAdapter extends BaseAdapter {
    private Context context;
    private List<PostComment> commentList=new ArrayList<>();
    private int itemLayout;

    public PostCommentAdapter(Context context, List<PostComment> commentList, int itemLayout) {
        this.context = context;
        this.commentList = commentList;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(itemLayout,null);
        }
        CircleImageView commenterPhoto=convertView.findViewById(R.id.commenter_photo);
        TextView commenterName=convertView.findViewById(R.id.commenter_name);
        TextView comment=convertView.findViewById(R.id.comment);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"img1/"+commentList.get(position).getCommenterId()+".jpg")
                .apply(requestOptions)
                .into(commenterPhoto);
        commenterName.setText(commentList.get(position).getCommenterName());
        comment.setText(commentList.get(position).getCommentContent());
        return convertView;
    }
}
