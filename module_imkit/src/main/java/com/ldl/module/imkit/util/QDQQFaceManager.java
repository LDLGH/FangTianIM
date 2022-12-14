/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ldl.module.imkit.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.util.SparseIntArray;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.collection.ArrayMap;

import com.blankj.utilcode.util.Utils;
import com.ldl.module.imkit.R;
import com.ldl.module.imkit.bean.QQFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cginechen
 * @date 2016-12-21
 */

public class QDQQFaceManager {
    private static final HashMap<String, Integer> sQQFaceMap = new HashMap<>();
    private static final List<QQFace> mQQFaceList = new ArrayList<>();
    private static final SparseIntArray sEmojisMap = new SparseIntArray(0);
    private static final SparseIntArray sSoftbanksMap = new SparseIntArray(0);
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final ArrayMap<String, String> mQQFaceFileNameList = new ArrayMap<>();//??????QQ????????????????????????,??????????????????????????????????????????
    private static Context context = Utils.getApp();
    private static LruCache<String, Bitmap> drawableCache = new LruCache(1024);

    private static QDQQFaceManager sQDQQFaceManager = new QDQQFaceManager();

    static {
        long start = System.currentTimeMillis();

        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_1_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_2_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_3_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_4_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_5_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_6_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_7_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_8_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_9_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_10_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_11_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_12_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_13_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_14_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_15_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_16_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_17_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_18_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_19_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_20_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_21_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_22_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_23_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_24_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_25_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_26_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_27_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_28_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_29_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_30_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_31_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_32_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_33_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_34_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_35_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_36_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_37_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_38_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_39_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_40_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_41_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_42_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_43_2x));
        mQQFaceList.add(new QQFace("[?????????]", R.drawable.expression_44_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_45_2x));
        mQQFaceList.add(new QQFace("[?????????]", R.drawable.expression_46_2x));
        mQQFaceList.add(new QQFace("[?????????]", R.drawable.expression_47_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_48_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_49_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_50_2x));
        mQQFaceList.add(new QQFace("[?????????]", R.drawable.expression_51_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_52_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_53_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_54_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_55_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_56_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_57_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_58_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_59_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_60_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_61_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_62_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_63_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_64_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_65_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_66_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_67_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_68_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_69_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_70_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_71_2x));
//        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_56_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_73_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_74_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_75_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_76_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_77_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_78_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_79_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_80_2x));
        mQQFaceList.add(new QQFace("[???]", R.drawable.expression_81_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_82_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_83_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_84_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_85_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_86_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_87_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_88_2x));
        mQQFaceList.add(new QQFace("[NO]", R.drawable.expression_89_2x));
        mQQFaceList.add(new QQFace("[OK]", R.drawable.expression_90_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_91_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_92_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_93_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_94_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_95_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_96_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_97_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_98_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_99_2x));
        mQQFaceList.add(new QQFace("[??????]", R.drawable.expression_100_2x));
//        mQQFaceList.add(new QQFace("[??????]", R.drawable.smiley_100));
//        mQQFaceList.add(new QQFace("[??????]", R.drawable.smiley_101));
//        mQQFaceList.add(new QQFace("[??????]", R.drawable.smiley_102));
//        mQQFaceList.add(new QQFace("[?????????]", R.drawable.smiley_103));
//        mQQFaceList.add(new QQFace("[?????????]", R.drawable.smiley_104));

        for (QQFace face : mQQFaceList) {
            sQQFaceMap.put(face.getName(), face.getRes());
            loadAssetBitmap(face.getName(), face.getRes());
        }

        mQQFaceFileNameList.put("[??????]", "expression_1_2x");
        mQQFaceFileNameList.put("[??????]", "expression_2_2x");
        mQQFaceFileNameList.put("[???]", "expression_3_2x");
        mQQFaceFileNameList.put("[??????]", "expression_4_2x");
        mQQFaceFileNameList.put("[??????]", "expression_5_2x");
        mQQFaceFileNameList.put("[??????]", "expression_6_2x");
        mQQFaceFileNameList.put("[??????]", "expression_7_2x");
        mQQFaceFileNameList.put("[??????]", "expression_8_2x");
        mQQFaceFileNameList.put("[???]", "expression_9_2x");
        mQQFaceFileNameList.put("[??????]", "expression_10_2x");
        mQQFaceFileNameList.put("[??????]", "expression_11_2x");
        mQQFaceFileNameList.put("[??????]", "expression_12_2x");
        mQQFaceFileNameList.put("[??????]", "expression_13_2x");
        mQQFaceFileNameList.put("[??????]", "expression_14_2x");
        mQQFaceFileNameList.put("[??????]", "expression_15_2x");
        mQQFaceFileNameList.put("[??????]", "expression_16_2x");
        mQQFaceFileNameList.put("[???]", "expression_17_2x");
        mQQFaceFileNameList.put("[??????]", "expression_18_2x");
        mQQFaceFileNameList.put("[??????]", "expression_19_2x");
        mQQFaceFileNameList.put("[???]", "expression_20_2x");
        mQQFaceFileNameList.put("[??????]", "expression_21_2x");
        mQQFaceFileNameList.put("[??????]", "expression_22_2x");
        mQQFaceFileNameList.put("[??????]", "expression_23_2x");
        mQQFaceFileNameList.put("[??????]", "expression_24_2x");
        mQQFaceFileNameList.put("[??????]", "expression_25_2x");
        mQQFaceFileNameList.put("[???]", "expression_26_2x");
        mQQFaceFileNameList.put("[??????]", "expression_27_2x");
        mQQFaceFileNameList.put("[??????]", "expression_28_2x");
        mQQFaceFileNameList.put("[??????]", "expression_29_2x");
        mQQFaceFileNameList.put("[??????]", "expression_30_2x");
        mQQFaceFileNameList.put("[??????]", "expression_31_2x");
        mQQFaceFileNameList.put("[??????]", "expression_32_2x");
        mQQFaceFileNameList.put("[??????]", "expression_33_2x");
        mQQFaceFileNameList.put("[???]", "expression_34_2x");
        mQQFaceFileNameList.put("[???]", "expression_35_2x");
        mQQFaceFileNameList.put("[??????]", "expression_36_2x");
        mQQFaceFileNameList.put("[???]", "expression_37_2x");
        mQQFaceFileNameList.put("[??????]", "expression_38_2x");
        mQQFaceFileNameList.put("[??????]", "expression_39_2x");
        mQQFaceFileNameList.put("[??????]", "expression_40_2x");
        mQQFaceFileNameList.put("[??????]", "expression_41_2x");
        mQQFaceFileNameList.put("[??????]", "expression_42_2x");
        mQQFaceFileNameList.put("[??????]", "expression_43_2x");
        mQQFaceFileNameList.put("[?????????]", "expression_44_2x");
        mQQFaceFileNameList.put("[??????]", "expression_45_2x");
        mQQFaceFileNameList.put("[?????????]", "expression_46_2x");
        mQQFaceFileNameList.put("[?????????]", "expression_47_2x");
        mQQFaceFileNameList.put("[??????]", "expression_48_2x");
        mQQFaceFileNameList.put("[??????]", "expression_49_2x");
        mQQFaceFileNameList.put("[??????]", "expression_50_2x");
        mQQFaceFileNameList.put("[?????????]", "expression_51_2x");
        mQQFaceFileNameList.put("[??????]", "expression_52_2x");
        mQQFaceFileNameList.put("[??????]", "expression_53_2x");
        mQQFaceFileNameList.put("[???]", "expression_54_2x");
        mQQFaceFileNameList.put("[??????]", "expression_55_2x");
        mQQFaceFileNameList.put("[??????]", "expression_56_2x");
        mQQFaceFileNameList.put("[??????]", "expression_57_2x");
        mQQFaceFileNameList.put("[??????]", "expression_58_2x");
        mQQFaceFileNameList.put("[??????]", "expression_59_2x");
        mQQFaceFileNameList.put("[??????]", "expression_60_2x");
        mQQFaceFileNameList.put("[??????]", "expression_61_2x");
        mQQFaceFileNameList.put("[???]", "expression_62_2x");
        mQQFaceFileNameList.put("[??????]", "expression_63_2x");
        mQQFaceFileNameList.put("[??????]", "expression_64_2x");
        mQQFaceFileNameList.put("[??????]", "expression_65_2x");
        mQQFaceFileNameList.put("[??????]", "expression_66_2x");
        mQQFaceFileNameList.put("[??????]", "expression_67_2x");
        mQQFaceFileNameList.put("[??????]", "expression_68_2x");
        mQQFaceFileNameList.put("[??????]", "expression_69_2x");
        mQQFaceFileNameList.put("[??????]", "expression_70_2x");
        mQQFaceFileNameList.put("[??????]", "expression_71_2x");
//        mQQFaceFileNameList.put("[???]", "??????");
        mQQFaceFileNameList.put("[??????]", "expression_73_2x");
        mQQFaceFileNameList.put("[??????]", "expression_74_2x");
        mQQFaceFileNameList.put("[??????]", "expression_75_2x");
        mQQFaceFileNameList.put("[??????]", "expression_76_2x");
        mQQFaceFileNameList.put("[??????]", "expression_77_2x");
        mQQFaceFileNameList.put("[??????]", "expression_78_2x");
        mQQFaceFileNameList.put("[??????]", "expression_79_2x");
        mQQFaceFileNameList.put("[???]", "expression_80_2x");
        mQQFaceFileNameList.put("[???]", "expression_81_2x");
        mQQFaceFileNameList.put("[??????]", "expression_82_2x");
        mQQFaceFileNameList.put("[??????]", "expression_83_2x");
        mQQFaceFileNameList.put("[??????]", "expression_84_2x");
        mQQFaceFileNameList.put("[??????]", "expression_85_2x");
        mQQFaceFileNameList.put("[??????]", "expression_86_2x");
        mQQFaceFileNameList.put("[??????]", "expression_87_2x");
        mQQFaceFileNameList.put("[??????]", "expression_88_2x");
        mQQFaceFileNameList.put("[NO]", "expression_89_2x");
        mQQFaceFileNameList.put("[OK]", "expression_90_2x");
        mQQFaceFileNameList.put("[??????]", "expression_91_2x");
        mQQFaceFileNameList.put("[??????]", "expression_92_2x");
        mQQFaceFileNameList.put("[??????]", "expression_93_2x");
        mQQFaceFileNameList.put("[??????]", "expression_94_2x");
        mQQFaceFileNameList.put("[??????]", "expression_95_2x");
        mQQFaceFileNameList.put("[??????]", "expression_96_2x");
        mQQFaceFileNameList.put("[??????]", "expression_97_2x");
        mQQFaceFileNameList.put("[??????]", "expression_98_2x");
        mQQFaceFileNameList.put("[??????]", "expression_99_2x");
        mQQFaceFileNameList.put("[??????]", "expression_100_2x");
//        mQQFaceFileNameList.put("[??????]", "smiley_100");
//        mQQFaceFileNameList.put("[??????]", "smiley_101");
//        mQQFaceFileNameList.put("[??????]", "smiley_102");
//        mQQFaceFileNameList.put("[?????????]", "smiley_103");
//        mQQFaceFileNameList.put("[?????????]", "smiley_104");

        Log.d("emoji", String.format("init emoji cost: %dms", (System.currentTimeMillis() - start)));
    }

    public static QDQQFaceManager getInstance() {
        return sQDQQFaceManager;
    }

    public List<QQFace> getQQFaceList() {
        return mQQFaceList;
    }

    public static void handlerEmojiText(TextView comment, String content, boolean typing) {
        SpannableStringBuilder sb = new SpannableStringBuilder(content);
        String regex = "\\[(\\S+?)\\]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        boolean imageFound = false;
        while (m.find()) {
            String emojiName = m.group();
            Bitmap bitmap = drawableCache.get(emojiName);
            if (bitmap != null) {
                imageFound = true;
                sb.setSpan(new ImageSpan(context, bitmap),
                        m.start(), m.end(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        // ????????????????????????????????????????????????????????????????????????????????????
        if (!imageFound && typing) {
            return;
        }
        int selection = comment.getSelectionStart();
        comment.setText(sb);
        if (comment instanceof EditText) {
            ((EditText) comment).setSelection(selection);
        }
    }

    private static void loadAssetBitmap(String filter, @DrawableRes int res) {
        try {
            Resources resources = context.getResources();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDensity = DisplayMetrics.DENSITY_XXHIGH;
            options.inScreenDensity = resources.getDisplayMetrics().densityDpi;
            options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
            Bitmap bitmap = BitmapFactory.decodeResource(resources, res, options);
            if (bitmap != null) {
                drawableCache.put(filter, bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
