[x,y] = ginput(5);
a = min(x);
b = max(x);
t = linspace(a,b,50);
p = spline(x,y);

hold on
plot(x, y, '*r')
plot(t, ppval(p, t), 'b')
hold off