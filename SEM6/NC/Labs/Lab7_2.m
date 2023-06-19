t = [0,10,20,30,40,60,80,100];
p = [0.0061,0.0123,0.0234,0.0424,0.0738,0.1992,0.4736,1.0133];

p1 = polyfit(t,p,2);
p2 = polyfit(t,p,3);


a1 = polyval(p1,45)
a2 = polyval(p2,45)
actual = 0.095848;

e1 = abs(actual - a1);
e2 = abs(actual - a2);
fprintf("e1 = %f\n",e1)
fprintf("e2 = %f\n",e2)

xp = [0:0.01:100];
pp = polyfit(t,p,7);

hold on
plot(t, p, 'r*');
plot(xp, polyval(p1, xp), 'b-');
plot(xp, polyval(p2, xp), 'g-');
plot(xp, polyval(pp, xp), 'y-');
hold off