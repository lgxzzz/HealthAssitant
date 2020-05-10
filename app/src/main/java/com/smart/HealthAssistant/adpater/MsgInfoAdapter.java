package com.smart.HealthAssistant.adpater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.HealthAssistant.R;
import com.smart.HealthAssistant.WebViewActivity;
import com.smart.HealthAssistant.bean.MsgInfo;

import java.util.ArrayList;
import java.util.List;

public class MsgInfoAdapter extends BaseAdapter {

    Context mContext;
    List<MsgInfo> mMsgInfos = new ArrayList<>();

    public MsgInfoAdapter(Context mContext, List<MsgInfo> mMsgInfos){
        this.mContext = mContext;
        this.mMsgInfos = mMsgInfos;
    }

    @Override
    public int getCount() {
        return mMsgInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return mMsgInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final MsgInfo msgInfo = mMsgInfos.get(i);
        MsgInfoAdapter.ViewHoler holer = null;
        if (view == null){
            holer = new MsgInfoAdapter.ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.msg_item,null);
            holer.mPic = (ImageView) view.findViewById(R.id.msg_pic);
            holer.mTitle = (TextView) view.findViewById(R.id.msg_title);
            holer.mContent = (TextView) view.findViewById(R.id.msg_content);
            view.setTag(holer);
        }else{
            holer = (MsgInfoAdapter.ViewHoler) view.getTag();
        }
        holer.mPic.setBackgroundResource(msgInfo.getmPicId());
        holer.mTitle.setText(msgInfo.getTitle());
        holer.mContent.setText(msgInfo.getContent());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext,WebViewActivity.class);
                intent.putExtra("url",msgInfo.getUrl());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHoler{
        ImageView mPic;
        TextView mTitle;
        TextView mContent;
    }
}
