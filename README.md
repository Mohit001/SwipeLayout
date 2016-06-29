# SwipeLayout

You can add swipelayout in listview、ExpandableListView、GridView ...


The effect：

###　　 ListView　　　　　　　GirdView　　　　　ExpandableListView
<img src="http://fanrunqi.github.io/images/SwipeLayout/1.gif" width = "232" height = "386"  /> ......
<img src="http://fanrunqi.github.io/images/SwipeLayout/3.gif" width = "232" height = "386"  /> ......
<img src="http://fanrunqi.github.io/images/SwipeLayout/2.gif" width = "232" height = "386"  /> 

# Usage

## Step 1

> Copy [SwipeLayout.java](https://github.com/fanrunqi/SwipeLayout/blob/master/app/src/main/java/cn/scu/fanrunqi/swipelayout/SwipeLayout.java) to your project.


## Step 2

> On the basis of the following attributes ,add your own content and swipe menus in your item.xml.

```
<?xml version="1.0" encoding="utf-8"?>
<xx.xxx.xxxx.SwipeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="(Specific value) dp"
    android:id="@+id/swipelayout"
    android:orientation="horizontal"
    >
        <xxxLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent" >
           // --------your content-----------
        </xxxLayout>

        <xxxLayout
            android:layout_width="(Specific value) dp"
            android:layout_height="match_parent">
          // ---------your swipe menus----------
        </xxxLayout>
    </xx.xxx.xxxx.SwipeLayout>
```

## Step 3

> in your getView() or getChildView（）add following code.

```
 if (convertView == null) {
          
          
         /**
         * add this swipeview
         */
         SwipeLayout.addSwipeView(holder.swipelayout);
     }
    
  //if you need delete item
   holder.BtDetele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    

                    /**
                     * remove this swipeview
                     */
                    SwipeLayout.removeSwipeView(holder.swipelayout);
                }
            });
```

### Thanks

> Everyone who has contributed code and reported issues!
