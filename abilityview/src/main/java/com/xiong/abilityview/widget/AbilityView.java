package com.xiong.abilityview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiong.abilityview.bean.AbilityBean;
import com.xiong.abilityview.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiong
 * @time: 2017/11/10
 * @说明: 能力图View
 */

public class AbilityView extends View {

    private AbilityBean data;
    private List<PointF> abilityPoints;

    private int n;
    private float R;
    private float intervalCount;
    private float angle;

    private int viewWidth;
    private int viewHeight;

    private List<ArrayList<PointF>> pointsArrayList;

    private Paint linePaint;
    private Paint textPaint;

    public AbilityView(Context context) {
        this(context, null);
    }

    public AbilityView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbilityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initSize(context);
        initPoints();
        initPaint(context);
    }

    /**
     * 初始化固定数据
     */
    private void initSize(Context context) {
        // 边数
        n = 7;
        // 半径
        R = DensityUtils.dp2px(context, 100);
        // 层级
        intervalCount = 4;
        //一周是2π,这里用π，因为进制的问题，不能用360度,画出来会有问题
        angle = (float) ((2 * Math.PI) / n);

        //拿到屏幕的宽高，单位是像素
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        //控件设置为正方向
        viewWidth = screenWidth;
        viewHeight = screenWidth;
    }

    /**
     * 初始化多边形的点
     */
    private void initPoints() {
        //一个数组中每个元素又一是一个点数组,有几个多边形就有几个数组
        pointsArrayList = new ArrayList<>();
        float x;
        float y;
        for (int i = 0; i < intervalCount; i++) {
            //创建一个存储点的数组
            ArrayList<PointF> points = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                float r = R * ((float) (4 - i) / intervalCount);  //每一圈的半径都按比例减少
                //这里减去Math.PI / 2 是为了让多边形逆时针旋转90度，所以后面的所有用到cos,sin的都要减
                x = (float) (r * Math.cos(j * angle - Math.PI / 2));
                y = (float) (r * Math.sin(j * angle - Math.PI / 2));
                points.add(new PointF(x, y));
            }
            pointsArrayList.add(points);
        }
    }

    /**
     * 初始化画笔
     */
    private void initPaint(Context context) {
        //画线的笔
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置线宽度
        linePaint.setStrokeWidth(DensityUtils.dp2px(context, 1f));
        //抗锯齿
        linePaint.setAntiAlias(true);

        //画文字的笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置文字居中
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(DensityUtils.sp2px(context, 14f));
        //抗锯齿
        textPaint.setAntiAlias(true);
    }

    /**
     * 传入元数据
     *
     * @param data
     */
    public void setData(AbilityBean data) {
        if (data == null) {
            return;
        }
        this.data = data;

        //View本身调用迫使view重画
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initSize(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //把画布的原点移动到控件的中心点
        canvas.translate(viewWidth / 2, viewHeight / 2);

        drawPolygon(canvas);
        drawOutLine(canvas);
        drawAbilityLine(canvas);
        drawAbilityText(canvas);

        //坐标轴x,y 辅助用
//        linePaint.setColor(Color.RED);
//        canvas.drawLine(-(viewWidth / 2), 0, viewWidth / 2, 0, linePaint);
//        canvas.drawLine(0, -(viewWidth / 2), 0, viewWidth / 2, linePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //设置控件大小
        setMeasuredDimension(viewWidth, viewHeight);
    }

    /**
     * 绘制多边形框,每一层都绘制
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        canvas.save();//保存画布当前状态(平移、放缩、旋转、裁剪等),和canvas.restore()配合使用

        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);  //设置为填充且描边

        Path path = new Path();  //路径
        for (int i = 0; i < intervalCount; i++) {  //循环、一层一层的绘制
            //每一层的颜色都都不同
            switch (i) {
                case 0:
                    linePaint.setColor(Color.parseColor("#D4F0F3"));
                    break;
                case 1:
                    linePaint.setColor(Color.parseColor("#99DCE2"));
                    break;
                case 2:
                    linePaint.setColor(Color.parseColor("#56C1C7"));
                    break;
                case 3:
                    linePaint.setColor(Color.parseColor("#278891"));
                    break;
            }
            for (int j = 0; j < n; j++) {   //每一层有n个点
                float x = pointsArrayList.get(i).get(j).x;
                float y = pointsArrayList.get(i).get(j).y;
                if (j == 0) {
                    //如果是每层的第一个点就把path的起点设置为这个点
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();  //设置为闭合的
            canvas.drawPath(path, linePaint);
            path.reset();   //清除path存储的路径
        }
        canvas.restore();
    }

    /**
     * 画轮廓线
     * 1.先画最外面的多边形轮廓
     * 2.再画顶点到中心的线
     *
     * @param canvas
     */
    private void drawOutLine(Canvas canvas) {
        canvas.save();//保存画布当前状态(平移、放缩、旋转、裁剪等),和canvas.restore()配合使用

        linePaint.setColor(Color.parseColor("#000000"));
        linePaint.setStyle(Paint.Style.STROKE);  //设置空心的

        //先画最外面的多边形轮廓
        Path path = new Path();  //路径
        for (int i = 0; i < n; i++) {
            //只需要第一组的点
            float x = pointsArrayList.get(0).get(i).x;
            float y = pointsArrayList.get(0).get(i).y;
            if (i == 0) {
                //如果是第一个点就把path的起点设置为这个点
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close(); //闭合路径
        canvas.drawPath(path, linePaint);

        //再画顶点到中心的线
        for (int i = 0; i < n; i++) {
            float x = pointsArrayList.get(0).get(i).x;
            float y = pointsArrayList.get(0).get(i).y;
            canvas.drawLine(0, 0, x, y, linePaint); //起点都是中心点
        }

        canvas.restore();
    }

    /**
     * 画能力线
     *
     * @param canvas
     */
    private void drawAbilityLine(Canvas canvas) {
        canvas.save();

        //先把能力点初始化出来
        abilityPoints = new ArrayList<>();
        int[] allAbility = data.getAllAbility();
        for (int i = 0; i < n; i++) {
            float r = R * (allAbility[i] / 100.0f);  //能力值/100再乘以半径就是所占的比例
            float x = (float) (r * Math.cos(i * angle - Math.PI / 2));
            float y = (float) (r * Math.sin(i * angle - Math.PI / 2));
            abilityPoints.add(new PointF(x, y));
        }

        linePaint.setStrokeWidth(DensityUtils.dp2px(getContext(), 2f));
        linePaint.setColor(Color.parseColor("#E96153"));
        linePaint.setStyle(Paint.Style.FILL);  //设置填充
        linePaint.setAlpha(200);

        Path path = new Path();  //路径
        for (int i = 0; i < n; i++) {
            float x = abilityPoints.get(i).x;
            float y = abilityPoints.get(i).y;
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close();   //别忘了闭合

        canvas.drawPath(path, linePaint);

        canvas.restore();
    }

    /**
     * 画能力描述的文字
     *
     * @param canvas
     */
    private void drawAbilityText(Canvas canvas) {
        canvas.save();
        //先计算出坐标来
        ArrayList<PointF> textPoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            float r = R + DensityUtils.dp2px(getContext(), 15f);
            float x = (float) (r * Math.cos(i * angle - Math.PI / 2));
            float y = (float) (r * Math.sin(i * angle - Math.PI / 2));
            textPoints.add(new PointF(x, y));
        }
        //拿到字体测量器
        Paint.FontMetrics metrics = textPaint.getFontMetrics();
        String[] ability = AbilityBean.getAbility();
        for (int i = 0; i < n; i++) {
            float x = textPoints.get(i).x;
            //ascent:上坡度，是文字的基线到文字的最高处的距离
            //descent:下坡度,，文字的基线到文字的最低处的距离
            float y = textPoints.get(i).y - (metrics.ascent + metrics.descent);
            canvas.drawText(ability[i], x, y, textPaint);
        }

        canvas.restore();
    }
}
