package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int postEq = 0;
    public void displayNumber(View num)
    {
        int number = Integer.parseInt(((Button)num).getText().toString());
        if (number<0 || number>9) return;

        TextView disp = findViewById(R.id.display);
        int sum = Integer.parseInt(disp.getText().toString());
        if (postEq==1) sum=0;
        if (((float)sum*10+number)>2147483647)
        {
            String errorMsg = "Number out of integer range";
            disp.setText(errorMsg);
            return;
        }

        int toDisplay = sum*10+number;
        disp.setText(String.valueOf(toDisplay));
    }

    int operation = 0;//1-add, 2-sub, 3-mul;
    int opSum = 0;
    public void operation(View b)
    {
        TextView disp = findViewById(R.id.display);
        if (operation==1) opSum += Integer.parseInt(disp.getText().toString());
        if (operation==2) opSum -= Integer.parseInt(disp.getText().toString());
        if (operation==3) opSum *= Integer.parseInt(disp.getText().toString());
        if (operation==0) opSum = Integer.parseInt(disp.getText().toString());
        disp.setText(String.valueOf(0));

        String op = ((Button)b).getText().toString();
        if (op.charAt(0)=='+') operation = 1;
        else if (op.charAt(0)=='-') operation = 2;
        else if (op.charAt(0)=='*') operation = 3;
        else return;
    }

    public void equal(View dump)
    {
        TextView disp = findViewById(R.id.display);
        int current = Integer.parseInt(disp.getText().toString()), result = 0;
        if (operation==1)
        {
            if(((float)opSum+(float)current) > 2147483647)
            {
                String errorMsg = "Number out of integer range";
                disp.setText(errorMsg);
                return;
            }
            result = opSum+current;
        }
        else if (operation==2) result = opSum-current;
        else if (operation==3)
        {
            if(((float)opSum*(float)current) > 2147483647)
            {
                String errorMsg = "Number out of integer range";
                disp.setText(errorMsg);
                return;
            }
            result = opSum*current;
        }
        disp.setText(String.valueOf(result));
        opSum=0;
        operation=0;
        postEq=1;
    }
}