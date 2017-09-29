package edu.rosehulman.zhaiz;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * TODO Put here a description of what this class does.
 *
 * @author zhaiz.
 *         Created 2017/09/12.
 */
public class MinTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)throws 
	IOException,InterruptedException{
		
		int minValue = Integer.MAX_VALUE;
		for(IntWritable value:values){
			minValue = Math.min(minValue,value.get());
		}

		context.write(key, new IntWritable(minValue));
	}
}
