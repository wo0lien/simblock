#!/usr/bin/env bash

# CONSTANTS

startN=1
stopN=101
step=100
k=8
kstep=4
kstop=16
inter=$(( 1000 * 60 * 10 )) # 10 minutes
maxHeight=100 # maximal block height

# progress bar function
prog() {
    local w=80 p=$1;  shift
    # create a string of spaces, then change them to dots
    printf -v dots "%*s" "$(( $p*$w/100 ))" ""; dots=${dots// /.};
    # print those dots on a fixed-width space plus the percentage etc. 
    printf "\r\e[K|%-*s| %3d %% %s" "$w" "$dots" "$p" "$*"; 
}

# main
gradle build

for((j=$k;$j<=$kstop;j=$j+$kstep)); do

  # init
  printf "Loop with k = $j\n"
  n=$startN
  printf "" > results-typeE-h$maxHeight-k$j-i$inter.csv
  printf "" > results-typeN-h$maxHeight-k$j-i$inter.csv

  for((i=$startN;$i*$step<$stopN;i++)); do
    n=$(( i * step ))
    current=$(( 100 * (n - startN) / (stopN-startN) ))
    prog "$current"  // noeuds : $n / $stopN
    printf "$inter; $n; $maxHeight;" >> out.csv # append to the file in csv format
    gradle :simulator:run --quiet --args="$inter $n $maxHeight $j 0" >> results-typeE-h$maxHeight-k$j-i$inter.csv
    gradle :simulator:run --quiet --args="$inter $n $maxHeight $j 1" >> results-typeN-h$maxHeight-k$j-i$inter.csv
  done
  printf "\n"
done
