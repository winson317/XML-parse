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
				//����xml��Դ��id��ȡ��������Դ�Ľ������� XmlResourceParser��XmlPullParse������
				XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.books);
				try
				{
					StringBuilder sb = new StringBuilder("");
					while (xmlResourceParser.getEventType() != XmlResourceParser.END_DOCUMENT) //��û�е�XML�ĵ��Ľ�β��
					{
						if (xmlResourceParser.getEventType() == XmlResourceParser.START_TAG)  //��������˿�ʼ��ǩ
						{
							String tagName = xmlResourceParser.getName();  //��ȡ�ñ�ǩ�ı�ǩ��
							
							if (tagName.equals("book"))  //�������book��ǩ
							{
								String bookName = xmlResourceParser.getAttributeValue(null, "price"); //������������ȡ����ֵ
								sb.append("�۸�: ");
								sb.append(bookName);
								String bookPrice = xmlResourceParser.getAttributeValue(1); //��������������ȡ����ֵ
								sb.append("�������ڣ�");
								sb.append(bookPrice);
								sb.append("������");
								sb.append(xmlResourceParser.nextText()); //��ȡ�ı��ڵ��ֵ
							}
							sb.append("\n");
						}
							xmlResourceParser.next(); //��ȡ����������һ���¼�
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
