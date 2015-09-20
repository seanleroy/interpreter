read x;
t0 := 0;
t1 := t0 < x;
if (t1 == 0 ) goto l0;
t2 := 1;
t3 := t2;
fact := t3;
l1:
fact := fact * x;
t4 := fact;
fact := t4;
t5 := 1;
x := x - t5;
t6 := x;
x := t6;
t8 := 0;
t7 := x == t8;
if (t7 == 0 ) goto l1;
t9 := fact;
write t9;
l0:
halt;
