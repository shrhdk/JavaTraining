#!/usr/bin/ruby

STDOUT.sync = true

while str = STDIN.gets
  break if str.chomp == "exit"
  print "hello ", str
end
