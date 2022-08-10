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
    private static final ArrayMap<String, String> mQQFaceFileNameList = new ArrayMap<>();//存储QQ表情对应的文件名,方便混淆后可以获取到原文件名
    private static Context context = Utils.getApp();
    private static LruCache<String, Bitmap> drawableCache = new LruCache(1024);

    private static QDQQFaceManager sQDQQFaceManager = new QDQQFaceManager();

    static {
        long start = System.currentTimeMillis();

        mQQFaceList.add(new QQFace("[微笑]", R.drawable.expression_1_2x));
        mQQFaceList.add(new QQFace("[撇嘴]", R.drawable.expression_2_2x));
        mQQFaceList.add(new QQFace("[色]", R.drawable.expression_3_2x));
        mQQFaceList.add(new QQFace("[发呆]", R.drawable.expression_4_2x));
        mQQFaceList.add(new QQFace("[得意]", R.drawable.expression_5_2x));
        mQQFaceList.add(new QQFace("[流泪]", R.drawable.expression_6_2x));
        mQQFaceList.add(new QQFace("[害羞]", R.drawable.expression_7_2x));
        mQQFaceList.add(new QQFace("[闭嘴]", R.drawable.expression_8_2x));
        mQQFaceList.add(new QQFace("[睡]", R.drawable.expression_9_2x));
        mQQFaceList.add(new QQFace("[大哭]", R.drawable.expression_10_2x));
        mQQFaceList.add(new QQFace("[尴尬]", R.drawable.expression_11_2x));
        mQQFaceList.add(new QQFace("[发怒]", R.drawable.expression_12_2x));
        mQQFaceList.add(new QQFace("[调皮]", R.drawable.expression_13_2x));
        mQQFaceList.add(new QQFace("[呲牙]", R.drawable.expression_14_2x));
        mQQFaceList.add(new QQFace("[惊讶]", R.drawable.expression_15_2x));
        mQQFaceList.add(new QQFace("[难过]", R.drawable.expression_16_2x));
        mQQFaceList.add(new QQFace("[酷]", R.drawable.expression_17_2x));
        mQQFaceList.add(new QQFace("[冷汗]", R.drawable.expression_18_2x));
        mQQFaceList.add(new QQFace("[抓狂]", R.drawable.expression_19_2x));
        mQQFaceList.add(new QQFace("[吐]", R.drawable.expression_20_2x));
        mQQFaceList.add(new QQFace("[偷笑]", R.drawable.expression_21_2x));
        mQQFaceList.add(new QQFace("[愉快]", R.drawable.expression_22_2x));
        mQQFaceList.add(new QQFace("[白眼]", R.drawable.expression_23_2x));
        mQQFaceList.add(new QQFace("[傲慢]", R.drawable.expression_24_2x));
        mQQFaceList.add(new QQFace("[饥饿]", R.drawable.expression_25_2x));
        mQQFaceList.add(new QQFace("[困]", R.drawable.expression_26_2x));
        mQQFaceList.add(new QQFace("[惊恐]", R.drawable.expression_27_2x));
        mQQFaceList.add(new QQFace("[流汗]", R.drawable.expression_28_2x));
        mQQFaceList.add(new QQFace("[憨笑]", R.drawable.expression_29_2x));
        mQQFaceList.add(new QQFace("[悠闲]", R.drawable.expression_30_2x));
        mQQFaceList.add(new QQFace("[奋斗]", R.drawable.expression_31_2x));
        mQQFaceList.add(new QQFace("[咒骂]", R.drawable.expression_32_2x));
        mQQFaceList.add(new QQFace("[疑问]", R.drawable.expression_33_2x));
        mQQFaceList.add(new QQFace("[嘘]", R.drawable.expression_34_2x));
        mQQFaceList.add(new QQFace("[晕]", R.drawable.expression_35_2x));
        mQQFaceList.add(new QQFace("[疯了]", R.drawable.expression_36_2x));
        mQQFaceList.add(new QQFace("[衰]", R.drawable.expression_37_2x));
        mQQFaceList.add(new QQFace("[骷髅]", R.drawable.expression_38_2x));
        mQQFaceList.add(new QQFace("[敲打]", R.drawable.expression_39_2x));
        mQQFaceList.add(new QQFace("[再见]", R.drawable.expression_40_2x));
        mQQFaceList.add(new QQFace("[擦汗]", R.drawable.expression_41_2x));
        mQQFaceList.add(new QQFace("[抠鼻]", R.drawable.expression_42_2x));
        mQQFaceList.add(new QQFace("[鼓掌]", R.drawable.expression_43_2x));
        mQQFaceList.add(new QQFace("[糗大了]", R.drawable.expression_44_2x));
        mQQFaceList.add(new QQFace("[坏笑]", R.drawable.expression_45_2x));
        mQQFaceList.add(new QQFace("[左哼哼]", R.drawable.expression_46_2x));
        mQQFaceList.add(new QQFace("[右哼哼]", R.drawable.expression_47_2x));
        mQQFaceList.add(new QQFace("[哈欠]", R.drawable.expression_48_2x));
        mQQFaceList.add(new QQFace("[鄙视]", R.drawable.expression_49_2x));
        mQQFaceList.add(new QQFace("[委屈]", R.drawable.expression_50_2x));
        mQQFaceList.add(new QQFace("[快哭了]", R.drawable.expression_51_2x));
        mQQFaceList.add(new QQFace("[阴险]", R.drawable.expression_52_2x));
        mQQFaceList.add(new QQFace("[亲亲]", R.drawable.expression_53_2x));
        mQQFaceList.add(new QQFace("[吓]", R.drawable.expression_54_2x));
        mQQFaceList.add(new QQFace("[可怜]", R.drawable.expression_55_2x));
        mQQFaceList.add(new QQFace("[菜刀]", R.drawable.expression_56_2x));
        mQQFaceList.add(new QQFace("[西瓜]", R.drawable.expression_57_2x));
        mQQFaceList.add(new QQFace("[啤酒]", R.drawable.expression_58_2x));
        mQQFaceList.add(new QQFace("[篮球]", R.drawable.expression_59_2x));
        mQQFaceList.add(new QQFace("[乒乓]", R.drawable.expression_60_2x));
        mQQFaceList.add(new QQFace("[咖啡]", R.drawable.expression_61_2x));
        mQQFaceList.add(new QQFace("[饭]", R.drawable.expression_62_2x));
        mQQFaceList.add(new QQFace("[猪头]", R.drawable.expression_63_2x));
        mQQFaceList.add(new QQFace("[玫瑰]", R.drawable.expression_64_2x));
        mQQFaceList.add(new QQFace("[凋谢]", R.drawable.expression_65_2x));
        mQQFaceList.add(new QQFace("[嘴唇]", R.drawable.expression_66_2x));
        mQQFaceList.add(new QQFace("[爱心]", R.drawable.expression_67_2x));
        mQQFaceList.add(new QQFace("[心碎]", R.drawable.expression_68_2x));
        mQQFaceList.add(new QQFace("[蛋糕]", R.drawable.expression_69_2x));
        mQQFaceList.add(new QQFace("[闪电]", R.drawable.expression_70_2x));
        mQQFaceList.add(new QQFace("[炸弹]", R.drawable.expression_71_2x));
//        mQQFaceList.add(new QQFace("[菜刀]", R.drawable.expression_56_2x));
        mQQFaceList.add(new QQFace("[足球]", R.drawable.expression_73_2x));
        mQQFaceList.add(new QQFace("[瓢虫]", R.drawable.expression_74_2x));
        mQQFaceList.add(new QQFace("[便便]", R.drawable.expression_75_2x));
        mQQFaceList.add(new QQFace("[月亮]", R.drawable.expression_76_2x));
        mQQFaceList.add(new QQFace("[太阳]", R.drawable.expression_77_2x));
        mQQFaceList.add(new QQFace("[礼物]", R.drawable.expression_78_2x));
        mQQFaceList.add(new QQFace("[拥抱]", R.drawable.expression_79_2x));
        mQQFaceList.add(new QQFace("[强]", R.drawable.expression_80_2x));
        mQQFaceList.add(new QQFace("[弱]", R.drawable.expression_81_2x));
        mQQFaceList.add(new QQFace("[握手]", R.drawable.expression_82_2x));
        mQQFaceList.add(new QQFace("[胜利]", R.drawable.expression_83_2x));
        mQQFaceList.add(new QQFace("[抱拳]", R.drawable.expression_84_2x));
        mQQFaceList.add(new QQFace("[勾引]", R.drawable.expression_85_2x));
        mQQFaceList.add(new QQFace("[拳头]", R.drawable.expression_86_2x));
        mQQFaceList.add(new QQFace("[差劲]", R.drawable.expression_87_2x));
        mQQFaceList.add(new QQFace("[爱你]", R.drawable.expression_88_2x));
        mQQFaceList.add(new QQFace("[NO]", R.drawable.expression_89_2x));
        mQQFaceList.add(new QQFace("[OK]", R.drawable.expression_90_2x));
        mQQFaceList.add(new QQFace("[爱情]", R.drawable.expression_91_2x));
        mQQFaceList.add(new QQFace("[飞吻]", R.drawable.expression_92_2x));
        mQQFaceList.add(new QQFace("[跳跳]", R.drawable.expression_93_2x));
        mQQFaceList.add(new QQFace("[发抖]", R.drawable.expression_94_2x));
        mQQFaceList.add(new QQFace("[怄火]", R.drawable.expression_95_2x));
        mQQFaceList.add(new QQFace("[转圈]", R.drawable.expression_96_2x));
        mQQFaceList.add(new QQFace("[磕头]", R.drawable.expression_97_2x));
        mQQFaceList.add(new QQFace("[回头]", R.drawable.expression_98_2x));
        mQQFaceList.add(new QQFace("[跳绳]", R.drawable.expression_99_2x));
        mQQFaceList.add(new QQFace("[投降]", R.drawable.expression_100_2x));
//        mQQFaceList.add(new QQFace("[激动]", R.drawable.smiley_100));
//        mQQFaceList.add(new QQFace("[街舞]", R.drawable.smiley_101));
//        mQQFaceList.add(new QQFace("[献吻]", R.drawable.smiley_102));
//        mQQFaceList.add(new QQFace("[左太极]", R.drawable.smiley_103));
//        mQQFaceList.add(new QQFace("[右太极]", R.drawable.smiley_104));

        for (QQFace face : mQQFaceList) {
            sQQFaceMap.put(face.getName(), face.getRes());
            loadAssetBitmap(face.getName(), face.getRes());
        }

        mQQFaceFileNameList.put("[微笑]", "expression_1_2x");
        mQQFaceFileNameList.put("[撇嘴]", "expression_2_2x");
        mQQFaceFileNameList.put("[色]", "expression_3_2x");
        mQQFaceFileNameList.put("[发呆]", "expression_4_2x");
        mQQFaceFileNameList.put("[得意]", "expression_5_2x");
        mQQFaceFileNameList.put("[流泪]", "expression_6_2x");
        mQQFaceFileNameList.put("[害羞]", "expression_7_2x");
        mQQFaceFileNameList.put("[闭嘴]", "expression_8_2x");
        mQQFaceFileNameList.put("[睡]", "expression_9_2x");
        mQQFaceFileNameList.put("[大哭]", "expression_10_2x");
        mQQFaceFileNameList.put("[尴尬]", "expression_11_2x");
        mQQFaceFileNameList.put("[发怒]", "expression_12_2x");
        mQQFaceFileNameList.put("[调皮]", "expression_13_2x");
        mQQFaceFileNameList.put("[呲牙]", "expression_14_2x");
        mQQFaceFileNameList.put("[惊讶]", "expression_15_2x");
        mQQFaceFileNameList.put("[难过]", "expression_16_2x");
        mQQFaceFileNameList.put("[酷]", "expression_17_2x");
        mQQFaceFileNameList.put("[冷汗]", "expression_18_2x");
        mQQFaceFileNameList.put("[抓狂]", "expression_19_2x");
        mQQFaceFileNameList.put("[吐]", "expression_20_2x");
        mQQFaceFileNameList.put("[偷笑]", "expression_21_2x");
        mQQFaceFileNameList.put("[愉快]", "expression_22_2x");
        mQQFaceFileNameList.put("[白眼]", "expression_23_2x");
        mQQFaceFileNameList.put("[傲慢]", "expression_24_2x");
        mQQFaceFileNameList.put("[饥饿]", "expression_25_2x");
        mQQFaceFileNameList.put("[困]", "expression_26_2x");
        mQQFaceFileNameList.put("[惊恐]", "expression_27_2x");
        mQQFaceFileNameList.put("[流汗]", "expression_28_2x");
        mQQFaceFileNameList.put("[憨笑]", "expression_29_2x");
        mQQFaceFileNameList.put("[悠闲]", "expression_30_2x");
        mQQFaceFileNameList.put("[奋斗]", "expression_31_2x");
        mQQFaceFileNameList.put("[咒骂]", "expression_32_2x");
        mQQFaceFileNameList.put("[疑问]", "expression_33_2x");
        mQQFaceFileNameList.put("[嘘]", "expression_34_2x");
        mQQFaceFileNameList.put("[晕]", "expression_35_2x");
        mQQFaceFileNameList.put("[疯了]", "expression_36_2x");
        mQQFaceFileNameList.put("[衰]", "expression_37_2x");
        mQQFaceFileNameList.put("[骷髅]", "expression_38_2x");
        mQQFaceFileNameList.put("[敲打]", "expression_39_2x");
        mQQFaceFileNameList.put("[再见]", "expression_40_2x");
        mQQFaceFileNameList.put("[擦汗]", "expression_41_2x");
        mQQFaceFileNameList.put("[抠鼻]", "expression_42_2x");
        mQQFaceFileNameList.put("[鼓掌]", "expression_43_2x");
        mQQFaceFileNameList.put("[糗大了]", "expression_44_2x");
        mQQFaceFileNameList.put("[坏笑]", "expression_45_2x");
        mQQFaceFileNameList.put("[左哼哼]", "expression_46_2x");
        mQQFaceFileNameList.put("[右哼哼]", "expression_47_2x");
        mQQFaceFileNameList.put("[哈欠]", "expression_48_2x");
        mQQFaceFileNameList.put("[鄙视]", "expression_49_2x");
        mQQFaceFileNameList.put("[委屈]", "expression_50_2x");
        mQQFaceFileNameList.put("[快哭了]", "expression_51_2x");
        mQQFaceFileNameList.put("[阴险]", "expression_52_2x");
        mQQFaceFileNameList.put("[亲亲]", "expression_53_2x");
        mQQFaceFileNameList.put("[吓]", "expression_54_2x");
        mQQFaceFileNameList.put("[可怜]", "expression_55_2x");
        mQQFaceFileNameList.put("[菜刀]", "expression_56_2x");
        mQQFaceFileNameList.put("[西瓜]", "expression_57_2x");
        mQQFaceFileNameList.put("[啤酒]", "expression_58_2x");
        mQQFaceFileNameList.put("[篮球]", "expression_59_2x");
        mQQFaceFileNameList.put("[乒乓]", "expression_60_2x");
        mQQFaceFileNameList.put("[咖啡]", "expression_61_2x");
        mQQFaceFileNameList.put("[饭]", "expression_62_2x");
        mQQFaceFileNameList.put("[猪头]", "expression_63_2x");
        mQQFaceFileNameList.put("[玫瑰]", "expression_64_2x");
        mQQFaceFileNameList.put("[凋谢]", "expression_65_2x");
        mQQFaceFileNameList.put("[嘴唇]", "expression_66_2x");
        mQQFaceFileNameList.put("[爱心]", "expression_67_2x");
        mQQFaceFileNameList.put("[心碎]", "expression_68_2x");
        mQQFaceFileNameList.put("[蛋糕]", "expression_69_2x");
        mQQFaceFileNameList.put("[闪电]", "expression_70_2x");
        mQQFaceFileNameList.put("[炸弹]", "expression_71_2x");
//        mQQFaceFileNameList.put("[刀]", "菜刀");
        mQQFaceFileNameList.put("[足球]", "expression_73_2x");
        mQQFaceFileNameList.put("[瓢虫]", "expression_74_2x");
        mQQFaceFileNameList.put("[便便]", "expression_75_2x");
        mQQFaceFileNameList.put("[月亮]", "expression_76_2x");
        mQQFaceFileNameList.put("[太阳]", "expression_77_2x");
        mQQFaceFileNameList.put("[礼物]", "expression_78_2x");
        mQQFaceFileNameList.put("[拥抱]", "expression_79_2x");
        mQQFaceFileNameList.put("[强]", "expression_80_2x");
        mQQFaceFileNameList.put("[弱]", "expression_81_2x");
        mQQFaceFileNameList.put("[握手]", "expression_82_2x");
        mQQFaceFileNameList.put("[胜利]", "expression_83_2x");
        mQQFaceFileNameList.put("[抱拳]", "expression_84_2x");
        mQQFaceFileNameList.put("[勾引]", "expression_85_2x");
        mQQFaceFileNameList.put("[拳头]", "expression_86_2x");
        mQQFaceFileNameList.put("[差劲]", "expression_87_2x");
        mQQFaceFileNameList.put("[爱你]", "expression_88_2x");
        mQQFaceFileNameList.put("[NO]", "expression_89_2x");
        mQQFaceFileNameList.put("[OK]", "expression_90_2x");
        mQQFaceFileNameList.put("[爱情]", "expression_91_2x");
        mQQFaceFileNameList.put("[飞吻]", "expression_92_2x");
        mQQFaceFileNameList.put("[跳跳]", "expression_93_2x");
        mQQFaceFileNameList.put("[发抖]", "expression_94_2x");
        mQQFaceFileNameList.put("[怄火]", "expression_95_2x");
        mQQFaceFileNameList.put("[转圈]", "expression_96_2x");
        mQQFaceFileNameList.put("[磕头]", "expression_97_2x");
        mQQFaceFileNameList.put("[回头]", "expression_98_2x");
        mQQFaceFileNameList.put("[跳绳]", "expression_99_2x");
        mQQFaceFileNameList.put("[投降]", "expression_100_2x");
//        mQQFaceFileNameList.put("[激动]", "smiley_100");
//        mQQFaceFileNameList.put("[街舞]", "smiley_101");
//        mQQFaceFileNameList.put("[献吻]", "smiley_102");
//        mQQFaceFileNameList.put("[左太极]", "smiley_103");
//        mQQFaceFileNameList.put("[右太极]", "smiley_104");

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
        // 如果没有发现表情图片，并且当前是输入状态，不再重设输入框
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
