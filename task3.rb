#!/usr/bin/ruby
require 'optparse'

# parse some command line options
input = nil
output = nil
file = nil
OptionParser.new do |opts|
  opts.on("-i Target_Location", "--input Target_Location", "Target location on HDFS") do |val|
    input = val
  end
  opts.on("-o Target_Location", "--output Target_Location", "Target location on Sandbox") do |val|
    output = val
  end
  opts.on("-f filename", "--file filename", "The file to be copied") do |val|
    file = val
  end
end.parse(ARGV)
raise "You must specify a target location on HDFS (-i)" if input.nil?
raise "You must specify a target location on Sandbox (-o)" if output.nil?
raise "You must specify a file (-f)" if file.nil?

temp = File.join(input, file)
puts "hadoop fs -put #{file} #{input}"
puts "hadoop fs -get #{temp} #{output}"

`hadoop fs -put #{file} #{input} `
`hadoop fs -get  #{temp} #{output}`

