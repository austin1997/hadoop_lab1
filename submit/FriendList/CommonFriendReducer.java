package edu.rosehulman.zhaiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * TODO Put here a description of what this class does.
 *
 * @author zhaiz.
 *         Created 2017/09/13.
 */
public class CommonFriendReducer extends Reducer<Text, Text, Text, Text> {
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param key
	 * @param values
	 * @param context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)throws 
	IOException,InterruptedException{
		Iterator<Text> itr = values.iterator();
		ArrayList<String> friends1 = new ArrayList<String>(Arrays.asList(itr.next().toString().split(",")));
		ArrayList<String> friends2 = new ArrayList<String>(Arrays.asList(itr.next().toString().split(",")));
		String out = "";
//		String test = friends1 + " : " + friends2;
//		String test2 = "";
		for (String friend : friends1) {
//			test2 += friend + " " + friends2.contains(friend);
			if (friends2.contains(friend)) {
				out += friend + ",";
			}

		}
		if (out.isEmpty())
			context.write(key, new Text(out));
		else
			context.write(key, new Text(out.substring(0, out.length()-1)));
	}
}
