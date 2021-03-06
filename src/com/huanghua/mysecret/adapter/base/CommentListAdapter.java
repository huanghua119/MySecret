package com.huanghua.mysecret.adapter.base;

import java.util.List;

import android.app.DownloadManager.Query;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.huanghua.mysecret.R;
import com.huanghua.mysecret.bean.Comment;
import com.huanghua.mysecret.bean.CommentSupport;
import com.huanghua.mysecret.bean.User;
import com.huanghua.mysecret.manager.UserManager;
import com.huanghua.mysecret.util.ImageLoadOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CommentListAdapter extends BaseListAdapter<Comment> {

    public CommentListAdapter(Context context, List<Comment> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.comment_item_view, null);
        }
        Comment coment = (Comment) getList().get(position);

        User user = coment.getFromUser();
        ImageView mPhoto = (ImageView) view
                .findViewById(R.id.item_comment_photo);
        TextView mName = (TextView) view.findViewById(R.id.item_comment_name);
        TextView mContents = (TextView) view
                .findViewById(R.id.item_comment_contents);

        String avatar = user.getAvatar();
        if (avatar != null && !avatar.equals("")) {
            ImageLoader.getInstance().displayImage(avatar, mPhoto,
                    ImageLoadOptions.getOptions());
        } else {
            mPhoto.setImageResource(R.drawable.user_photo_default);
        }

        mContents.setText(coment.getContents());
        Drawable drawable = mContext.getResources().getDrawable(
                user.isSex() ? R.drawable.man : R.drawable.women);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        mName.setCompoundDrawables(drawable, null, null, null);
        mName.setText(user.getUsername() + mContext.getString(R.string.say));

        TextView csView = (TextView) view
                .findViewById(R.id.item_comment_support);
        setDingTextView(coment, csView);
        setOnInViewClickListener(R.id.item_comment_support,
                new onInternalClickListener() {
                    @Override
                    public void OnClickListener(View parentV, View v,
                            Integer position, Object values) {
                        final TextView vv = (TextView) v;
                        final Comment c = (Comment) values;
                        CommentSupport cs = new CommentSupport();
                        cs.setComment(c);
                        cs.setToUser(c.getFromUser());
                        cs.setFromUser(UserManager.getInstance(mContext)
                                .getCurrentUser());
                        cs.save(mContext, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                showLog("save commentSupport success");
                                vv.setSelected(true);
                                vv.setClickable(false);
                                setDingTextView(c, vv);
                            }

                            @Override
                            public void onFailure(int arg0, String arg1) {
                                showLog("save commentSupport failure");
                            }
                        });
                    }
                });
        return view;
    }

    private void setDingTextView(Comment comment, final TextView csView) {
        BmobQuery<CommentSupport> queryCs = new BmobQuery<CommentSupport>();
        queryCs.addWhereEqualTo("comment", comment);
        queryCs.findObjects(mContext, new FindListener<CommentSupport>() {
            @Override
            public void onSuccess(List<CommentSupport> arg0) {
                int count = arg0.size();
                for (CommentSupport cs : arg0) {
                    if (cs.getFromUser().equals(
                            UserManager.getInstance(mContext).getCurrentUser())) {
                        csView.setSelected(true);
                        csView.setClickable(false);
                        break;
                    }
                }
                if (count == 0) {
                    csView.setText(R.string.add_one);
                } else {
                    csView.setText(arg0.size() + "");
                }
            }

            @Override
            public void onError(int arg0, String arg1) {

            }
        });
    }
}
