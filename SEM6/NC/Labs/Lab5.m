close all
%1
t = [0 3 5 8 13];
f = [0 225 383 623 993];
d = [75 77 80 74 72];
x = 10;

hermite(t,f,d,x)

%2
t = [1 2];
f = [0 0.6931];
d = [1 0.5];
x = 1.5;

fprintf("f(1.5) =  %f\n",hermite(t,f,d,x));
fprintf("error %f\n",abs(log(x) - hermite(t,f,d,x)));

%3
t = linspace(-5,5,15);

ff = @(x)sin(2*x);
f = ff(t);

dt = @(x)2*cos(2*x);
d = dt(t);

x = linspace(-5,5,50);

H = hermite(t,f,d,x);

hold on 
plot(x, ff(x),'b*')
plot(x, H, 'g')
hold off