package walden.shoppingcart.demo.test3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import walden.lib.cart.ShopCart;
import walden.shoppingcart.demo.A;
import walden.shoppingcart.demo.R;

/**
 * Created by walden on 2017/2/15.
 */

public class Test3Activity extends Activity implements v, View.OnClickListener {


    private final int COMPLETE = 1;
    private final int EDITOR = 2;
    public static final char RMB = '¥';


    private ExpandableListView mListView;

    /**
     * 结算删除是一家
     */
    private TextView delTv;

    /**
     * 编辑/完成
     */
    private TextView titleTv;

    /**
     * 价格
     */
    private TextView priceTv;

    /**
     * 运费
     */
    private TextView freightTv;

    private CheckBox selectAllCb;


    private Test3Adapter mAdapter;

    /**
     * 购物车状态
     */
    private int mCartState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_ui_3);

        initTitle();
        findView();
        completeState();


        testData();
    }

    private void testData() {
        ParsingServiceShopCart tb3 = null;
        String josn = A.getJson(this, "test3.json");
        try {
            tb3 = new Gson().fromJson(josn, ParsingServiceShopCart.class);
        } catch (Exception e) {
            e.printStackTrace();
            //...
            return;
        }
        ShopCart cart = new ShopCart();
        cart.loadCart(tb3.getData().getCommunityCartList());
        mAdapter = new Test3Adapter(this, cart);
        mListView.setAdapter(mAdapter);
        for (int i = 0; i < mAdapter.getGroupCount(); i++)
            mListView.expandGroup(i);
    }

    private void initTitle() {
        //setTitl;
        titleTv = (TextView) findViewById(R.id.shop_cart_title_tv);
        titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //完成 || 编辑 状态切换
                if (mCartState == COMPLETE) {
                    editorState();
                } else if (mCartState == EDITOR) {
                    completeState();
                }
            }
        });
    }

    private void findView() {
        delTv = (TextView) findViewById(R.id.shop_cart_del_tv);
        priceTv = (TextView) findViewById(R.id.shop_cart_price_tv);
        freightTv = (TextView) findViewById(R.id.shop_cart_freight_tv);

        selectAllCb = (CheckBox) findViewById(R.id.shop_cart_cb);

        mListView = (ExpandableListView) findViewById(R.id.shop_cart_lv);

        //全选
        findViewById(R.id.shop_cart_select_all_tv).setOnClickListener(this);

        delTv.setOnClickListener(this);
        priceTv.setOnClickListener(this);
        freightTv.setOnClickListener(this);
        selectAllCb.setOnClickListener(this);

    }


    @Override
    public void completeState() {
        if (mCartState == COMPLETE) return;

        mCartState = COMPLETE;

        titleTv.setText(R.string.editor);
        // TODO: 2017/2/14 结算 ( 获取选中数量 )
        delTv.setText(R.string.settlement);

    }

    @Override
    public void editorState() {
        if (mCartState == EDITOR) return;

        mCartState = EDITOR;

        titleTv.setText(R.string.complete);
        delTv.setText(R.string.delete);
    }

    @Override
    public void del() {

    }

    @Override
    public void settlement() {

    }

    @Override
    public void selectAll(boolean select) {
        if (mAdapter != null)
            mAdapter.CheckAll(select);
    }


    public void heji(String s) {
        priceTv.setText(RMB + s);
    }

    @Override
    public void setData(List<ParsingServiceShopCart.DataBean.CommunityCartListBean> b) {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

//      使用  switch 最好了
        if (i == R.id.shop_cart_del_tv) {
            //结算 | 删除
            if (mCartState == COMPLETE) {
                settlement();
            } else {
                del();
            }
        } else if (i == R.id.shop_cart_price_tv) {
            //价格
        } else if (i == R.id.shop_cart_freight_tv) {
            //运费
        } else if (i == R.id.shop_cart_cb) {
            //全选
            boolean b = selectAllCb.isChecked();
            selectAll(b);
        } else if (i == R.id.shop_cart_select_all_tv) {
            //全选
            boolean b = selectAllCb.isChecked();
            selectAll(!b);
            selectAllCb.setChecked(!b);
        }
    }

    public static void toActivity(Context c) {
        c.startActivity(new Intent(c, Test3Activity.class));
    }
}
