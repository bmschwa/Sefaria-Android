package org.sefaria.sefaria.TOCElements;

import android.content.Context;
import android.content.res.Resources;

import androidx.gridlayout.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import org.sefaria.sefaria.R;
import org.sefaria.sefaria.Util;
import org.sefaria.sefaria.activities.TOCActivity;
import org.sefaria.sefaria.database.Node;
import org.sefaria.sefaria.layouts.SefariaTextView;

/**
 *
 */
public class TOCNumBox extends SefariaTextView implements TOCElement {

    private Context context;
    private Node node;
    private Util.Lang lang;

    public  TOCNumBox(Context context){
        super(context);
        this.context = context;
        this.setVisibility(View.INVISIBLE);
    }

    public TOCNumBox(Context context, Node node, Util.Lang lang){
        super(context);

        //FORMATTING
        Resources resources = getResources();
        setBackgroundResource(Util.getDrawable(context,R.attr.button_selector_solid_drawable));
        setTextColor(Util.getColor(context,R.attr.text_verse_num_color));

        GridLayout.LayoutParams lp = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(
                (int) resources.getDimension(R.dimen.toc_numbox),(int) resources.getDimension(R.dimen.toc_numbox)));
        int margin = 3;
        lp.setMargins(margin,margin,margin,margin);
        setLayoutParams(lp);

        //setWidth((int) r.getDimension(R.dimen.toc_numbox));
        //setHeight((int) r.getDimension(R.dimen.toc_numbox));

        setPadding(1, 1, 1, 1);
        setGravity(Gravity.CENTER);
        setBackgroundResource(Util.getDrawable(context,R.attr.button_selector_solid_drawable));
        setTextColor(Util.getColor(context,R.attr.text_color_soft));

        this.node = node;
        this.context = context;
        this.lang = lang;
        init(lang);

    }

    private void init(Util.Lang lang){
        setLang(lang);

        this.setOnClickListener(clickListener);
    }

    @Override
    public void setLang(Util.Lang lang) {
        setFont(lang,false,15);
        setText(node.getNiceGridNum(lang));
    }



    OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!node.isTextSection()) {
                return;
            }
            TOCActivity.gotoTextActivity(context,node,lang);
        }
    };


}
