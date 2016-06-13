package com.example.xmlres;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class XmlRes extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//根据xml资源的id获取解析该资源的解析器。 XmlResourceParser是XmlPullParse的子类
				XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.books);
				try
				{
					StringBuilder sb = new StringBuilder("");
					while (xmlResourceParser.getEventType() != XmlResourceParser.END_DOCUMENT) //还没有到XML文档的结尾处
					{
						if (xmlResourceParser.getEventType() == XmlResourceParser.START_TAG)  //如果遇到了开始标签
						{
							String tagName = xmlResourceParser.getName();  //获取该标签的标签名
							
							if (tagName.equals("book"))  //如果遇到book标签
							{
								String bookName = xmlResourceParser.getAttributeValue(null, "price"); //根据属性名获取属性值
								sb.append("价格: ");
								sb.append(bookName);
								String bookPrice = xmlResourceParser.getAttributeValue(1); //根据属性索引获取属性值
								sb.append("出版日期：");
								sb.append(bookPrice);
								sb.append("书名：");
								sb.append(xmlResourceParser.nextText()); //获取文本节点的值
							}
							sb.append("\n");
						}
							xmlResourceParser.next(); //获取解析器的下一个事件
					}
					EditText show = (EditText)findViewById(R.id.show);
					show.setText(sb.toString());
				}
				catch (XmlPullParserException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
    }

}
