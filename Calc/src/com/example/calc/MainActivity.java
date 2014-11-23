package com.example.calc;

import java.util.Stack;
import java.util.TreeMap;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.lang.Character;
public class MainActivity extends Activity  {

	Button zero,one,two,three,four,five,six,seven,eight,nine;
	Button add,sub,mul,div,decimal,delete,equals;
	EditText display;
	
	Stack<String> ops  = new Stack<String>();
    Stack<Double> vals = new Stack<Double>();
    TreeMap<String, Integer> precedence = new TreeMap<String, Integer>();
    
    String  number , operator ;
	protected boolean isDouble;
	protected boolean readOperator;
    
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        precedence.put("(", 0);   // for convenience with algorithm
        precedence.put(")", 0);  
        precedence.put("+", 1);   // + and - have lower precedence than * and /
        precedence.put("-", 1);
        precedence.put("x", 2);
        precedence.put("/", 2);
        
        number = "";
        operator = "";
        isDouble = false ;
        
        
        display = (EditText)findViewById(R.id.editText1);
        zero = (Button)findViewById(R.id.zero);
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        seven = (Button)findViewById(R.id.seven);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        add = (Button)findViewById(R.id.plus);
        sub = (Button)findViewById(R.id.minus);
        div = (Button)findViewById(R.id.divide);
        mul = (Button)findViewById(R.id.multiply);
        decimal = (Button)findViewById(R.id.decimal);
        equals = (Button)findViewById(R.id.equals);
        delete = (Button)findViewById(R.id.delete);
        
        zero.setOnClickListener(myButtonListener);
        one.setOnClickListener(myButtonListener);
        two.setOnClickListener(myButtonListener);
        three.setOnClickListener(myButtonListener);
        four.setOnClickListener(myButtonListener);
        five.setOnClickListener(myButtonListener);
        six.setOnClickListener(myButtonListener);
        seven.setOnClickListener(myButtonListener);
        eight.setOnClickListener(myButtonListener);
        nine.setOnClickListener(myButtonListener);
        add.setOnClickListener(myButtonListener);
        sub.setOnClickListener(myButtonListener);
        mul.setOnClickListener(myButtonListener);
        div.setOnClickListener(myButtonListener);
        decimal.setOnClickListener(myButtonListener);
        equals.setOnClickListener(myButtonListener);
        delete.setOnClickListener(myButtonListener);
        
        
        delete.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

             Log.v("ABC", "on long press");
              display.setText("");
              vals.clear();
              ops.clear();
              number = "";
              isDouble = false;
              
                return true;
            }
        });
        
        
    }
    
    private OnClickListener myButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
        	
        switch(v.getId())
        {
        case R.id.zero  : display.append("0");
                          if(number.equals(""))
                        	  number = "0";
                          else
                        	  number = number + "0";
                          readOperator = false;
                          break;
        case R.id.one   : display.append("1");
				        if(number.equals(""))
				      	  number = "1";
				        else
				        	 number = number + "1";
				        readOperator = false;
        				  break;
        case R.id.two   : display.append("2");
                         if(number.equals(""))
                           number = "2";
                         else
        				  number = number + "2";
                         readOperator = false;       				 
                          break;
        case R.id.three  : display.append("3");
				        if(number.equals(""))
				      	  number = "3";
				        else
				        	 number = number + "3";
				        readOperator = false;
				        break;
        case R.id.four  : display.append("4");
				        if(number.equals(""))
				      	  number = "4";
				        else
				        	 number = number + "4";
				        readOperator = false;
        					break;
        case R.id.five  : display.append("5");
				        if(number.equals(""))
				      	  number = "5";
				        else
				        	 number = number + "5";
				        readOperator = false;
				        	break;
        case R.id.six  : display.append("6");
				        if(number.equals(""))
				      	  number = "6";
				        else
				        	 number = number + "6";
				        readOperator = false;
        				 break;
        case R.id.seven : display.append("7");
				        if(number.equals(""))
				      	  number = "7";
				        else
				        	 number = number + "7";
                            break;
        case R.id.eight  : display.append("8");
				        if(number.equals(""))
				      	  number = "8";
				        else
				        	 number = number + "8";
				        readOperator = false;
                           break;
        case R.id.nine  : display.append("9");
				        if(number.equals(""))
				      	  number = "9";
				        else
				        	 number = number + "9";
				        readOperator = false;
				        	break;
        case R.id.plus  : if(number.equals("")&& vals.isEmpty()) break;
                          if(readOperator == true)
                          {
                        	   ops.pop();
                        	   Editable text ;
                               text = display.getText();
                               display.setText("");
                               display.append((CharSequence)text,0,text.length()-1);
                          }
        				  display.append("+");
                          pushNumber();
                          operator = "+";
                          calculate();
                          readOperator = true;
                           break;
        case R.id.minus  : if(number.equals("")&& vals.isEmpty()) break;
					        if(readOperator == true)
					        {
					      	   ops.pop();
					      	   Editable text ;
					             text = display.getText();
					             display.setText("");
					             display.append((CharSequence)text,0,text.length()-1);
					        }
        		           display.append("-");
                           pushNumber();
                           operator = "-";
                           calculate();
                           readOperator = true;
                           break;
        case R.id.multiply  : if(number.equals("")&& vals.isEmpty()) break;
					        if(readOperator == true)
					        {
					      	   ops.pop();
					      	   Editable text ;
					             text = display.getText();
					             display.setText("");
					             display.append((CharSequence)text,0,text.length()-1);
					        }
        					  display.append("x");
                              pushNumber();
                              operator = "x";
                              calculate();
                              readOperator = true;
                               break;
        case R.id.divide  : if(number.equals("")&& vals.isEmpty()) break;
					        if(readOperator == true)
					        {
					      	   ops.pop();
					      	   Editable text ;
					           text = display.getText();
					           display.setText("");
					           display.append((CharSequence)text,0,text.length()-1);
					        }
        					display.append("/");
                            pushNumber();
                            operator = "/";
                            calculate();
                            readOperator = true;
                             break;
        case R.id.decimal  : if(isDouble && readOperator)  break;
        	                display.append(".");
					        if(number.equals(""))
						      	  number = "0.";
						    else
						      	 number = number + ".";
					        isDouble = true; 
					          break;
        case R.id.equals  : if(number.equals("") && vals.isEmpty()) break;
                             if(readOperator) break;
        	               pushNumber();
        				   //display.setText("");
                           output();
                            break;
                            
                        
        case R.id.delete  : if(display.length() >0 && !Character.isDigit(display.getText().charAt(display.length()-1))
        		                && display.getText().charAt(display.length()-1) != '.' )
                            {
        	                   ops.pop();
        	                   readOperator = false;
                            }
                            else
                            if(number.equals(""))
                            {
                            	display.setText("");
                            	ops.clear();
                            	vals.clear();
                            	isDouble = false;
                            	break;
                            }
                            else
        	                 number = number.substring(0,number.length()-1);
        				   if(display.length() >0)
                           {
        	                Editable text ;
                            text = display.getText();
                            display.setText("");
                         	display.append((CharSequence)text,0,text.length()-1);
                           } 
                         	break;
        
        	
        }
        	
      }
    } ;
    
    public static double eval(String op, double val1, double val2) {
        if (op.equals("+")) return val1 + val2;
        if (op.equals("-")) return val1 - val2;
        if (op.equals("/")) return val1 / val2;
        if (op.equals("x")) return val1 * val2;
        throw new RuntimeException("Invalid operator");
    }
    @SuppressLint("Assert") public void output() {
		// TODO Auto-generated method stub
    	if(vals.size()<2) return;
    	 while (!ops.isEmpty() ) {
             String op = ops.pop();
             double val2 = vals.pop();
             double val1 = vals.pop();
             vals.push(eval(op, val1, val2));
         }
    	 if(isDouble)
    	  display.setText(vals.peek().toString());
    	 else
    	 {
    	  double val =  vals.peek();
    	  int value = (int) val;
    	 
    	  display.setText(Integer.toString(value));
    	 }
    	 //vals.pop();
    	 
         //assert vals.isEmpty();
         assert ops.isEmpty();
		
	}
	public void pushNumber()
    {
		Log.v("push","Number;"+number);
		if(number.equals("")) return ;
    	 vals.push(Double.parseDouble(number));
    	 number = "";
    	 //isDouble = false;
    }
    
    public void calculate()
    {
    	while (true) {

            // the last condition ensures that the operator with higher precedence is evaluated first
            if (ops.isEmpty() || operator.equals("(") || (precedence.get(operator) > precedence.get(ops.peek()))) {
                ops.push(operator);
                break;
            }

            // evaluate expression
            String op = ops.pop();

            // but ignore left parentheses
            if (op.equals("(")) {
                assert operator.equals(")");
                break;
            }

            // evaluate operator and two operands and push result onto value stack
            else {
                double val2 = vals.pop();
                double val1 = vals.pop();
                vals.push(eval(op, val1, val2));
            }
        }
    }
}
