package com.ldl.module.imkit.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

/**
 * @author LDL
 * @date: 2021/11/5
 * @description:
 */
public class MessageRecyclerView extends RecyclerView {
    public MessageRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public MessageRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MessageRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(getContext()));
        setItemAnimator(null);
        //底部布局弹出,聊天列表上滑
        addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom < oldBottom) {
                post(() -> {
                    if (Objects.requireNonNull(getAdapter()).getItemCount() > 0) {
                        scrollToPosition(getAdapter().getItemCount() - 1);
                    }
                });
            }
        });
    }

    public boolean isLastItemVisibleCompleted() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
        if (linearLayoutManager == null) {
            return false;
        }
        int lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        int childCount = linearLayoutManager.getChildCount();
        int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
        return lastPosition >= firstPosition + childCount - 1;
    }
}
