package edu.rosehulman.zhaiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * TODO Put here a description of what this class does.
 *
 * @author zhaiz.
 *         Created 2017/09/13.
 */
public class CommonFriendMapper extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	public void map(LongWritable key, Text value, Context context) throws 
	IOException,InterruptedException{
		String line = value.toString();
		String[] people = line.split(",");
		for (int i = 1; i < people.length; i++) {
			ArrayList<String> first = new ArrayList<String>(2);
			ArrayList<String> second = new ArrayList<String>();
			first.add(people[0]);
			first.add(people[i]);
			for (int j = 1; j < people.length; j++) {
				if (i == j) continue;
				second.add(people[j]);
			}

			Collections.sort(first);
			Collections.sort(second);
			System.out.println("first: "+first);
			System.out.println("second: "+second);
			
			String outKey = first.get(0)+","+first.get(1);
			String outValue = "";
			for (String str : second) {
				outValue += str + ",";
			}
			if (outValue.isEmpty()) {
				context.write(new Text(outKey), new Text(outValue));
			}else
				context.write(new Text(outKey), new Text(outValue.substring(0, outValue.length()-1)));
		}
	}
}
