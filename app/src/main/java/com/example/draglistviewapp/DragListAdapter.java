package com.example.draglistviewapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * リスト項目の並び替えを行うクラス
 * AndroidのBaseAdapterクラスを継承しています。データの並び替えを行う処理を実装しています。
 * ドラッグ中の選択項目はポップアップ表示する方針ですので、当クラスでは非表示にしています。
 */
public class DragListAdapter extends BaseAdapter {

    private static final String[] items = {
            "Android 1.0（APIレベル1）",
            "Android 1.1（APIレベル2）", "Android 1.5（APIレベル3）",
            "Android 1.6（APIレベル4）", "Android 2.0（APIレベル5）",
            "Android 2.0.1（APIレベル6）", "Android 2.1（APIレベル7）",
            "Android 2.2（APIレベル8）", "Android 2.3（APIレベル9）",
            "Android 2.3.3（APIレベル10）", "Android 3.0（APIレベル11）",
            "Android 3.1（APIレベル12）", "Android 3.2（APIレベル13）",
            "Android 4.0（APIレベル14）"
    };

    private Context context;
    private int currentPosition = -1;

    public DragListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * リスト項目のViewを取得する
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View作成
        if (convertView == null) {
            convertView = new TextView(context);
        }
        TextView textView = (TextView) convertView;
        textView.setTextSize(30);

        // データ設定
        textView.setText((String) getItem(position));

        // ドラッグ対象項目はListView側で別途描画するため、非表示にする
        if(position == currentPosition) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
        return textView;
    }

    /**
     * ドラッグ開始
     */
    public void startDrag(int position) {
        this.currentPosition = position;
    }

    /**
     * ドラッグに従ってデータを並び替える
     */
    public void doDrag(int newPosition) {
        String item = items[currentPosition];
        if (currentPosition < newPosition) {
            //リスト項目を下に移動している場合
            for (int i = currentPosition; i < newPosition; i++) {
                items[i] = items[i + 1];
            }
        } else if (currentPosition > newPosition) {
            //リスト項目を上に移動している場合
            for (int i = currentPosition; i > newPosition; i--) {
                items[i] = items[i - 1];
            }
        }
        items[newPosition] = item;

        currentPosition = newPosition;
    }

    /**
     * ドラッグ終了
     */
    public void stopDrag() {
        this.currentPosition = -1;
    }
}
