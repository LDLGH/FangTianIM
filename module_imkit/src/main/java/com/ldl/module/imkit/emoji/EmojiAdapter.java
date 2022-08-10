package com.ldl.module.imkit.emoji;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ldl.module.imkit.R;

import java.util.List;

public class EmojiAdapter extends BaseQuickAdapter<EmojiBean, BaseViewHolder> {

    public EmojiAdapter(@Nullable List<EmojiBean> data, int index, int pageSize) {
        super(R.layout.item_emoji, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EmojiBean item) {
        //判断是否为最后一个item
        if (item.getId().equals("0")) {
            helper.setBackgroundResource(R.id.et_emoji, R.mipmap.rc_icon_emoji_delete);
        } else {
            helper.setBackgroundResource(R.id.et_emoji, item.getRes());
//             helper.setText(R.id.et_emoji,item.getUnicodeInt() );
        }
    }

}
