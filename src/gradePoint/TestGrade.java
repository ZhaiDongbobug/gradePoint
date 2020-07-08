package gradePoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TestGrade {
	
	public static String path;
	
	
	public static float result() {
		List pointList = getPointList();
		List creditList = getCreditList();
		float totalPoint = getTotalPoint(pointList);
		float totalCredit = getTotalCredit(creditList);
		float GPA = getGPA(totalPoint,totalCredit);
		return GPA;
	}
	
	private static List getCourseList() {
		int courseIndex = 0;//查课成绩列数据
		List courseList = new ArrayList();//所有课程的成绩集合
        try {
        	File file = new File(path);
        	//InputStreamReader Isr = new InputStreamReader(new FileInputStream(file),"UTF-8")
            BufferedReader reader = new BufferedReader
            		(new InputStreamReader(new FileInputStream(file),"GBK"));//换成你的文件名 
            //第一行信息，为标题信息，不用,如果需要，注释掉 
            String line = null;
            while((line=reader.readLine())!=null){  
                String items[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                for(int i=0;i<items.length;i++) {//通过第一行信息，找出要查的数据在哪一列
                	if(items[i].equals("课程名称")) {            		
                		courseIndex = i;
                	}
                }
                String courseItem = items[courseIndex];//该课程成绩
                courseList.add(courseItem);                          
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return courseList;
	}
	
	private static List getPointList() {
		int creditIndex = 0;//查学分列数据
		int gradeIndex = 0;//查课成绩数据
		List pointList = new ArrayList();//所有课程学分的学分集合
		float point = 0;//课程绩点
        try {
        	File file = new File(path);
        	//InputStreamReader Isr = new InputStreamReader(new FileInputStream(file),"UTF-8")
            BufferedReader reader = new BufferedReader
            		(new InputStreamReader(new FileInputStream(file),"GBK"));//换成你的文件名 
            //第一行信息，为标题信息，不用,如果需要，注释掉 
            String line = null;
            while((line=reader.readLine())!=null){  
                String items[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                for(int i=0;i<items.length;i++) {//通过第一行信息，找出要查的数据在哪一列
                	if(items[i].equals("总成绩")) {            		
                		gradeIndex = i;
                	}
                	if(items[i].equals("学分")) {            		
                		creditIndex = i;
                	}
                }
                String gradeItem = items[gradeIndex];//该课程成绩
                String creditItem = items[creditIndex];//该课程学分
                if(isDoubleOrFloat(gradeItem)&&isDoubleOrFloat(creditItem)) {
                	float grade = Float.parseFloat(gradeItem);
                    float credit = Float.parseFloat(creditItem);
                    point = getCoursePoint(grade)*credit;
                    pointList.add(point); 
                }
                                          
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return pointList;
	}
	
	private static List getCreditList() {
		int creditIndex = 0;//查学分列数据
		List creditList = new ArrayList();//所有课程学分的学分集合
		float point = 0;//课程绩点
        try {
        	File file = new File(path);
        	//InputStreamReader Isr = new InputStreamReader(new FileInputStream(file),"UTF-8")
            BufferedReader reader = new BufferedReader
            		(new InputStreamReader(new FileInputStream(file),"GBK"));//换成你的文件名 
            //第一行信息，为标题信息，不用,如果需要，注释掉 
            String line = null;
            while((line=reader.readLine())!=null){  
                String items[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                for(int i=0;i<items.length;i++) {//通过第一行信息，找出要查的数据在哪一列
                	if(items[i].equals("学分")) {            		
                		creditIndex = i;
                	}
                }
                String creditItem = items[creditIndex];//该课程学分
                if(isDoubleOrFloat(creditItem)) {
                	float credit = Float.parseFloat(creditItem);
                    creditList.add(credit);
                }                                        
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return creditList;
	}
	
	private static float getTotalCredit(List creditList) {//总学分（所有课程的学分总和）
		float totalCredit = 0;
		for(int i=1;i<creditList.size();i++) {
			totalCredit += (float)creditList.get(i);
		}
		return totalCredit;
	}
	
	private static float getTotalPoint(List pointList) {//所有课程的绩点乘学分得到的总和
		float totalPoint = 0;
		for(int i=1;i<pointList.size();i++) {
			totalPoint += (float)pointList.get(i);
		}
		return totalPoint;
	}
	
	
	private static Float getGPA(float totalPoint,float totalCredit) {//所有课程的平均绩点
		float GPA = totalPoint/totalCredit;
		return GPA;
	}
	
	private static Float getCoursePoint(float value) {//根据课程成绩得出该门课程绩点
		float coursePoint = 0;
		if(value>=60&value<65) {
			coursePoint = 1;
		}
		else if(value>=65&value<70) {
			coursePoint = 1.5f;
		}
		else if(value>=70&value<75) {
			coursePoint = 2;
		}
		else if(value>=75&value<80) {
			coursePoint = 2.5f;
		}
		else if(value>=80&value<85) {
			coursePoint = 3;
		}
		else if(value>=85&value<90) {
			coursePoint = 3.5f;
		}
		else if(value>=90&value<95) {
			coursePoint = 4;
		}
		else if(value>=95&value<100) {
			coursePoint = 4.5f;
		}
		return coursePoint;
	}
	
	public static boolean isDoubleOrFloat(String str) {//判断字符串是否是float数值
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
}
