close all
%1
x = [1 1.5 2 3 4];
y = [0 0.17609 0.30103 0.47712 0.60206];
xx = [2.5 3.25];
i = [10:35];
yi = i ./ 10 ;
newton(x, y, xx)
fprintf("max error = %f \n", max(abs(log10(yi) - newton(x, y, yi))))

%2
figure(1)
x=[1 2 3 4 5];
f=[22 23 25 30 28];
yi = [2.5];
N = newton(x,f,yi)
hold on
plot(x,f,'b*')
z = 0:0.01:6;
A = newton(x, f, z);
plot(z, A,'g')
hold off

%3
figure(2)
X = 0 : 0.01 : 6;
f = @(x) exp(sin(x));
plot(X, f(X), 'r'); 

figure(3)
x = linspace(0, 6, 13);
y = newton(x, f(x), X);

hold on
plot(X, y, 'g');
plot(x, f(x), 'b*');
hold off

%4
x = [64 81 100 121 144 169];
y = [8 9 10 11 12 13];
fprintf("sqrt(15) = %f \n", sqrt(115));
fprintf("aprox = %f \n", aitken(x,y,115));
