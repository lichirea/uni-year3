x = [0, pi / 2, pi, (3 * pi) / 2, 2 * pi];
y = sin(x);
actual = sin(pi/4)
t = spline(x, y);
natural = ppval(t, pi / 4)
t1 = spline(x, [1, y, 1]);
clamped = ppval(t1, pi / 4)
hold on
plot(x,y,'pb')
fplot(@(X) ppval(t, X), [0, 2 * pi],'k')
fplot(@(X) ppval(t1, X), [0, 2 * pi],'r')
hold off