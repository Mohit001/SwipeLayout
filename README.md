# SwipeLayout

You can add swipelayout in listview、ExpandableListView、GridView ...


The effect：

###　　 ListView　　　　　　　GirdView　　　　　ExpandableListView
<img src="https://raw.githubusercontent.com/fanrunqi/SwipeLayout/master/screenshots/1.gif" width = "232" height = "386"  /> ......
<img src="https://raw.githubusercontent.com/fanrunqi/SwipeLayout/master/screenshots/2.gif" width = "232" height = "386"  /> ......
<img src="https://raw.githubusercontent.com/fanrunqi/SwipeLayout/master/screenshots/3.gif" width = "232" height = "386"  /> 

# Usage

## dependency

> Gradle
```
compile 'cn.fanrunqi:swipelayoutlibrary:1.0.1'  
```
> Maven
```
<dependency>
  <groupId>cn.fanrunqi</groupId>
  <artifactId>swipelayoutlibrary</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

## layout

> On the basis of the following attributes ,add your own content and swipe menus in your item.xml.

```
<?xml version="1.0" encoding="utf-8"?>
<cn.fanrunqi.swipelayoutlibrary.SwipeLayout
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
    </cn.fanrunqi.swipelayoutlibrary.SwipeLayout>
```

## code

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

# License
> Copyright 2016 fanrunqi

> Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  >  http://www.apache.org/licenses/LICENSE-2.0

> Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
