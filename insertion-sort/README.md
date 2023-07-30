# Insertion Sort

Selection is a in-place sorting algrithm, wich means that it does not create any other arrays, but change the position of the original.

It's complexity is quadratic constant => O(n^2) = Omega(n^2)

The current implementation considers a copy of the original array and returns the sorted copy.

This algorithm iterates through the list comparing pairs, when it finds the lower value between one pair, it stores the lower value into a variable and
iterates backwards moving the position of the greater number one index foward and comparing pairs with stored value. Then, when it finds a value that is lower 
thant the value sotred, or it's the beginning of the list, it puts the stored value into that position.
