package com.smart.HealthAssistant.data;

import com.smart.HealthAssistant.R;
import com.smart.HealthAssistant.bean.MsgInfo;

import java.util.ArrayList;
import java.util.List;

public class DefaultDataFacotry {

    //标题
    public String[] DEFAULT_TITLE = new String[]{
        "心脏病患者有7大怕，很多人都遇",
            "国庆长假健康攻略！人手必备",
            "2020-04-28最适合女性春天吃的7种野菜",
            "苹果和它一起煮，女人常吃失眠好"
    };

    //描述
    public String[] DEFAULT_CONTENT = new String[]{
            "心脏病患者有7大怕，很多人都遇上过！ 患有心脏疾病的人很多时候要特别注意自己的身体，尤其是一些诱发因素。大部分都知道气温的降低会对于心脏患者的病情造成影响，但是在平时生活中还是有其他的诱发因素也是心脏患...",
            "国庆长假健康攻略！人手必备 假期虽难得，但饮食、出行、补觉都要注重身体健康。小编这就给大家支几招，只要稍加注意，假期都可以变得健康又完美！ 饮食篇： 减盐油、少热量 不宜过咸：正常人的食盐量，以每日不超过...",
            "最适合女性春天吃的7种野菜 野菜没有化学药物的污染，是纯天然的绿色食物，它们营养丰富、味道鲜美，春季如何养生？女性朋友们想必一直在苦苦思索吧，其实想要春季养生好，只要女性善于吃以下几种野菜就可以了。 荠...",
            "苹果和它一起煮，女人常吃失眠好了 每个女人都爱美，尤其是到了一定的年龄后，看着脸上慢慢出现的细纹，总是会担心容颜老去，因此为了留住青春容颜，有些人都会买大量的化妆品用在脸上；而我更喜欢从饮食调理，平时..."
    };

    //链接
    public String[] DEFAULT_URL = new String[]{
            "https://www.jkcsw.com/jiankang/75746.html",
            "https://www.jkcsw.com/jiankang/75733.html",
            "https://www.jkcsw.com/jiankang/75720.html",
            "https://www.jkcsw.com/jiankang/75707.html",
    };

    //图片
    public int[] DEFAULT_PIC = new int[]{
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
    };

    public List<MsgInfo> mMsgInfoList = new ArrayList<>();

    public DefaultDataFacotry(){
        for (int i=0;i<DEFAULT_CONTENT.length;i++){
            MsgInfo info = new MsgInfo();
            info.setTitle(DEFAULT_TITLE[i]);
            info.setContent(DEFAULT_CONTENT[i]);
            info.setmPicId(DEFAULT_PIC[i]);
            info.setUrl(DEFAULT_URL[i]);
            mMsgInfoList.add(info);
        }
    }
}
