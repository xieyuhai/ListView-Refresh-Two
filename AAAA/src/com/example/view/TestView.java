package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Scroller;

public class TestView extends android.view.View {

	private VelocityTracker velocityTracker;

	public TestView(Context context, AttributeSet attrs) {
		super(context, attrs);
		velocityTracker = VelocityTracker.obtain();
	}

	private float lastX;
	private float lastY;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		velocityTracker.addMovement(event);
		float rawX = event.getRawX();
		float rawY = event.getRawY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			// setTranslationX(getTranslationX() + (rawX - LastX));
			// setTranslationY(getTranslationY() + (rawY - lastY));

			// setPadding(rawY - lastY, top, 0, 0);
			MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
			mlp.leftMargin = (int) (mlp.leftMargin + rawX - lastX);
			mlp.topMargin = (int) (mlp.topMargin + rawY - lastY);
			Log.e("TAG2", String.format("x%s;y%s;left=%s-top=%s;;;", getScrollX(), getScrollY(), mlp.leftMargin,
					mlp.topMargin));

			requestLayout();
			// setLayoutParams(mlp);

			// LayoutParams lp = (LayoutParams) getLayoutParams();
			//
			// lp.leftMargin = (int) (lp.leftMargin + rawX - lastX);
			// lp.topMargin = (int) (lp.topMargin + rawY - lastY);
			// Log.e("TAG2", String.format("x%s;y%s;left=%s-top=%s;;;",
			// getScrollX(), getScrollY(), lp.leftMargin,
			// lp.topMargin));
			// setLayoutParams(lp);
			break;
		case MotionEvent.ACTION_UP:

			break;
		default:
			break;
		}
		lastX = rawX;
		lastY = rawY;
		// Log.e("TAG2", String.format("x%s;y%s;left=%s-top=%s;;;",
		// getScrollX(), getScrollY()),lp.leftMargin,lp.topMargin);

		scrollTo((int) getX(), (int) getY());
		return super.onTouchEvent(event);
	}

	public void calc() {
		velocityTracker.computeCurrentVelocity(1000);
		float xVelocity = velocityTracker.getXVelocity();
		float yVelocity = velocityTracker.getYVelocity();
		Log.e("TAG", String.format("x%s;y%s;", xVelocity, yVelocity));
	}

	@Override
	public void computeScroll() {
		Log.e("TAG3", String.format("computeScroll=x%s;y%s;", getScrollX(), getScrollY()));
		// scrollTo((int) getX(), (int) getY());
		super.computeScroll();
	}

	Scroller scroller = new Scroller(getContext());

	private void smoothScrollTo(int destX, int destY) {
		int scrollX = getScrollX();
		int deltaX = destX - scrollX;
	}

	@Override
	protected void onDetachedFromWindow() {

		velocityTracker.clear();
		velocityTracker.recycle();
		super.onDetachedFromWindow();
	}

}
