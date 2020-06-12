#!/usr/bin/env bash

# CONSTANTS

startN=1
stopN=10000
step=100
inter=$(( 1000 * 60 * 10 )) # 10 minutes
maxHeight=2000 # maximal block height

# progress bar function
prog() {
    local w=80 p=$1;  shift
    # create a string of spaces, then change them to dots
    printf -v dots "%*s" "$(( $p*$w/100 ))" ""; dots=${dots// /.};
    # print those dots on a fixed-width space plus the percentage etc. 
    printf "\r\e[K|%-*s| %3d %% %s" "$w" "$dots" "$p" "$*"; 
}

# main
printf "" > out.csv
gradle build
n=$startN

for((i=$startN;$i*$step<$stopN;i++)); do
  n=$(( i * step ))
  current=$(( 100 * (n - startN) / (stopN-startN) ))
  prog "$current"  // noeuds : $n / $stopN
  printf "$inter; $n; $maxHeight;" >> out.csv # append to the file in csv format
  gradle :simulator:run --quiet --args="$inter $n $maxHeight" >> out.csv
done
