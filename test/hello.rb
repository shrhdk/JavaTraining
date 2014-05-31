#!/usr/bin/ruby

if ARGV.length == 0 then
  puts 'hello'
else
  ARGV.each do |who|
    puts 'hello ' + who
  end
end
