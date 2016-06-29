# SwipeLayout

可添加侧滑菜单的布局（可用于listview、ExpandableListView、GridView等）


效果如图：

### ListView & GirdView & ExpandableListView
<img src="http://img.blog.csdn.net/20160629120426480" width = "232" height = "386"  />
<img src="http://img.blog.csdn.net/20160629120455527" width = "232" height = "386"  />
<img src="http://img.blog.csdn.net/20160629120444668" width = "232" height = "386"  />

# 使用方法

## 第一步

> 把 [SwipeLayout.java](https://github.com/fanrunqi/SwipeLayout/blob/master/app/src/main/java/cn/scu/fanrunqi/swipelayout/SwipeLayout.java) 拷贝到你的项目里


## 第二步

> 给你的swipelayout_item.xml布局，设置自己的内容和侧滑菜单（在以下属性基础上做添加）

```
<?xml version="1.0" encoding="utf-8"?>
<cn.scu.fanrunqi.swipelayout.SwipeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="具体值（如100dp）"
    android:id="@+id/swipelayout"
    android:orientation="horizontal"
    >
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent" >
           //内容布局
        </LinearLayout>

        <LinearLayout
            android:layout_width="具体值（如120dp）"
            android:layout_height="match_parent"
            android:orientation="horizontal">
          //侧滑菜单布局
        </LinearLayout>
    </cn.scu.fanrunqi.swipelayout.SwipeLayout>
```

## 第三步 

> 在你的 Adapter的getView() 或是 getChildView（）方法中添加两行代码

```
 if (convertView == null) {
          
          
         /**
         * 添加该item
         */
         SwipeLayout.addSwipeView(holder.swipelayout);
     }
    
  //如果你要删除item
   holder.llDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    

                    /**
                     * 移除该item
                     */
                    SwipeLayout.removeSwipeView(holder.swipelayout);
                }
            });
```

## About

> 有什么疑问可以在Issues留言！
