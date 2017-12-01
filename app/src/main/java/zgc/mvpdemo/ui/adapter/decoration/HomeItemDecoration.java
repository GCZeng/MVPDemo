package zgc.mvpdemo.ui.adapter.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import zgc.mvpdemo.R;


/**
 * Created by Nick on 2017/2/7
 */
public class HomeItemDecoration extends RecyclerView.ItemDecoration {
//    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public HomeItemDecoration(Context context) {
//        final TypedArray array = context.obtainStyledAttributes(ATTRS);
//        mDivider = array.getDrawable(0);
        mDivider = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP?
                context.getResources().getDrawable(R.drawable.recyclerview_item_decoration_default,null):
                context.getResources().getDrawable(R.drawable.recyclerview_item_decoration_default);
//        array.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    // 水平线
    public void drawHorizontal(Canvas c, RecyclerView parent) {

        final int childCount = parent.getChildCount();

        // 在每一个子控件的底部画线
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final int left = child.getLeft() + child.getPaddingLeft();
            final int right = child.getWidth() + child.getLeft() - child.getPaddingRight();
//            final int top = child.getBottom() - mDivider.getIntrinsicHeight() - child.getPaddingBottom();
//            final int bottom = top + mDivider.getIntrinsicHeight();

            final int top = child.getBottom()  - child.getPaddingBottom();
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    // 竖直线
    public void drawVertical(Canvas c, RecyclerView parent) {

        final int childCount = parent.getChildCount();

        // 在每一个子控件的右侧画线
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int right = child.getRight() - child.getPaddingRight();
            int left = right - mDivider.getIntrinsicWidth();
            final int top = child.getTop() + child.getPaddingTop();
            final int bottom = child.getTop() + child.getHeight() - child.getPaddingBottom();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

        }
    }

    // Item之间的留白
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, 0);
    }
}