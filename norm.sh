#!/bin/bash
COUNTER=0
RMSCOUNTER=0

while read line; do
RMSSTAT=$(sox $line -n stat 2>&1 | grep "RMS     amplitude:")
echo "RMSSTAT = \"$RMSSTAT\""
RMSCOUNTER=$(echo "scale=6; $RMSCOUNTER + ${RMSSTAT##*\ }" | bc)
echo "RMSSTAT value = \"${RMSSTAT##*\ }\" ... RMSCOUNTER updated to \"$RMSCOUNTER\""
COUNTER=$[COUNTER + 1]
echo "COUNTER = $COUNTER"
done < fileSelection.txt

MEANRMS=$(echo "scale=6; $RMSCOUNTER / $COUNTER" |bc)
echo "MEANRMS = \"$MEANRMS\""

#now to actually do stuff
while read line; do
TEMPFILE=`echo $line | sed 's/\(.*\)\.wav/\1-temp\.wav/'`
normalize-audio -a $MEANRMS $line
sox $line $TEMPFILE fade h 00:00:03 0 00:00:03
mv $TEMPFILE $line
done < fileSelection.txt
