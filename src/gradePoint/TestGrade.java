package gradePoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestGrade {

	public static void main(String[] args) {
		int index = 0;//查哪一行数据
		float point = 0;//绩点
        try {
        	File file = new File("d:\\point.csv");
        	//InputStreamReader Isr = new InputStreamReader(new FileInputStream(file),"UTF-8")
            BufferedReader reader = new BufferedReader
            		(new InputStreamReader(new FileInputStream(file),"GBK"));//换成你的文件名 
            //第一行信息，为标题信息，不用,如果需要，注释掉 
            String line = null;
            while((line=reader.readLine())!=null){  
                String items[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                for(int i=0;i<items.length;i++) {//通过第一行信息，找出要查的数据在哪一列
                	if(items[i].equals("总成绩")) {            		
                		index = i;
                		break;
                	}
                }
                String item = items[index];//这就是你要的数据了
                if(!item.equals("总成绩")) {
                	float value = Float.parseFloat(item);//如果是数值，可以转化为数值 
                	point = getPoint(value);
                }              
                System.out.println(item+"\t"+point);             
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	
	private static Float getPoint(float value) {
		float point = 0;
		if(value>=60&value<65) {
			point = 1;
		}
		else if(value>=65&value<70) {
			point = 1.5f;
		}
		else if(value>=70&value<75) {
			point = 2;
		}
		else if(value>=75&value<80) {
			point = 2.5f;
		}
		else if(value>=80&value<85) {
			point = 3;
		}
		else if(value>=85&value<90) {
			point = 3.5f;
		}
		else if(value>=90&value<95) {
			point = 4;
		}
		else if(value>=95&value<100) {
			point = 4.5f;
		}
		return point;
	}
}
