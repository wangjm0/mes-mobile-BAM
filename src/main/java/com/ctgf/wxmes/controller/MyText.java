package com.ctgf.wxmes.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.Test;

public class MyText
{
	// 转换为二进制
	@Test
	public void test1()
	{
		/*
		 * for(int i =0; i<= 6; i++) { if((9&(1<<i)) != 0) {
		 * System.out.println((9&(1<<i))); } }
		 */
		/// String result = Integer.toBinaryString(65);
		// int r = Integer.parseInt(result);
		// System.out.println(r);
		// 二进制位
		// Integer[] code = new Integer[100];
		List<Integer> code = new ArrayList<>();
		// 十进制转换为二进制
		String result = Integer.toBinaryString(8);
		char[] cs = result.toCharArray();
		for (int i = 0; i < cs.length; i++)
		{
			// System.out.println(cs[i] + " " + i);
			if(cs[i] == '1')
			{
				
				code.add(cs.length - i);
			}
		}
		for (Integer i : code)
		{
			System.out.println(i);
		}
		
	}
	
	@Test
	public void test2()
	{
		Optional<String> str = null;
		System.out.println(str);
	}
	
	public Integer getRole(int code)
	{
		return (int) Math.pow(2, code);
	}
	
	@Test
	public void test3()
	{
		Integer role = null;
		String[] code =
			{ "0", "1", "2" };
		for (String s : code)
		{
			if(role == null)
			{
				role = getRole(Integer.valueOf(s));
			}
			else
			{
				
				role += getRole(Integer.valueOf(s));
			}
			
		}
		System.out.println(role);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test4()
	{
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("a");
		list.add("b");
		list = list.stream().distinct().collect(Collectors.toList());
		for (String i : list)
		{
			System.out.println(i);
		}
	}
	
	@Test
	public void test5()
	{
		
		String timeStamp = "1531782000000";// 直接是时间戳
		long l = Long.parseLong(timeStamp);
		// long timeStamp = System.currentTimeMillis();
		// //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 这个是你要转成后的时间的格式
		String sd = sdf.format(new Date(l)); // 时间戳转换成时间
		System.out.println(sd);// 打印出你要的时间
		
	}
	
	@Test
	public void jinzhi()
	{
		// 二进制转十进制
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a binary number: ");
	//	String binaryString = input.nextLine();
		// System.out.println("Output: " + Integer.parseInt(binaryString, 2));
		
		// 十进制转二进制
		//方法一
		int n = 13;
	//	for (int i = 31; i >= 0; i--)
	//		System.out.print(n >>> i & 1);
		
		//方法二
		String result = Integer.toBinaryString(n);
		// int r = Integer.parseInt(result);
		// System.out.println(r);
		System.out.print(result);
	}
	
	@Test
	public void test6()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		
		System.out.println(date);
		System.out.println(System.currentTimeMillis());
		System.out.println(System.currentTimeMillis());
		
		Calendar c = new GregorianCalendar();
		Date d1 = new Date();
		
		c.setTime(d1);//设置参数时间
		c.add(Calendar.SECOND,-30);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
		Date d2=c.getTime();
		
		System.out.println(d1.getTime() - d2.getTime());
		
		if((d1.getTime() - d2.getTime()) < 20)
		{
			System.out.println(d1);
			System.out.println(d2);
		}
	}
}
