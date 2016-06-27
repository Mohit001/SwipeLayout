package cn.scu.fanrunqi.swipelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by fanrunqi on 2016/6/26.
 */
public class SwipeLayout extends LinearLayout {
    /**
     * 滑动的工具类
     */
    private Scroller scroller;
    /**
     * 认为是滑动的最短距离
     */
    private int mTouchSlop;
    /**
     * 内容和菜单的宽度
     */
    private int leftViewWidth;
    private int rightViewWidth;
    /**
     * 侧边栏收缩状态
     */
    public static final int EXPAND = 0;
    public static final int SHRINK = 1;
    /**
     * 是否发生横向移动
     */
    Boolean isHorizontalMove;
    /**
     * 用于记录位置
     */
    float startX;
    float startY;
    float curX;
    float curY;
    float lastX;
    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(HORIZONTAL);
        scroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed,l,t,r,b);
        //获取两个子view的宽度
        View leftView = this.getChildAt(0);
        leftViewWidth = leftView.getMeasuredWidth();

        View rightView = this.getChildAt(1);
        rightViewWidth = rightView.getMeasuredWidth();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("SwipeLayout","dispatchTouchEvent_ACTION_DOWN");
                //不允许父view对触摸事件的拦截
                disallowParentsInterceptTouchEvent(getParent());
                startX = ev.getX();
                startY = ev.getY();
                isHorizontalMove =false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("SwipeLayout","dispatchTouchEvent_ACTION_MOVE");
                if(!isHorizontalMove){
                curX = ev.getX();
                curY = ev.getY();
                float dx = curX - startX;
                float dy = curY - startY;
                    //认为发生滑动
                    if(dx*dx+dy*dy > mTouchSlop*mTouchSlop){
                        //垂直滑动
                        if (Math.abs(dy) > Math.abs(dx)){
                            //允许父view对触摸事件拦截
                            allowParentsInterceptTouchEvent(getParent());
                            if(null != onSwipeListener){
                                onSwipeListener.onHorizontalMoved(this,false);
                            }
                        }else{//水平滑动
                            isHorizontalMove = true;
                            lastX = curX;
                        }
                    }
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isHorizontalMove){
            //发生水平滑动，把事件中断到本层onTouchEvent中处理
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("SwipeLayout","onTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("SwipeLayout","onTouchEvent_ACTION_MOVE");
                curX = ev.getX();
                float dX = curX-lastX;
                lastX = curX;
                if(isHorizontalMove){
                    //滑动的距离与实际相反，因为滚动的时候移动的是内容，不是view
                    int disX = getScrollX() + (int)(-dX);//位移增量
                    //如果滚动距离大于菜单宽度
                    if(disX>rightViewWidth){
                         //向右滚动内容，直到右侧菜单显示出来
                         scrollTo(rightViewWidth,0);
                    }else if(disX<0){
                        //位移增量小于0，移动到起点
                        scrollTo(0, 0);
                    }else{
                        //开始滚动时mScrollX=0
                        scrollTo(disX+0, 0);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("SwipeLayout","onTouchEvent_ACTION_UP");
                    float endX = ev.getX();
                    if(endX<startX){
                        ScrollShowMenu(EXPAND);
                    }else{
                        ScrollShowMenu(SHRINK);
                    }
            default:
                break;
        }

        return true;
    }
    public void ScrollShowMenu(int type){
        int dx =0;
        switch (type){
            case EXPAND:
                dx = rightViewWidth-getScrollX();
                break;
            case SHRINK:
                dx = 0-getScrollX();
                break;
            default:
                break;
        }
        scroller.startScroll(getScrollX(),0,dx,0,Math.abs(dx)/2);
        invalidate();
    }

    @Override
    public void computeScroll() {
        // 更新当前的X轴偏移量
        if (scroller.computeScrollOffset()) { // 返回true代表正在模拟数据，false 已经停止模拟数据
            scrollTo(scroller.getCurrX(), 0); // 更新X轴的偏移量
            invalidate();
        }
    }
    /**
     * 因为不知道是父类那一层会拦截触摸事件，所以递归向上设置标志位
     * 直到parent是activity，就直接返回
     */
    private void disallowParentsInterceptTouchEvent(ViewParent parent) {
        if (null == parent) {
            return;
        }
        parent.requestDisallowInterceptTouchEvent(true);
        disallowParentsInterceptTouchEvent(parent.getParent());
    }
    private void allowParentsInterceptTouchEvent(ViewParent parent) {
        if (null == parent) {
            return;
        }
        parent.requestDisallowInterceptTouchEvent(false);
        allowParentsInterceptTouchEvent(parent.getParent());
    }


    /**
     * 回调滚动接口
     */
    OnSwipeListener onSwipeListener = null;
    public void setOnSwipeListener(OnSwipeListener listener) {
        this.onSwipeListener = listener;
    }

    public interface OnSwipeListener {
        void onHorizontalMoved(SwipeLayout sll, boolean isHorizontal);
    }
}
