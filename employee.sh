#!/bin/bash

#echo "Enter the user name : "
#name="inc8"
#echo -n "Enter password : "
#read -s 
#password="Root@123"

#myDatabase="company_details"


temp=($(mysql -u inc8 -pRoot@123 -e "use company_details;select * from employee"))
len=${#temp[@]}

cat > output.html <<EOF
<table border=2>
EOF

for (( i=0; i<${len}; i++ ));
do
{
rem=$((i%5))
	if [ $rem -eq 0 ]
	then
cat >> output.html <<EOF
 <tr>	 
EOF

    fi

    if [ $i -lt 5 ]
    then	
cat >> output.html <<EOF 
	  <th>${temp[$i]}</th>
EOF
  
    else
cat >> output.html <<EOF
	 <td>${temp[$i]}</td>
EOF
   fi

  rem1=$(((i+1)%5))

  if [ $rem1 -eq 0 ]
  then
  cat >> output.html <<EOF
  </tr>
EOF
  
  fi
}
done

cat >> output.html <<EOF
</table>
EOF

#echo "$temp" > output.html
open=$(google-chrome output.html)

echo $open
		

