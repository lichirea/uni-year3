rectangle('Position', [0 0 3 5])

[x, y] = ginput(10);
p = polyfit(x, y, 2);

hold on
xp = 0:0.01:3;
plot(x, y, 'rp')
plot(xp, polyval(p, xp), 'k-');
hold off