x = [1,2,3,4,5,6,7];
f = [13,15,20,14,15,13,10];
t = 8;

A = [sum(x.^2), sum(x); sum(x), length(x)];
B = [sum(x.*f); sum(f)];

X = linsolve(A,B);
fprintf('phi=%f x + %f\n',X);
approx = polyval(X, t)
E = sum((f-polyval(X,x)).^2)

hold on
plot(x,f,'g*');
xp = 1:0.01:8;
plot(xp, polyval(X, xp),'g-');
hold off;