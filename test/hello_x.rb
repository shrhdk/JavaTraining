#!/usr/bin/ruby

puts 'hello'
while str = STDIN.gets
  break if str.chomp == "exit"
  print "hello ", str
end
